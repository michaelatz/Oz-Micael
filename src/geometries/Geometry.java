package geometries;

import primitives.*;

/**
 * Interface of the whole geometry shapes which gives the the ability to find a
 * normal and intersect with rays.
 *
 * @author michaul
 * @author oz
 */
public abstract class Geometry implements Intersectable {
	private Color emission;
	private Material material;

	// ***************** Constructors ********************** //
	/*
	 * /** Constructs a Geometry
	 */
	public Geometry() {
		this(new Material(1, 0, 100), Color.BLACK);
	}

	/**
	 * Constructs a Geometry with a color
	 * 
	 * @param color
	 */
	public Geometry(Color emission) {
		this(new Material(0.9, 0, 100), emission);
	}

	/**
	 * Constructs a Geometry with a color and material
	 * 
	 * @param color
	 * @param material
	 */
	public Geometry(Material material, Color emission) {
		this.emission = emission;
		this.material = material;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * getter of the emission light of the geometry
	 *
	 * @return the emission light
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * getter of the material of the geometry
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return this.material;
	}

	/**
	 * Calculates unit vector orthogonal to the tangent plane at the point
	 *
	 * @param p point of tangent
	 * @return normal vector
	 */
	public abstract Vector getNormal(Point3D p);
}