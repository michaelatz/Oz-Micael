package unittests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import elements.Camera;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

public class IntegrationTestCamera {

	static Camera c = new Camera(new Point3D(0, 0, 1), new Vector(0, -1, 0), new Vector(0, 0, -1));
/**
 * counting the number of the intersection points for each shape
 * @param shape
 * @return number of the intersection points
 */
	private int counter(Intersectable shape) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				List<GeoPoint> temp = shape.findIntersections(c.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if (temp != null) {
					counter = counter + temp.size();
				}

			}
		}

		return counter;
	}

	@Test
	public void spherePoints() {
		// tow points
		Sphere sph = new Sphere(1, new Point3D(0, 0, -3));
		assertEquals("Integration function error", 2, counter(sph));
		// 18 points
		sph = new Sphere(2.5, new Point3D(0, 0, -3));
		assertEquals("Integration function error", 18, counter(sph));
		// 10 points
		sph = new Sphere(1.5, new Point3D(0, 0, -2));
		assertEquals("Integration function error", 10, counter(sph));
		// 9 points
		sph = new Sphere(20, new Point3D(0, 0, -1));
		assertEquals("Integration function error", 9, counter(sph));
		// zero points
		sph = new Sphere(1, new Point3D(0, 0, 1));
		assertEquals("Integration function error", 0, counter(sph));

	}

	@Test
	public void trianglePoints() {
		// one point
		Triangle triangle = new Triangle(new Point3D(0, -1, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
		assertEquals("Integration function error", 1, counter(triangle));
		// tow points
		triangle = new Triangle(new Point3D(0, -20, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
		assertEquals("Integration function error", 2, counter(triangle));

	}

	@Test
	public void planePoints() {
		// 9 points
		Plane plane = new Plane(new Point3D(0, 0, -5), new Vector(0, 0, -1));
        assertEquals(9, counter(plane), 0);
		// 9 points-b
		plane = new Plane(new Point3D(0, 0, -3), new Vector(0, 1, 3));
		assertEquals("Integration function error", 9, counter(plane));
		// 6 points
		plane = new Plane(new Point3D(0, 0, -3), new Vector(0, 3, 1));
		assertEquals("Integration function error", 6, counter(plane));

	}
}