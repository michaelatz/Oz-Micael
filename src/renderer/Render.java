package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Random;

/**
 * class represents a renderer for a scene
 */
public class Render {
	ImageWriter image;
	Scene scene;
	// ***************** Constructors ********************** //

	/**
	 * creates a renderer by the scene (3D model) and a view plane size
	 *
	 * @param image
	 * @param scene
	 */
	public Render(ImageWriter image, Scene scene) {
		this.image = image;
		this.scene = scene;
	}
	// ***************** Operations ******************** //

	/**
	 * renders the scene to a 2D picture
	 */
	public void renderImage() {

		for (int i = 0; i < image.getNy(); ++i)
			for (int j = 0; j < image.getNx(); ++j) {
				Ray ray = scene.getCamera().constructRayThroughPixel(image.getNx(), image.getNy(), j, i,
						scene.getScreenDistance(), image.getHeight(), image.getWidth());
				GeoPoint closestPoint = findClosestIntersection(ray);
				image.writePixel(j, i, closestPoint == null ? scene.getBackground().getColor()
						: calcColor(closestPoint, ray).getColor());
			}
	}

	/**
	 * renders the scene to a 2D picture for DOF
	 */
	public void renderImageDOF(Sphere aperture, double focalDistance) {

		for (int i = 0; i < image.getNy(); ++i)
			for (int j = 0; j < image.getNx(); ++j) {
				Ray ray = scene.getCamera().constructRayThroughPixel(image.getNx(), image.getNy(), j, i,
						scene.getScreenDistance(), image.getHeight(), image.getWidth());
				GeoPoint closestPoint = findClosestIntersection(ray);
				image.writePixel(j, i,
						closestPoint == null ? scene.getBackground().getColor()
								: generateDOFfromCamera(this.image, this.scene, aperture, focalDistance, closestPoint)
										.getColor());
			}
	}

	/**
	 * prints a 2D grid on the rendered picture
	 *
	 * @param interval - the number of pixels between each line
	 * @param color    - the color of the grid
	 */
	public void printGrid(int interval, primitives.Color color) {

		for (int i = 1; i < image.getNy(); i++) {
			for (int j = 1; j < image.getNx(); j++)
				if (i % interval == 0 || j % interval == 0)
					image.writePixel(j, i, color.getColor());
		}
	}

	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		image.writeToimage();
	}

	private static final double EPS = 1.0;

	/**
	 * the function calculates the transparency of the geometry ang the point
	 *
	 * @param l        - the direction of the light source
	 * @param n        - the normal to the geometry
	 * @param geopoint - the transparency in the point
	 * @return the transparency intense
	 */
	private double transparency(Vector l, Vector n, GeoPoint geopoint, double d) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
		Point3D point = geopoint.point.add(epsVector);
		Ray lightRay = new Ray(lightDirection, point);
		List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
		if (intersections == null)
			return 1;
		double ktr = 1;
		for (GeoPoint gp : intersections)
			if (gp.point.distance(geopoint.point) <= d)
				ktr *= gp.geometry.getMaterial().getKt();
		return ktr;
	}

	/**
	 * the function calculates the reflected ray from the geometry
	 *
	 * @param n     - the normal to the geometry
	 * @param point - the intersection point
	 * @param inRay - the ray to the point
	 * @return the reflected ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getV();
		Vector new_v = v.add((n.scale(v.dotProduct(n))).scale(-2));
		Point3D newPoint = point.add(new_v.scale(0.1));
		return new Ray(new_v, newPoint);
	}

	/**
	 * the function refracts the ray by Snell's law
	 *
	 * @param point - the intersection point
	 * @param inRay - the ray to the point
	 * @return the refracted ray
	 */
	private Ray constructRefractedRay(Point3D point, Ray inRay) {
		Vector v = inRay.getV();
		Point3D newPoint = point.add(v.scale(0.1));
		return new Ray(v, newPoint);
	}

	/**
	 * the function calculates the diffusion of the light on the geometry
	 *
	 * @param kd             - the diffusive coefficient index
	 * @param l              - the direction of the light source
	 * @param n              - the normal to the geometry
	 * @param lightIntensity - the intensity of the light
	 * @return the diffused intensity of the light
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double diffusive = kd * Math.abs(l.dotProduct(n));
		return lightIntensity.scale(diffusive);
	}

	/**
	 * the function calculates the specular reflection of the light on the geometry
	 *
	 * @param ks             - the specular coefficient index
	 * @param l              - the direction of the light
	 * @param n              - the normal to the geometry
	 * @param v              - the direction from the camera to the geometry
	 * @param shininess      - the shininess index
	 * @param lightIntensity - the intensity of the light
	 * @return the specular reflection intensity
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
		Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normal();
		double temp = v.scale(-1).dotProduct(r);
		if (temp <= 0)
			return new Color(0, 0, 0);
		return lightIntensity.scale(ks * Math.pow(temp, shininess));
	}

	/**
	 * finds closest intersection of the ray with geometry
	 *
	 * @param points - the intersection point
	 * @return the closest point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> points) {
		Point3D p0 = scene.getCamera().getP0();
		return pointClosestTo(points, p0);
	}

	/**
	 * the function finds the closest intersection point using the getClosestPoint
	 * that take a intersection list of geopoints and return the closest.
	 *
	 * @param ray in space
	 * @return the closest geometry intersection point to the camera
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> points = scene.getGeometries().findIntersections(ray);
		if (points == null)
			return null;
		Point3D p0 = ray.getHead();
		return pointClosestTo(points, p0);
	}

	/**
	 * finds the closest point from a given list to a point
	 *
	 * @param points
	 * @param p0
	 * @return the closest point
	 */
	private GeoPoint pointClosestTo(List<GeoPoint> points, Point3D p0) {
		double minValue = points.get(0).getPoint().distance(p0);
		GeoPoint minPoint = points.get(0);
		for (int i = 0; i < points.size(); ++i) {
			GeoPoint p = points.get(i);
			double d0 = p.point.distance(p0);
			if (d0 < minValue) {
				minValue = d0;
				minPoint = p;
			}
		}
		return minPoint;
	}

	/*********** CalcColor function *************/

	private static final int MAX_CALC_COLOR_LEVEL = 10;

	/**
	 * calculates the color at a certain point (pixel) by calling calculator
	 * function
	 *
	 * @param -   GeoPoint in the space (pixel on the view plane)
	 * @param ray
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(scene.getAmbientLight().getIntensity());
	}

	private static final double MIN_CALC_COLOR_K = 0.001;

	/**
	 * calculates the color at a certain point
	 *
	 * @param -      GeoPoint in the space (pixel on the view plane)
	 * @param ray
	 * @param int    level
	 * @param double k
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;
		Color color = geopoint.geometry.getEmission(); // remove Ambient Light

		Vector v = geopoint.point.subtract(scene.getCamera().getP0()).normal();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		Material mat = geopoint.geometry.getMaterial();
		double kd = mat.getkD();
		double ks = mat.getkS();
		int nShininess = mat.getNShininess();
		for (LightSource lightSource : scene.getLights()) {
			Vector l = lightSource.getL(geopoint.point);
			double e1 = n.dotProduct(l);
			double e2 = n.dotProduct(v);
			if ((e1 > 0 && e2 > 0) || (e1 < 0 && e2 < 0)) {
				double ktr = transparency(l, n, geopoint, lightSource.getDist(geopoint.point));
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}
		double kr = geopoint.geometry.getMaterial().getKr();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null)
				color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		double kt = geopoint.geometry.getMaterial().getKt();
		double kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(geopoint.point, inRay);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null)
				color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}

	private static Random random = new Random(1);
	private static int count = 0;

	/**
	 * Calculation the depth of field from the camera
	 * 
	 * @param img
	 * @param scene
	 * @param aperture
	 * @param focalDistance
	 * @param pixel
	 */
	Color generateDOFfromCamera(ImageWriter img, Scene scene, Sphere aperture, double focalDistance, GeoPoint pixel) {
		Ray fromCam = new Ray(pixel.point.subtract(scene.getCamera().getP0()), scene.getCamera().getP0());
		Vector vec = fromCam.getV().normal().scale(focalDistance);
		Point3D focalPoint = fromCam.getHead().add(vec);// The focal point
		Color pixelColor = Color.BLACK;
		
		// Casting of 15 different rays threw each pixel
		for (int i = 0; i < 15; i++) {
			double angle = random.nextDouble() * 360;
			double newRadius = random.nextDouble() * (aperture.getRadius()-1);
			double cos=Math.cos(angle);
			double sin=Math.sin(angle);
			Point3D newPoint = new Point3D((newRadius*cos), (newRadius * sin), 0);
			Point3D onFocal = aperture.getCenter().add(new Vector(newPoint));// point on the aperture
			Vector direction = focalPoint.subtract(onFocal);
			Ray focalRay = new Ray(direction, onFocal);
			Color addColor=calcColor(pixel, focalRay);
			pixelColor = pixelColor.add(addColor);
		}
		System.out.println(count++);
		return pixelColor.reduce(15);
	}

	/*
	 * void generateDOFfromEye(ImageWriter img, Camera camera, Scene scene, double
	 * focusPoint) { double pixelWidth = 1.0f / (double) img.getWidth(); double
	 * pixelHeight = 1.0f / (double) img.getHeight();
	 * scene.setBackground(Color.BLACK); for (int y = 0; y < img.getHeight(); ++y) {
	 * for (int x = 0; x < img.getWidth(); ++x) { //Center of the current pixel
	 * double px = ( x * pixelWidth) - 0.5; double py = ( y * pixelHeight) - 0.5;
	 * 
	 * Ray cameraSpaceRay = Ray(Vector(0,0,0,1), Vector(px, py, 1.0f, 0.0f));
	 * 
	 * Ray ray = camera.Transform() * cameraSpaceRay;
	 * 
	 * int depth = 0; int focaldistance = 2502; Color blend(0,0,0,0);
	 * 
	 * 
	 * //Stratified Sampling i.e. Random sampling (with 16 samples) inside each
	 * pixel to add DOF for(int i = 0; i < 16; i++) { //random values between [-1,1]
	 * float rw = (static_cast<double>(rand() % RAND_MAX) / RAND_MAX) * 2.0f - 1.0f;
	 * float rh = (static_cast<double>(rand() % RAND_MAX) / RAND_MAX) * 2.0f - 1.0f;
	 * // Since eye position is (0,0,0,1) I generate samples around that point with
	 * a 3x3 aperture size window. float dx = ( (rw) * 3 * pixelWidth) - 0.5; float
	 * dy = ( (rh) * 3 * pixelHeight) - 0.5;
	 * 
	 * //Now here I compute point P in the scene where I want to focus my scene
	 * Vector P = Vector(0,0,0,1) + focusPoint * ray.Direction(); Vector dir = P -
	 * Vector(dx, dy, 0.0f, 1.0f);
	 * 
	 * 
	 * ray = Ray(Vector(dx,dy,0.0f,1.0f), dir); ray = camera.Transform() * ray;
	 * 
	 * //Calling the phong shader to render the scene blend += phongShader(scene,
	 * ray, depth, output);
	 * 
	 * } blend /= 16.0f;
	 * 
	 * img(x, y) += blend; } } }
	 */
}