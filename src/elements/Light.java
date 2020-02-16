package elements;

import primitives.*;

/**
 * class representing abstract light
 * 
 * @author michael
 * @author oz
 */
public abstract class Light {
	Color intensity;

	// ***************** Constructors ********************** //

	/**
	 * DirectionaLight constructor with color and vector
	 * 
	 * @param i0
	 * @param direction
	 */
	public Light(Color intensity) {
		this.intensity = intensity;
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * intensity getter
	 * 
	 * @return the _intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}