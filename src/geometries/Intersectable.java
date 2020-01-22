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
		
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
		
	}
	
	

	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<GeoPoint> findIntersections(Ray ray);
}
