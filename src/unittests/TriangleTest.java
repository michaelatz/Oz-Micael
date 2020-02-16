package unittests;

import static org.junit.Assert.*;

import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TriangleTest {
	static Point3D p1 = new Point3D(0, 0, 0);
	static Point3D p2 = new Point3D(0, 3, 0);
	static Point3D p3 = new Point3D(4, 0, 0);
	static Triangle triangle = new Triangle(p1, p2, p3);

	@Test
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest() {
		try {
			Point3D p1 = new Point3D(1.0, 0.0, 0.0);
			Point3D p2 = new Point3D(1.0, 1.0, 1.0);
			Point3D p3 = new Point3D(0.0, 1.0, 1.0);
			Plane plane = new Plane(p1, p2, p3);
			Vector actual = plane.getNormal(p1);
			Vector expected = new Vector(0.0, -1.0, 1.0).normal();
			Assert.assertEquals(expected, actual);
		} catch (Exception e) {
			Assert.fail("getNormaltest unxpected exception:" + e);
		}
	}

	/**
	 * test Method for
	 * {@link geometries.Polygon#findIntersections (geomtries.Polygon)}
	 */
	@Test
	public void testFindIntersections() {
		/**
		 * EP case
		 */
		// EP ray to a point inside the triangle
		Ray ray = new Ray(new Vector(-1, -1, 1), new Point3D(2, 2, -1));
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 1, 0)));
		assertEquals("Find intersection function error", intersections, triangle.findIntersections(ray));
		// EP ray to a point outside the triangle between axis's of two sides near the
		// side
		ray = new Ray(new Vector(0, 1, 2), new Point3D(1, -2, -1));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// EP ray to a point outside the triangle between axis's of two sides near the
		// vertex
		ray = new Ray(new Vector(2, 2, 2), new Point3D(-2, -2, -1));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		/**
		 * BVA case 1
		 */
		// BVA ray from a point inside the triangle
		ray = new Ray(new Vector(1, -1, -1), new Point3D(1, 1, 0));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 1, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray from a point outside the triangle between axis's of two sides near
		// the side
		ray = new Ray(new Vector(0, 2, 1), new Point3D(1, -2, 0));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray from a point outside the triangle between axis's of two sides near
		// the vertex
		ray = new Ray(new Vector(1, 1, 1), new Point3D(-1, -1, 0));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		/**
		 * BVA case 2
		 */
		// BVA ray to the vertex of the triangle
		ray = new Ray(new Vector(-1, -1, 1), new Point3D(1, 1, -1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray to a point outside the triangle on the side axis of the triangle
		ray = new Ray(new Vector(1, 0, 3), new Point3D(-2, 0, -2));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray to a point on the side of the triangle
		ray = new Ray(new Vector(-1, 0, 1), new Point3D(2, 0, -1));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(1, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		/**
		 * BVA case 3
		 */
		// BVA ray from the vertex of the triangle
		ray = new Ray(new Vector(2, 1, 1), new Point3D(0, 0, 0));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray from a point outside the triangle on the side axis of the triangle
		ray = new Ray(new Vector(0, -1, 2), new Point3D(0, -1, 0));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
		// BVA ray from a point on the side of the triangle
		ray = new Ray(new Vector(0, 2, 1), new Point3D(0, 2, 0));
		intersections = Arrays.asList(new GeoPoint(triangle, new Point3D(0, 2, 0)));
		assertEquals("Find intersection function error", null, triangle.findIntersections(ray));
	}
}