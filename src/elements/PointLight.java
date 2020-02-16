package elements;

import primitives.*;

/**
 * class representing Point Light
 * 
 * @author michael
 * @author oz
 *
 */
public class PointLight extends Light implements LightSource {
	Point3D position;
	double kC, kL, kQ;

	// ***************** Constructors ********************** //

	/**
	 * PintLight constructor with intensity, position, kC, kL, kQ
	 * 
	 * @param intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
		super(intensity);
		this.position = position;
		this.kC = kC;
		this.kL = kL;
		this.kQ = kQ;
	}

	// ***************** Getters/Setters ********************** //
	
	/**
	 * getter of distance between light position and point
	 *
	 * @param p - the point
	 * @return the distance
	 */
	public double getDist(Point3D p) {
		return this.position.distance(p);
	}

	@Override
	public Color getIntensity(Point3D p) {
		double d2 = p.distance2(position);
		double d = Math.sqrt(d2);
		return intensity.reduce(kC + kL * d + kQ * d2);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(this.position).normal();
	}

}