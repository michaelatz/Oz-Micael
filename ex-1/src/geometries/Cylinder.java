package geometries;

import java.util.List;

import primitives.*;

/**
 * Class representing Cartesian 3D cylinder
 * 
 * @author michael
 * @author oz
 *
 */
public class Cylinder extends Tube {
	private double height;

	/********* constructors *********/
	/**
	 * Constructs a cylinder with radius, height and axis ray
	 * 
	 * @param double _radius, radius of the cylinder
	 * @param double _height, height of the cylinder
	 * @param Ray    _axis, axis of the cylinder
	 * @throws new IllegalException when radius is smaller than zero
	 */
	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this.height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Height of he cylinder getter
	 * 
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	// ***************** Operations ******************** //
	/**
	 * get normal function
	 * 
	 * @param Point3D
	 * @return getNormal
	 */
	public Vector getNormal(Point3D poi) {
		Point3D p1 = _axisRay.getHead();
		Vector v = _axisRay.getV();
		Vector u = poi.subtract(p1);// vector from p1 to poi
		double t = v.dotProduct(u);// size of projection of vector u on the ray, point on the ray and plane
									// crossing P and orthogonal to the ray
		if (t == 0 || (t - this.height) == 0)
			return v;
		return poi.subtract(p1.add(v.scale(t))).normalization();
	}

	public List<GeoPoint> findIntersections(Ray ray) {
		return null;
	}
}