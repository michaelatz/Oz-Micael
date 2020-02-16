package primitives;

/**
 * class representing material
 * 
 * @author michael
 * @author oz
 */
public class Material {
	double kD;
	double kS;
	int nShininess;
	double kR;
	double kT;

	// ***************** Constructors ********************** //

	/**
	 * constructs material with kd, ks and shininess
	 * 
	 * @param kD
	 * @param kS
	 * @param nShininess
	 */
	public Material(double kD, double kS, int nShininess) {
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;

	}

	/**
	 * constructs material with kd, ks, shininess, kr and kt
	 * 
	 * @param kD
	 * @param kS
	 * @param nShininess
	 * @param kR
	 * @param kT
	 */
	public Material(double kD, double kS, int nShininess, double kR, double kT) {
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;
		this.kR=kR;
		this.kT=kT;
	}
	
	// ***************** Getters/Setters ********************** //
	/**
	 * kd getter
	 * 
	 * @return the kD
	 */
	public double getkD() {
		return kD;
	}

	/**
	 * ks getter
	 * 
	 * @return the kS
	 */
	public double getkS() {
		return kS;
	}

	/**
	 * nShininess getter
	 * 
	 * @return the nShininess
	 */
	public int getNShininess() {
		return nShininess;
	}
	
	/**
	 * kr getter
	 * 
	 * @return the kD
	 */
	public double getKr() {
		return kR;
	}

	/**
	 * ks getter
	 * 
	 * @return the kT
	 */
	public double getKt() {
		return kT;
	}
}