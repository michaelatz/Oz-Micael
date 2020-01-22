package elements;

import primitives.Color;

/**
 * @author michael
 *@author oz
 */
public class AmbientLight extends Light {
double ka;
//***************** Constructors ********************** //
	/**
	 * AmbientLight constructor with color and double
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight(Color _intensity, double ka) {
		super(_intensity);
		this.ka = ka;
	}
	/**
	 * AmbientLight constructor with color (intensity)
	 * @param intensity
	 */
	public AmbientLight(Color intensity) {
		super(intensity);	}
	// ***************** Getters/Setters ********************** //
	/**
	 * intensity getter
	 * @return 
	 */
	public Color getIntensity() {
		return this.getIntensity().scale(ka);
	}
	
}