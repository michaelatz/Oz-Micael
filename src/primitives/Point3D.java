
package primitives;

/**
 * Class represents point in 3D space.
 * 
 * @author michael
 * @author oz
 */
public class Point3D {
	private Coordinate x;
	private Coordinate y;
	private Coordinate z;

	public static Point3D ZERO = new Point3D(Coordinate.ZERO, Coordinate.ZERO, Coordinate.ZERO);

	// ***************** Constructors ********************** //
	/**
	 * Constructor with Coordinates
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor with double
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */

	public Point3D(double x, double y, double z) {
		Coordinate c1 = new Coordinate(x);
		Coordinate c2 = new Coordinate(y);
		Coordinate c3 = new Coordinate(z);
		this.x = c1;
		this.y = c2;
		this.z = c3;
	}

	/**
	 * Copy constructor
	 * 
	 * @param point
	 */
	public Point3D(Point3D point) {
		this.x = new Coordinate(point.x);
		this.y = new Coordinate(point.y);
		this.z = new Coordinate(point.z);
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * X coordinate getter
	 * 
	 * @return the x
	 */

	public Coordinate getX() {
		return x;
	}

	/**
	 * Y coordinate getter
	 * 
	 * @return the y
	 */
	public Coordinate getY() {
		return y;
	}

	/**
	 * Z coordinate getter
	 * 
	 * @return the z
	 */
	public Coordinate getZ() {
		return z;
	}

	// ***************** Administration ******************** //
	@Override
	public String toString() {
		return ("x:" + x + " y:" + y + " z:" + z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D other = (Point3D) obj;
		return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
	}

	// ***************** Operations ******************** //
	/**
	 * Subtract function
	 * 
	 * @param other point
	 * @return new Vector
	 */
	public Vector subtract(Point3D other) {
		double x1 = this.x.get();
		double y1 = this.y.get();
		double z1 = this.z.get();
		double x2 = other.x.get();
		double y2 = other.y.get();
		double z2 = other.z.get();
		return new Vector(x1 - x2, y1 - y2, z1 - z2);
	}

	/**
	 * Adding function
	 * 
	 * @param other vector
	 * @return new Point3D
	 */
	public Point3D add(Vector other) {
		return new Point3D(this.getX().add(other.getHead().getX()), //
				this.getY().add(other.getHead().getY()), //
				this.getZ().add(other.getHead().getZ()));
	}

	/**
	 * Calculate the squared distance between 2 points
	 * 
	 * @param a Point3D parameter
	 * @return squared distance
	 */

	public double distance2(Point3D other) {
		double temp;
		double s;
		s = this.getX().subtract(other.getX()).get();
		temp = s * s;
		s = this.getY().subtract(other.getY()).get();
		temp = temp + s * s;
		s = this.getZ().subtract(other.getZ()).get();
		temp = temp + s * s;

		return temp;
	}

	/**
	 * Calculate the distance between 2 points
	 * 
	 * @param other
	 * @return distance
	 */

	public double distance(Point3D other) {
		return Math.sqrt(this.distance2(other));
	}
}
