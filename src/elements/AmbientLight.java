package elements;

import primitives.Color;

/**
 * class representing Ambient Light
 * 
 * @author michael
 * @author oz
 */
public class AmbientLight extends Light {
	double ka;

//***************** Constructors ********************** //
	/**
	 * AmbientLight constructor with color and double
	 * 
	 * @param intensity
	 * @param Ka
	 */
	public AmbientLight(Color intensity, double ka) {
		super(intensity.scale(ka));
		this.ka = ka;
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * getter for the ambient lighting
	 *
	 * @return light intensity
	 */
	public Color getIntensity() {
		return intensity;
	}
}