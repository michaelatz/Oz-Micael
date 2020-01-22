package elements;

import primitives.*;

public class DirectionaLight extends Light {
	Vector Direction;
	/**
	 * DirectionaLight constructor with color and vector
	 * @param i0
	 * @param direction
	 */
	public DirectionaLight(Color i0, Vector direction) {
		super(i0);
		Direction = direction;
	}
	// ***************** Getters/Setters ********************** //
	/**
	 * I0 getter
	 * @return I0
	 */
	public Color getIntensity() {
		return this.getIntensity();
	}
	/**
	 * Direction getter
	 * @return Direction
	 */
	public Vector getDirection() {
		return Direction;
	}
}
