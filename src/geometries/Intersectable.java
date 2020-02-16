package geometries;

import java.util.List;
import primitives.*;

/**
 * Interface representing intersection geo point
 * 
 * @author michael
 * @author oz
 *
 */

public interface Intersectable {

	static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		// ***************** Constructors ********************** //

		/**
		 * Constructs a GePoint with a geometry and point
		 * 
		 * @param geometry
		 * @param point
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.geometry = geometry;
			this.point = point;
		}
		
		// ***************** Getters/Setters ********************** //
		
		/**
		 * @return the geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}


		/**
		 * @return the point
		 */
		public Point3D getPoint() {
			return point;
		}


		// ***************** Administration ******************** //

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;

			GeoPoint other = (GeoPoint) obj;

			return point.equals(other.point);
		}

		
		
	}

	// ***************** Operations ******************** //

	/**
	 * Finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point with its geometry
	 */
	List<GeoPoint> findIntersections(Ray ray);
}