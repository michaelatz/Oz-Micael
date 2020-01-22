package primitives;

public class Material {
	double kD;
	double kS;
	int nShininess;

	public Material(double kD, double kS, int nShininess) {
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;

	}

	/**
	 * @return the kD
	 */
	public double getkD() {
		return kD;
	}

	/**
	 * @return the kS
	 */
	public double getkS() {
		return kS;
	}

	/**
	 * @return the nShininess
	 */
	public int getnShininess() {
		return nShininess;
	}
}
