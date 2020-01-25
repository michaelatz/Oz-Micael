package geometries;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;
/**
 * Class represents plane in 3D space
 * @author michael
 *@author oz
 */
public class Plane extends Geometry {
	private Point3D _p;
	private Vector _normal;

	// ***************** Constructors ********************** //
	/**
	 * constructor of 3 points
	 * 
	 * @param _p1
	 * @param _p2
	 * @param _p3
	 */
	public Plane(Point3D _p1, Point3D _p2, Point3D _p3) {
		this._p = _p1;
		this._normal = _p2.subtract(_p1).crossProduct(_p3.subtract(_p1));
	}
	
	/**
	 * constructor of 3 points and color
	 * 
	 * @param _p1
	 * @param _p2
	 * @param _p3
	 * @param _color
	 */
	public Plane(Color color, Point3D _p1, Point3D _p2, Point3D _p3) {
		setEmmission(color);
		this._p = _p1;
		this._normal = _p2.subtract(_p1).crossProduct(_p3.subtract(_p1));
	}


	/**
	 * constructor with point and normal
	 * 
	 * @param _p
	 * @param _normal
	 */
	public Plane(Point3D _p, Vector _normal) {
		this._p = new Point3D(_p);
		this._normal = new Vector(_normal.normalization());
	}
	
	/**
	 * constructor with point, normal and color
	 * 
	 * @param _p
	 * @param _normal
	 * @param color
	 */
	public Plane(Color color, Point3D _p, Vector _normal) {
		setEmmission(color);
		this._p = new Point3D(_p);
		this._normal = new Vector(_normal.normalization());
	}


	// ***************** Operations ******************** //
	/**
	 * getNormal of a plane: The normal vector returns
	 * 
	 * @return normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		return _normal;
	}

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
			Plane plane=new Plane(q0, n);
			GeoPoint p=new GeoPoint(plane, p0.add(v.scale(t)));
			intersectionPoint.add(p);
			intersectionPoint.get(0).geometry = this;
			return intersectionPoint;
		}

		return null;
	}
}
