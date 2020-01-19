package elements;

import primitives.Color;

/**
 * @author michael
 *@author oz
 */
public class AmbientLight {
Color intensity;
//***************** Constructors ********************** //
	/**
	 * AmbientLight constructor with color and double
	 * @param Ia
	 * @param Ka
	 */
	public AmbientLight(Color Ia, double Ka) {
		this.intensity=Ia.scale(Ka);
	}
	/**
	 * AmbientLight constructor with color (intensity)
	 * @param intensity
	 */
	public AmbientLight(Color intensity) {
		this.intensity = new Color(intensity);
	}
	// ***************** Getters/Setters ********************** //
	/**
	 * intensity getter
	 * @return 
	 */
	public Color getIntensity() {
		return intensity;
	}
	
}