package geometries;

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
	 * constructs a triangle from three points
	 *
	 * @param p1, first point
	 * @param p2, second point
	 * @param p3, third point
	 */
	public Triangle(Point3D p1, Point3D p2, Point3D p3) {
		super(p1, p2, p3);
	}

	/**
	 * Constructs triangle with 3 points and color
	 * 
	 * @param points
	 * @param color
	 */
	public Triangle(Color color, Point3D p1, Point3D p2, Point3D p3) {
		super(color, p1, p2, p3);
	}

	/**
	 * Constructs triangle with 3 points, color and material
	 * 
	 * @param points
	 * @param color
	 */
	public Triangle(Material material, Color color, Point3D p1, Point3D p2, Point3D p3) {
		super(material, color, p1, p2, p3);
	}
}