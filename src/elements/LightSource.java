package elements;

import primitives.*;

/**
 * class representing light source
 * 
 * @author michael
 * @author oz
 *
 */
public interface LightSource {

	// ***************** Getters/Setters ********************** //

	/**
	 * Intensity getter
	 * 
	 * @param p
	 * @return color
	 */
	Color getIntensity(Point3D p);

	/**
	 * vector between location(of camera) to the point 
	 * 
	 * @param p
	 * @return vector
	 */
	Vector getL(Point3D p);
	/**
	 * getter of distance between light position and point
	 *
	 * @param p - the point
	 * @return the distance
	 */
	double getDist(Point3D p);

}