package geometries;

import primitives.Point3D;
import primitives.Vector;

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