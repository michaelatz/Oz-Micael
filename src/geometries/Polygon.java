package geometries;

import java.util.List;
import java.util.Arrays;
import static primitives.Util.*;
import primitives.*;

/**
 * class representing polygon in 3D space
 * 
 * @author michael
 * @author oz
 *
 */
public class Polygon extends Geometry {
	protected List<Point3D> _points;
	protected Plane _plane;

	// ***************** Constructors ********************** //
	/**
	 * Constructs a polygon with points
	 * 
	 * @param points
	 * @throws IllegalArgumentException when the points are not on the same plane
	 */
	public Polygon(Point3D... points) {
		super();
		if (points.length < 3)
			throw new IllegalArgumentException("Polygon must have at least 3 vertices");
		Point3D p1 = points[0];
		Point3D p2 = points[1];
		Point3D p3 = points[2];
		_plane = new Plane(p1, p2, p3);
		Vector n = _plane.getNormal(p1);
		for (int i = 3; i < points.length; i++)
			if (!isZero(p1.subtract(points[i]).dotProduct(n)))
				throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
		_points = Arrays.asList(points);
	}

	/**
	 * Constructs a polygon with points and color
	 * 
	 * @param points
	 * @param color
	 * @throws IllegalArgumentException when the points are not on the same plane
	 */
	public Polygon(Color color, Point3D... points) {
		super(color);
		if (points.length < 3)
			throw new IllegalArgumentException("Polygon must have at least 3 vertices");
		Point3D p1 = points[0];
		Point3D p2 = points[1];
		Point3D p3 = points[2];
		_plane = new Plane(p1, p2, p3);
		Vector n = _plane.getNormal(p1);
		for (int i = 3; i < points.length; i++)
			if (!isZero(p1.subtract(points[i]).dotProduct(n)))
				throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
		_points = Arrays.asList(points);
	}

	/**
	 * Constructs a polygon with points and color and material
	 * 
	 * @param points
	 * @param color
	 * @param material
	 * @throws IllegalArgumentException when the points are not on the same plane
	 */
	public Polygon(Material material, Color color, Point3D... points) {
		super(material, color);
		if (points.length < 3)
			throw new IllegalArgumentException("Polygon must have at least 3 vertices");
		Point3D p1 = points[0];
		Point3D p2 = points[1];
		Point3D p3 = points[2];
		_plane = new Plane(p1, p2, p3);
		Vector n = _plane.getNormal(p1);
		for (int i = 3; i < points.length; i++)
			if (!isZero(p1.subtract(points[i]).dotProduct(n)))
				throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
		_points = Arrays.asList(points);
	}

	// ***************** Operation ******************** //
	/**
	 * finds intersections of the ray with the polygon
	 *
	 * @param ray
	 * @return intersection point
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = this._plane.findIntersections(ray);
		if (this._plane.findIntersections(ray) == null) // if there are no intersections with the plane, or the ray's
			// base is on the triangle return null
			return null;
		Point3D p0 = ray.getHead();
		int size = this._points.size();
		Vector[] v = new Vector[size];
		Vector[] N = new Vector[size];
		for (int i = 0; i < size; ++i)
			v[i] = _points.get(i).subtract(p0);
		for (int i = 0; i < size; ++i) {
			if (i < size - 1)
				N[i] = v[i].crossProduct(v[i + 1]).normalization();
			if (i == size - 1)
				N[i] = v[i].crossProduct(v[0]).normalization();
		}
		Vector p = intersections.get(0).point.subtract(p0);
		for (int i = 0; i < size - 1; ++i) {
			if (!((alignZero(p.dotProduct(N[i])) > 0 && alignZero(p.dotProduct(N[i + 1])) > 0)
					|| (alignZero(p.dotProduct(N[i])) < 0 && alignZero(p.dotProduct(N[i + 1])) < 0))) {
				return null;
			}
		}
		intersections.get(0).geometry = this;
		return intersections;
	}

	@Override
	public Vector getNormal(Point3D p) {
		return _plane.getNormal(p);
	}
}