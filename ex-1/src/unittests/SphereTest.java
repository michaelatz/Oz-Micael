package unittests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import geometries.*;
import primitives.*;

public class SphereTest {

	@Test
	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest() {
		Sphere sph = new Sphere(3, new Point3D(0, 0, 0));
		assertEquals("Find intersection function error", new Vector(1, 0, 0), sph.getNormal(new Point3D(3, 0, 0)));
	}

	@Test
	public void findIntersectionsTest() {
		Sphere sph = new Sphere(1.0, new Point3D(0.0, 0.0, 0.0));
		// ray intersects the sphere(tow points) 1
		Ray ray = new Ray(new Vector(1, 1, 0), new Point3D(-2, -1, 0));
		List<Point3D> expected = Arrays.asList(new Point3D(0, 1, 0), new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray isn't intersects the sphere 2
		ray = new Ray(new Vector(1, 2, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(one point-starts on the sphere, pass in he center)
		// 3
		ray = new Ray(new Vector(1, 0, 0), new Point3D(-1, 0, 0));
		expected = Arrays.asList(new Point3D(1, 0, 0));
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
		expected = Arrays.asList(new Point3D(1, 0, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere-starts behind the sphere(pass the center)(tow
		// points) 8
		ray = new Ray(new Vector(1, 0, 0), new Point3D(-2, 0, 0));
		expected = Arrays.asList(new Point3D(1, 0, 0), new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(tow points?) -ray starts in the opposite direction
		// 9
		ray = new Ray(new Vector(-3, -1, 0), new Point3D(-2, 0, 0));
		assertEquals("Find intersection function error", null, sph.findIntersections(ray));
		// ray intersects the sphere(one point) -ray starts inside! the sphere 10
		ray = new Ray(new Vector(3, 1, 0), new Point3D(-0.5, 0.5, 0));
		expected = Arrays.asList(new Point3D(0.534846922835, 0.8449489742783, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(one point) -ray starts on! the sphere 11
		ray = new Ray(new Vector(3, 1, 0), new Point3D(-0.934846922835, 0.3550510257217, 0));
		expected = Arrays.asList(new Point3D(0.534846922835, 0.8449489742783, 0));
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
		expected = Arrays.asList(new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
		// ray intersects the sphere(ray starts in the sphere- ray passed the center) 17
		ray = new Ray(new Vector(-1, 0, 0), new Point3D(0.5, 0, 0));
		expected = Arrays.asList(new Point3D(-1, 0, 0));
		assertEquals("Find intersection function error", expected, sph.findIntersections(ray));
	}
}