package elements;

import primitives.*;

public class PointLight extends Light {
	Point3D position;
	double KC, KL, KQ;
	
	public PointLight(Color _intensity, Point3D position, double kC, double kL, double kQ) {
		super(_intensity);
		this.position = position;
		KC = kC;
		KL = kL;
		KQ = kQ;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * position getter
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * @return the KC
	 */
	public double getKC() {
		return KC;
	}

	/**
	 * @return the KL
	 */
	public double getKL() {
		return KL;
	}

	/**
	 * @return the KQ
	 */
	public double getKQ() {
		return KQ;
	}
	/**
	 * @param KC
	 * @param KL
	 * @param KQ
	 */
	public void setK(double KQ,double KL,double KC) {
		this.KQ = KQ;
		this.KQ = KL;
		this.KQ = KC;
	}
	/**
	 * @param _kQ the _kQ to set
	 */
	public void setPosition(Point3D position) {
		this.position = position;
	}
	public Color getIntensity(Point3D p) {
		double d=this.position.distance(p);
		return getIntensity().scale(KC+KL*d+KQ*d*d);
	}
	
}
