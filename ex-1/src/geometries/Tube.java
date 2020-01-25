package geometries;

import java.util.List;

import primitives.*;

/**
 * class represents ube i 3D space
 * 
 * @author michael
 * @author oz
 *
 */
public class Tube extends RadialGeometry {
	protected Ray _axisRay;

	// ***************** Constructors ********************** //
	/**
	 * constructs a tube from axis and radius
	 *
	 * @param axisRay: the axis of the tube
	 * @param radius:  the radius of the tube
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		_axisRay = axisRay;
	}
	
	/**
	 * constructs a tube from axis and radius and color
	 *
	 * @param axisRay: the axis of the tube
	 * @param radius:  the radius of the tube
	 * @param color
	 */
	public Tube(Color color, Ray axisRay, double radius) {
		super(color, radius);
		_axisRay = axisRay;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Axis of the tube getter
	 *
	 * @return axis of the tube
	 */
	public Ray getAxisRay() {
		return _axisRay;
	}

	// ***************** Operations ******************** //
	/**
	 * Get normal of the tube
	 * 
	 * @param p
	 * @return normal to the tube
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Point3D p0 = _axisRay.getHead();
		Vector v = _axisRay.getV();
		Vector u = p.subtract(p0);// Vector from p0 to p
		double t = v.dotProduct(u);// size of projection of vector u on the ray, point on the ray and plane
		// crossing p and orthogonal to the ray

		Point3D o;
		if (t == 0)
			o = p0;
		else
			o = p0.add(v.scale(t));
		return p.subtract(o).normalization();

	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		return null;
	}
}
