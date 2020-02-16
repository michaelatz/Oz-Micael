package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Test class of Polygon {@link geometries.Polygon}}
 */
public class PolygonTest {
	static Point3D p1 = new Point3D(1, -1, 0);
	static Point3D p2 = new Point3D(1, 1, 0);
	static Point3D p3 = new Point3D(-1, 1, 0);
	static Point3D p4 = new Point3D(-1, -1, 0);
	static Polygon polygon = new Polygon(p1, p2, p3, p4);

	/**
	 * test Method for {@link geometries.Polygon (geomtries.Polygon)}
	 */
	@Test
	public void testConstructor() {
		Point3D p1 = new Point3D(0, 0, 0);
		Point3D p2 = new Point3D(1, 0, 0);
		Point3D p3 = new Point3D(1, 1, 0);
		// ****** EP ******//
		try {
			// Test of the polygon constructor
			Point3D p4 = new Point3D(0, 1, 0);
			new Polygon(p1, p2, p3, p4);
			assertTrue(true);
		} catch (IllegalArgumentException e) {
			fail("threw an unwanted exception");
		}
		// ****** BVA ******//
		try {
			Point3D p4 = new Point3D(0, 1, 3);
			new Polygon(p1, p2, p3, p4);
			// BVA constructing a polygon with a point not on the same plane as the others
			fail("didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			Point3D p4 = new Point3D(0, 1, -3);
			new Polygon(p1, p2, p3, p4);
			// BVA constructing a polygon with a point not on the same plane as the others
			fail("didn't throw exception");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * test Method for
	 * {@link geometries.Polygon#findIntersections (geomtries.Polygon)}
	 */
	@Test
	public void testFindIntersections() {
		Vector vec = new Vector(0, 0, 1);
		/**
		 * EP case
		 */
		// EP ray to a point inside the polygon
		Ray ray = new Ray(vec, new Point3D(0, 0, -1));
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint(polygon, new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", intersections, polygon.findIntersections(ray));
		// EP ray to a point outside the polygon between axis's of two sides near the
		// side
		ray = new Ray(vec, new Point3D(2, 0, -1));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// EP ray to a point outside the polygon between axis's of two sides near the
		// vertex
		ray = new Ray(vec, new Point3D(2, 2, -1));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		/**
		 * BVA case 1
		 */
		// BVA ray from a point inside the polygon
		ray = new Ray(vec, new Point3D(0, 0, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray from a point outside the polygon between axis's of two sides near
		// the side
		ray = new Ray(vec, new Point3D(2, 0, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray from a point outside the polygon between axis's of two sides near
		// the vertex
		ray = new Ray(vec, new Point3D(2, 2, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		/**
		 * BVA case 2
		 */
		// BVA ray to the vertex of the polygon
		ray = new Ray(vec, new Point3D(1, 1, -1));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray to a point outside the polygon on the side axis of the polygon
		ray = new Ray(vec, new Point3D(1, 0, -1));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray to a point on the side of the polygon
		ray = new Ray(vec, new Point3D(2, 1, -1));
		intersections = Arrays.asList(new GeoPoint(polygon, new Point3D(1, 0, 0)));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		/**
		 * BVA case 3
		 */
		// BVA ray from the vertex of the polygon
		ray = new Ray(vec, new Point3D(1, 1, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray from a point outside the polygon on the side axis of the polygon
		ray = new Ray(vec, new Point3D(1, 0, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
		// BVA ray from a point on the side of the polygon
		ray = new Ray(vec, new Point3D(2, 1, 0));
		assertEquals("Find intersection function error", null, polygon.findIntersections(ray));
	}
}