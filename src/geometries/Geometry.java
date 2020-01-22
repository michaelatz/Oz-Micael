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
	private Color emmission;
	Material material;

	/**
	 * Calculates unit vector orthogonal to the tangent plane at the point (which is
	 * called - normal)
	 * 
	 * @param p point of tangent...
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point3D p);

	/**
	 * 
	 * @return getEmmission
	 */
	public Color getEmmission() {
		return emmission;
	}

	public Material getMaterial() {
		return material;
	}
}