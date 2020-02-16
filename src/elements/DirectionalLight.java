package elements;

import primitives.*;

/**
 * class representing Directional light
 * 
 * @author michael
 * @author oz
 */
public class DirectionalLight extends Light implements LightSource {
	Vector _direction;

	// ***************** Constructors ********************** //

	/**
	 * DirectionaLight constructor with color and vector
	 * 
	 * @param i0
	 * @param direction
	 */
	public DirectionalLight(Color i0, Vector direction) {
		super(i0);
		_direction = direction.normalization();
	}
	
	// ***************** Getters/Setters ********************** //

	@Override
	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point3D p) {
		return _direction;
	}
	
	public double getDist(Point3D p) {
		return 1000;
	}
}