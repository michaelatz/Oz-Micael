package geometries;

import primitives.*;

/**
 * Class represents radial shapes in 3D space
 * 
 * @author michael
 * @author oz
 */
public abstract class RadialGeometry extends Geometry {
	protected double _radius;

	// ***************** Constructors ********************** //
	/**
	 * Constructor with radius
	 * 
	 * @param _radius
	 * @throws IllegalArgumentException when radius "is smaller" than zero
	 */
	public RadialGeometry(double _radius) {
		if (_radius > 0)
			this._radius = _radius;
		else {
			throw new IllegalArgumentException("radius cant be smaller then zero");
		}
	}

	/**
	 * Constructor with radius and color
	 * 
	 * @param _radius
	 * @param color
	 * @throws IllegalArgumentException when radius "is smaller" than zero
	 */
	public RadialGeometry(Color color, double _radius) {
		super(color);
		if (_radius > 0)
			this._radius = _radius;
		else
			throw new IllegalArgumentException("radius cant be smaller then zero");
	}

	/**
	 * Constructor with radius, color and material
	 * 
	 * @param _radius
	 * @param color
	 * @param material
	 * @throws IllegalArgumentException when radius "is smaller" than zero
	 */
	public RadialGeometry(Material material, Color color, double _radius) {
		super(material, color);
		if (_radius > 0)
			this._radius = _radius;
		else
			throw new IllegalArgumentException("radius cant be smaller then zero");
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Gets the radius of the geometry
	 * 
	 * @return the radius
	 */
	public double getRadius() {
		return _radius;
	}
}