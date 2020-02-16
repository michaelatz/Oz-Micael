package primitives;

/**
 * Class represents 3D space ray.
 * 
 * @author michael
 * @author oz
 *
 */
public class Ray {
	private Point3D head;
	private Vector v;

	// ***************** Constructors ********************** //
	/**
	 * Construct a ray with initial point and direction vector
	 * 
	 * @param v
	 * @param p
	 */
	public Ray(Vector v, Point3D p) {

		this.v = v.normalization();
		this.head = p;
	}
	
	// ***************** Getters/Setters ********************** //
	/**
	 * Ray head point getter
	 * 
	 * @return head
	 */
	public Point3D getHead() {
		return head;
	}

	/**
	 * Ray direction vector getter
	 * 
	 * @return v
	 */
	public Vector getV() {
		return v;
	}

	// ***************** Administration ******************** //
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof Ray))
			return false;
		Ray obj = (Ray) other;
		return v.equals(obj.getV()) && head.equals(obj.head);
	}

	@Override
	public String toString() {
		return "from " + head + " to " + v;
	}
}