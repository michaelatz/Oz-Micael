package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Class represents plane in 3D space
 * 
 * @author michael
 * @author oz
 */
public class Plane extends Geometry {
	private Point3D _p;
	private Vector _normal;

	// ***************** Constructors ********************** //
	/**
	 * construct a plane with 3 points
	 * 
	 * @param _p1
	 * @param _p2
	 * @param _p3
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		this(new Material(0, 0, 0), Color.BLACK, p1, p2, p3);
	}

	/**
	 * construct a plane with 3 points and color
	 * 
	 * @param _p1
	 * @param _p2
	 * @param _p3
	 * @param _color
	 */
	public Plane(Color color, Point3D p1, Point3D p2, Point3D p3) {
		this(new Material(0, 0, 0), color, p1, p2, p3);
	}

	/**
	 * constructor of 3 points, color and material
	 * 
	 * @param _p1
	 * @param _p2
	 * @param _p3
	 * @param _color
	 * @param material
	 */
	public Plane(Material material, Color color, Point3D p1, Point3D p2, Point3D p3) {
		super(material, color);
		this._p = p1;
		this._normal = p2.subtract(p1).crossProduct(p3.subtract(p1)).normal();
	}

	/**
	 * constructor with point and normal
	 * 
	 * @param p
	 * @param normal
	 */
	public Plane(Point3D p, Vector normal) {
		this._p = new Point3D(p);
		this._normal = new Vector(normal.normal());
	}

	/**
	 * constructor with point, normal and color
	 * 
	 * @param p
	 * @param normal
	 * @param color
	 */
	public Plane(Color color, Point3D p, Vector normal) {
		super(color);
		this._p = new Point3D(p);
		this._normal = new Vector(normal.normal());
	}

	/**
	 * construct a plane with point, normal, color and material
	 * 
	 * @param p
	 * @param normal
	 * @param color
	 * @param material
	 */
	public Plane(Material material, Color color, Point3D p, Vector normal) {
		super(material, color);
		this._p = new Point3D(p);
		this._normal = new Vector(normal.normalization());
	}

	// ***************** Operations ******************** //

	/**
	 * @param ray
	 * @return list of intersectionPoint findIntersections of a plane
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		Vector n = this.getNormal(_p);
		Point3D q0 = this._p;
		Point3D p0 = ray.getHead();
		Vector v = ray.getV();

		if ((isZero(p0.distance(q0)))) {
			return null;
		}

		Vector v1 = new Vector(q0.subtract(p0));// q0-p0
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0) // The ray is parallel to the plane
			return null;

		double numer = alignZero(n.dotProduct(v1));
		double t = alignZero(numer / nv); // if t<0 return null
		if (t > 0) {
			List<GeoPoint> intersectionPoint = new ArrayList<>();
			Plane plane = new Plane(q0, n);
			GeoPoint p = new GeoPoint(plane, p0.add(v.scale(t)));
			intersectionPoint.add(p);
			intersectionPoint.get(0).geometry = this;
			return intersectionPoint;
		}

		return null;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		return _normal;
	}
}