package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Class represents a sphere in 3D Cartesian space
 * 
 * @author michael
 * @author oz
 */
public class Sphere extends RadialGeometry {
	Point3D _center;

	// ***************** Constructors ********************** //
	/**
	 * Constructor with radius and center point
	 * 
	 * @param _radius (of the sphere)
	 * @param _center (of the sphere)
	 */
	public Sphere(double _radius, Point3D _center) {
		super(_radius);
		this._center = _center;
	}

	/**
	 * Constructor with radius, center point and color
	 * 
	 * @param _radius (of the sphere)
	 * @param _center (of the sphere)
	 * @param color
	 */
	public Sphere(Color color, double _radius, Point3D _center) {
		super(color, _radius);
		this._center = _center;
	}

	/**
	 * Constructor with radius, center point and color and material
	 * 
	 * @param _radius  (of the sphere)
	 * @param _center  (of the sphere)
	 * @param color
	 * @param material
	 */
	public Sphere(Material material, Color color, double _radius, Point3D _center) {
		super(material, color, _radius);
		this._center = _center;
	}

	// ***************** Operations ******************** //
	/**
	 * get normal function
	 * 
	 * @param point p
	 * @return vector normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return p.subtract(_center).normal();// the normal vector
	}

	/**
	 * find the Intersections point of the sphere
	 * 
	 * @param ray
	 * @return list of intersectionPoint
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersectionPoint = null;
		Point3D o = this._center;
		Point3D p0 = ray.getHead();
		Vector v = ray.getV();
		if (o.equals(p0)) {
			intersectionPoint = new ArrayList<>();
			GeoPoint p = new GeoPoint(this, p0.add(v.scale(this._radius)));
			intersectionPoint.add(p);
			return intersectionPoint;
		}
		Vector u = o.subtract(p0);// vector from p0 to the center
		double tm = alignZero(v.dotProduct(u));// from p0, orthogonal to d
		double d = Math.sqrt(u.length2() - (tm * tm));// from the center to the ray

		// from the cross point of d and tm until P1(the intersection point)
		double th = alignZero(Math.sqrt(this._radius * this._radius - d * d));
		if (th == 0)
			return null;

		double t1 = alignZero(tm + th);// p1's scalar
		double t2 = alignZero(tm - th);// p2's scalar
		if (t1 > 0) {// t>0
			intersectionPoint = new ArrayList<>();
			GeoPoint p1 = new GeoPoint(this, p0.add(v.scale(t1)));
			intersectionPoint.add(p1);
		}
		if (t2 > 0) {// t>=0
			if (intersectionPoint == null)
				intersectionPoint = new ArrayList<>();
			GeoPoint p2 = new GeoPoint(this, p0.add(v.scale(t2)));
			intersectionPoint.add(p2);
		}
		return intersectionPoint;
	}
}