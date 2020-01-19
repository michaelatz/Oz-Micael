package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;
/**
 * Camera Test
 * @author michael 
 * @author oz
 *
 */
public class CameraTest {
	static Camera c = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));

	@Test
	public void testConstructor() {
		assertEquals("Camera test error", new Vector(-1, 0, 0), c.getRight());
	}

	@Test
	public void constructRayThroughPixelTest() {
		Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));

		Ray ray = camera.constructRayThroughPixel(3, 3, 0, 0, 1, 3, 3);
		assertEquals(new Ray(new Vector(1, -1, -1), new Point3D(0, 0, 0)), ray); // Corner pixel case
		ray = camera.constructRayThroughPixel(3, 3, 0, 1, 1, 3, 3);
		assertEquals(new Ray(new Vector(1, 0, -1), new Point3D(0, 0, 0)), ray); // Side pixel case
		ray = camera.constructRayThroughPixel(3, 3, 1, 1, 1, 3, 3);
		assertEquals(new Ray(new Vector(0, 0, -1), new Point3D(0, 0, 0)), ray); // Inside pixel case

		ray = camera.constructRayThroughPixel(4, 4, 0, 0, 1, 4, 4);
		assertEquals(new Ray(new Vector(1.5, -1.5, -1), new Point3D(0, 0, 0)), ray); // Corner pixel case
		ray = camera.constructRayThroughPixel(4, 4, 0, 1, 1, 4, 4);
		assertEquals(new Ray(new Vector(1.5, -0.5, -1), new Point3D(0, 0, 0)), ray); // Inside pixel case
		ray = camera.constructRayThroughPixel(4, 4, 1, 1, 1, 4, 4);
		assertEquals(new Ray(new Vector(0.5, -0.5, -1), new Point3D(0, 0, 0)), ray); // Side pixel case
	}
}
