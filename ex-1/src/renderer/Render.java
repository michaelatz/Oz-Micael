package renderer;

import java.util.*;

import elements.*;
import geometries.Intersectable.GeoPoint;
import geometries.*;
import primitives.*;
import primitives.Vector;
import scene.Scene;

public class Render {
	ImageWriter IWriter;
	Scene scene;
	private Object Vector;

	/**
	 * @param iWriter
	 * @param scene
	 */
	public Render(ImageWriter iWriter, Scene scene) {
		this.IWriter = iWriter;
		this.scene = scene;
	}

	public void renderImage() {
		for (int i = 0; i < IWriter.getHeight(); i++) {
			for (int j = 0; j < IWriter.getWidth(); j++) {
				Ray ray = scene.getCamera().constructRayThroughPixel(IWriter.getNx(), IWriter.getNy(), j, i,
						scene.getScreenDistance(), IWriter.getWidth(), IWriter.getHeight());
				if (scene.getGeometries().findIntersections(ray) == null) {
					IWriter.writePixel(j, i, scene.getBackground().getColor());
				} else {
					List<GeoPoint> intersectionPoints = scene.getGeometries().findIntersections(ray);
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					if (scene.getAmbientLight() != null)
						IWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
		}
	}

	/**
	 * get Closest Point function
	 * 
	 * @param intersectionPoints
	 * @return closest point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		GeoPoint closest = intersectionPoints.get(0);
		for (int i = 1; i < intersectionPoints.size(); i++) {
			GeoPoint a = intersectionPoints.get(i);
			GeoPoint b = intersectionPoints.get(i - 1);
			if (a.point.distance(scene.getCamera().getP0()) > b.point.distance(scene.getCamera().getP0())) {
				closest = a;
			}
		}
		return closest;
	}

	/**
	 * calcColor function
	 * 
	 * @param point
	 * @return color
	 */
	private Color calcColor(GeoPoint intersection) {
		Color color = scene.getAmbientLight().getIntensity();
			color = color.add(intersection.geometry.getEmmission());
			Vector v = intersection.point.subtract(scene.getCamera().getP0()).normalization();
			Vector n = intersection.geometry.getNormal(intersection.point);
			int nShininess = intersection.geometry.getMaterial().getnShininess();
			double kd = intersection.geometry.getMaterial().getkD();
			double ks = intersection.geometry.getMaterial().getkS();
			for (LightSource lightSource : scene.getLights()) {
				Vector l = lightSource.getL(intersection.point);
				if ((n.dotProduct(l) > 0) && (n.dotProduct(v) > 0) || (n.dotProduct(l) < 0) && (n.dotProduct(v) < 0)) {
					Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		return color;
	}

	/**
	 * print Grid
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		for (int i = 0; i < IWriter.getNx(); i++) {
			for (int j = 0; j < IWriter.getNy(); j++) {
				if (i % interval == 0 || j % interval == 0) {
					this.IWriter.writePixel(j, i, java.awt.Color.white);
				}
			}
		}

	}

	/**
	 * write to image
	 */
	public void writeToImage() {
		IWriter.writeToimage();
	}

	/**
	 * calcDiffusive
	 */
	public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		Math.abs(kd = kd + l.dotProduct(n));
		return lightIntensity.scale(kd);
	}

	/**
	 * calcSpecular
	 */
	public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nsh, Color lightIntensity) {
		double num = 2 * (l.dotProduct(n));
		Vector r = l.subtract(n.scale(num)).normalization();
		double rv = v.scale(-1).dotProduct(r);
		if (rv <= 0)
			return new Color(0, 0, 0);
		return lightIntensity.scale(Math.pow(rv, nsh) * ks);

	}
}
