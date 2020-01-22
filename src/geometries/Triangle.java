package geometries;

import java.util.List;

import primitives.*;

/**
 * class represents triangle in 3D space
 * 
 * @author michael
 * @author oz
 *
 */
public class Triangle extends Polygon {

	// ***************** Constructors ********************** //
	/**
	 * Constructs triangle with points
	 * 
	 * @param points
	 * @param plane
	 */
	public Triangle(Point3D... points) {
		super(points);
	}

	// ***************** Operations ******************** //
	/**
	 * get normal function of triangle, we use in the plane's function
	 * 
	 * @param Point3D
	 * @return normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return this._plane.getNormal(p);
	}

	/**
	 * constructs a triangle from three points
	 *
	 * @param p1, first point
	 * @param p2, second point
	 * @param p3, third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}
}
