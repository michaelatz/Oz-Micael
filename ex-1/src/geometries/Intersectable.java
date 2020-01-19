package geometries;

import java.util.List;
import primitives.*;

/**
 * Interface representing
 * 
 * @author michael
 * @author oz
 *
 */

public interface Intersectable {
	static class GeoPoint {
		public Geometry geometry;
		public Point3D point;
	}
	

	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<GeoPoint> findIntersections(Ray ray);//problema
}
