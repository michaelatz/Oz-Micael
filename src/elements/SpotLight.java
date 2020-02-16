package elements;

import primitives.*;
import static primitives.Util.*;

/**
 * class representing Spot Light
 * 
 * @author michael
 * @author oz
 *
 */
public class SpotLight extends PointLight {
	Vector dir;

	// ***************** Constructors ********************** //

	/**
	 * PointLight constructor with intensity, position, kC, kL, kQ and direction
	 * 
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param dir
	 */
	public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector dir) {
		super(intensity, position, kC, kL, kQ);
		this.dir = dir.normal();
	}

	// ***************** Getters/Setters ********************** //


	public Color getIntensity(Point3D p) {
		double cos = dir.dotProduct(getL(p));
		if (cos <= 0) // if the point is behind the light source (90 degrees or more)
			return Color.BLACK;
		return super.getIntensity(p).scale(cos);
	}
}
