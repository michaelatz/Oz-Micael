package geometries;

import java.util.*;
import primitives.Ray;

/**
 * Interface representing a list of shapes in 3D space
 * 
 * @author michael
 * @author oz
 *
 */
public class Geometries implements Intersectable {
	List<Intersectable> shapes = new ArrayList<Intersectable>();

	// ***************** Constructors ********************** //

	/**
	 * Default constructor
	 */
	public Geometries() {
		shapes = new ArrayList<Intersectable>();
	}

	/**
	 * constructs a list with geometries (one or more)
	 *
	 * @param list of geometry shapes.
	 */
	public Geometries(Intersectable... geometries) {
		for (Intersectable geo : geometries)
			shapes.add(geo);
	}
	// ***************** Operations ******************** //

	/**
	 * adds geometries to the list
	 *
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for (Intersectable shape : geometries)
			shapes.add(shape);
	}

	/**
	 * finds intersections of the ray with the geometries that are in the list
	 *
	 * @param ray in space.
	 * @return intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = null;
		for (Intersectable shape : shapes) {
			List<GeoPoint> ints = shape.findIntersections(ray);
			if (ints != null) {
				if (intersections == null)
					intersections = new ArrayList<>();
				intersections.addAll(ints);
			}
		}
		return intersections;
	}

}



