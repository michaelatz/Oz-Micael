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
	Color emmission;
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
	 * @return Emmission
	 */
	public Color getEmmission() {
		return emmission;
	}

	/**
	 * @param emmission the emmission to set
	 */
	public void setEmmission(Color emmission) {
		this.emmission = emmission;
	}

	/**
	 * @return Material
	 */
	public Material getMaterial() {
		return material;
	}
	
	/**
	 * @param Material
	 */
	public void setMaterial(Material mat) {
		this.material = mat;
	}
}