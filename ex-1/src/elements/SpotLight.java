package elements;

import primitives.*;

public class SpotLight extends PointLight {
	Vector dir;

	/**
	 * @param _intensity
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param dir
	 */
	public SpotLight(Color _intensity, Point3D position, double kC, double kL, double kQ, Vector dir) {
		super(_intensity, position, kC, kL, kQ);
		this.dir = dir;
	}

	public Color getIntensity(Point3D p) {
		double d = position.distance(p);
		Vector l = p.subtract(position);
		if (d == 0 || dir.dotProduct(l) < 0) {
			return getIntensity().scale(0);
		}
		return getIntensity().scale(dir.dotProduct(l) / (KC + KL * d + KQ * d * d));

	}
}
