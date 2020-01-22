
package primitives;

/**
 * Class representing Cartesian 3D space vector
 * 
 * @author michael
 * @author oz
 */
public class Vector {
	private Point3D head;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a vector with head point
	 * 
	 * @param head point of the vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Point3D head) {
		if (head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = head;
	}

	/**
	 * Constructs a vector by values of three coordinates of vector's head
	 * 
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(double x, double y, double z) {
		this.head = new Point3D(x, y, z);
		if (this.head.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
	}

	/**
	 * Constructs a vector with three coordinates.
	 * 
	 * @param x coordinate of head point
	 * @param y coordinate of head point
	 * @param z coordinate of head point
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D headPoint = new Point3D(x, y, z);
		if (headPoint.equals(Point3D.ZERO))
			throw new IllegalArgumentException("Zero Vector");
		this.head = headPoint;
	}

	/**
	 * Construct head point of vector class with a vector
	 * 
	 * @param other vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Vector other) {
		this.head = new Point3D(other.head);
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Vector head point getter
	 * 
	 * @return the head point
	 */
	public Point3D getHead() {
		return head;
	}

	// ***************** Administration ******************** //

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;

		return head.equals(other.head);
	}

	@Override
	public String toString() {
		return "->" + head;
	}

	// ***************** Operations ******************** //
	/**
	 * Adding function
	 * 
	 * @param other vector
	 * @return Vector
	 */
	public Vector add(Vector other) {
		Point3D temp = new Point3D(this.head.getX().add(other.getHead().getX()), //
				this.head.getY().add(other.getHead().getY()), //
				this.head.getZ().add(other.getHead().getZ())); //
		return new Vector(temp);
	}

	/**
	 * Subtraction function
	 * 
	 * @param other vector
	 * @return Vector
	 */
	public Vector subtract(Vector other) {
		Point3D temp = new Point3D(this.head.getX().subtract(other.getHead().getX()), //
				this.head.getY().subtract(other.getHead().getY()), //
				this.head.getZ().subtract(other.getHead().getZ())); //
		return new Vector(temp);
	}

	/**
	 * Scalar multiplication
	 * 
	 * @param num
	 * @return Scaled vector by num
	 */
	public Vector scale(double num) {
		Point3D temp = new Point3D(this.head.getX().scale(num), //
				this.head.getY().scale(num), //
				this.head.getZ().scale(num)); //
		return new Vector(temp); 
	}

	/**
	 * Calculate dot product between the vector and other vector
	 * 
	 * @param other vector
	 * @return dot product result
	 */

	public double dotProduct(Vector other) {
		double x1 = this.head.getX().get();
		double y1 = this.head.getY().get();
		double z1 = this.head.getZ().get();

		double x2 = other.head.getX().get();
		double y2 = other.head.getY().get();
		double z2 = other.head.getZ().get();

		return x1 * x2 + y1 * y2 + z1 * z2;
	}

	/**
	 * cross product multiplication
	 * 
	 * @param other vector
	 * @return vector orthogonal to each one of the two vectors.
	 */

	public Vector crossProduct(Vector other) {
		double x1 = this.head.getX().get();
		double y1 = this.head.getY().get();
		double z1 = this.head.getZ().get();

		double x2 = other.head.getX().get();
		double y2 = other.head.getY().get();
		double z2 = other.head.getZ().get();

		return new Vector(y1 * z2 - y2 * z1, z1 * x2 - z2 * x1, x1 * y2 - x2 * y1);
	}

	/**
	 * Calculate squared length of the vector
	 * 
	 * @return the squared length
	 */

	public double length2() {
		double x = this.head.getX().get();
		double y = this.head.getY().get();
		double z = this.head.getZ().get();

		return x * x + y * y + z * z;
	}

	/**
	 * Calculate the length of the vector
	 * 
	 * @return the length
	 */
	public double length() {
		return Math.sqrt(length2());
	}

	/**
	 * Makes the vector to be of length 1 keeping the direction
	 * 
	 * @return normalized vector itself
	 */

	public Vector normal() {
		double l = length();
		double x = this.head.getX().get();
		double y = this.head.getY().get();
		double z = this.head.getZ().get();
		this.head = new Point3D(x / l, y / l, z / l);
		return this;
	}

	/**
	 * Builds new vector of length 1 in the same direction
	 * 
	 * @return normalized vector
	 */
	public Vector normalization() {
		return new Vector(this).normal();
	}

}
