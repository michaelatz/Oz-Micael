
//public class SphereTest {

	/*@Test
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 
	public void getNormaltest() {
		Sphere sph = new Sphere(3, new Point3D(0, 0, 0));
		assertEquals("Find intersection function error", new Vector(1, 0, 0), sph.getNormal(new Point3D(3, 0, 0)));
	}*/
	/*
	@Test
	public void findIntersectionsTest() {
		Sphere sph = new Sphere(1.0, new Point3D(0.0, 0.0, 0.0));
		// ray intersects the sphere(tow points) 1
		Ray ray = new Ray(new Vector(1, 1, 0), new Point3D(-2, -1, 0));
		List<GeoPoint> expected = Arrays.asList(new GeoPoint(sph,new Point3D(0, 1, 0)), new GeoPoint(sph,new Point3D(-1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray isn't intersects the sphere 2
		ray = new Ray(new Vector(1, 2, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(one point-starts on the sphere, pass in he center)
		// 3
		ray = new Ray(new Vector(1, 0, 0), new Point3D(-1, 0, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray isn't intersects the sphere(Tangent) 4
		ray = new Ray(new Vector(0, 1, 0), new Point3D(-1, -1, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray isn't intersects the sphere(Tangent-ray starts on the sphere) 5
		ray = new Ray(new Vector(0, 1, 0), new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray isn't intersects the sphere(orthogonal to the ray from the center-ray
		// starts on that ray) 6
		ray = new Ray(new Vector(0, 1, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(one point-starts in the center of the sphere) 7
		ray = new Ray(new Vector(1, 0, 0), new Point3D(0, 0, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere-starts behind the sphere(pass the center)(tow
		// points) 8
		ray = new Ray(new Vector(1, 0, 0), new Point3D(-2, 0, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(1, 0, 0)), new GeoPoint(sph,new Point3D(-1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(tow points?) -ray starts in the opposite direction
		// 9
		ray = new Ray(new Vector(-3, -1, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(one point) -ray starts inside! the sphere 10
		ray = new Ray(new Vector(3, 1, 0), new Point3D(-0.5, 0.5, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(0.534846922835, 0.8449489742783, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(one point) -ray starts on! the sphere 11
		ray = new Ray(new Vector(3, 1, 0), new Point3D(-0.934846922835, 0.3550510257217, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(0.534846922835, 0.8449489742783, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(one point) -ray starts on! the sphere, ray is in
		// the opposite side 12
		ray = new Ray(new Vector(-3, -1, 0), new Point3D(-0.934846922835, 0.3550510257217, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray isn't intersects the sphere(Tangent-ray starts after the sphere) 13
		ray = new Ray(new Vector(0, 1, 0), new Point3D(-1, 1, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray isn't intersects the sphere(one point-starts on the sphere, pass in he
		// center-opposite side) 14
		ray = new Ray(new Vector(-1, 0, 0), new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray isn't intersects the sphere(ray starts after the sphere- the opposite
		// side of the ray passed the center) 15
		ray = new Ray(new Vector(-1, 0, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(ray starts in the sphere- the opposite side of the
		// ray passed the center) 16
		ray = new Ray(new Vector(-1, 0, 0), new Point3D(-0.5, 0, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(-1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(ray starts in the sphere- ray passed the center) 17
		ray = new Ray(new Vector(-1, 0, 0), new Point3D(0.5, 0, 0));
		expected = Arrays.asList(new GeoPoint(sph,new Point3D(-1, 0, 0)));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
	}
	*/
	package unittests;

	import static org.junit.Assert.*;
	import static geometries.Intersectable.GeoPoint;

	import geometries.*;
	import primitives.*;
	import org.junit.Test;

	import java.util.Arrays;
	import java.util.List;

	/**
	 * Test class of Sphere {@link geometries.Sphere}
	 */
	public class SphereTest {
		/**
		 * test Method for {@link geometries.Sphere#getNormal (geomtries.Sphere)}
		 */
		@Test
		public void testGetNormal() {
			Point3D center = new Point3D(0, 0, 0);
			Sphere sphere = new Sphere(2, center);
			Point3D p = new Point3D(0, 2, 0);
			Vector actual = new Vector(0, 1, 0);
			assertEquals("Get normal function error", sphere.getNormal(p), actual);
		}

		/**
		 * test Method for
		 * {@link geometries.Sphere#findIntersections (geomtries.Sphere)}
		 */
		@Test
		public void testFindIntersections() {
			Sphere sphere = new Sphere(2, new Point3D(0, 0, 0)); // x^2 + y^2 + z^2 = 4

			/****************** EP ******************/

			// ray does not intersect with the sphere
			assertEquals("Sphere findIntersection error : error shouldn't intersec ", null,
					sphere.findIntersections(new Ray(new Vector(-4, -4, 0), new Point3D(-4, 0, 0))));
			// ray intersects with the sphere at two points
			double x = 1.4142135623731;
			List<GeoPoint> intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 0, 2)),
					new GeoPoint(sphere, new Point3D(-2, 0, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(2, 0, 2), new Point3D(-3, 0, -1))));
			// ray intersects with the sphere at one point, while p0 is in the sphere
			x = 1.0696938456699;
			double y = 1.6898979485566;
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(x, y, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(3, 1, 0), new Point3D(-1, 1, 0))));
			// ray does not intersect with the sphere but the line have two intersection
			// points
			assertEquals("Sphere findIntersection error : error shouldn't intersect ", null,
					sphere.findIntersections(new Ray(new Vector(-2, 0, -2), new Point3D(-3, 0, -1))));

			/****************** BVA ******************/

			/******* BVA case 1 *******/
			// P0 is on the sphere and the ray points to the outside
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(-2, 0, 0))));
			// P0 is outside and there is no intersection.
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(-3, 0, 0))));
			// the ray is from the center of the sphere
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(0, 0, 0))));
			// the ray is from outside the sphere to the other side of the sphere by the
			// diameter
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)),new GeoPoint(sphere,  new Point3D(2, 0, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(3, 0, 0))));
			// the ray is from the sphere to the other side of the sphere by the diameter
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(2, 0, 0))));
			// the ray is from the inside of the sphere by the diameter
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point3D(1, 0, 0))));

			/******* BVA case 2 *******/
			// the ray is from the sphere to the other side of the sphere
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
			assertEquals("Sphere findIntersection error ", intersections,
					sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point3D(-2, 0, 0))));
			// ray is from the sphere
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point3D(0, 2, 0))));

			/******* BVA case 3 *******/
			// ray is tangent to the sphere
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(-2, 2, 0))));
			// ray is from the tangent to the sphere
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(0, 2, 0))));
			// ray is on the tangent line of the sphere
			intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(1, 2, 0))));

			/******* BVA case 4 *******/
			// ray is orthogonal to the radius but from outside the sphere
			assertEquals("Sphere findIntersection error ", null,
					sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point3D(0, 3, 0))));
		}
	}

