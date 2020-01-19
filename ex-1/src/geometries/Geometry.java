package geometries;

import primitives.*;

/**
 * Interface of the whole geometry shapes which gives the the ability to find a
 * normal and intersec with rays.
 * 
 * @author michael
 * @author oz
 *
 */
public abstract class Geometry implements Intersectable {
	public Color emmission = new Color(0, 0, 0);

	/**
	 * Calculates unit vector orthogonal to the tangent plane at the point (which is
	 * called - normal)
	 * 
	 * @param p point of tangent...
	 * @return normal vector
	 */
	abstract Vector getNormal(Point3D p);
}