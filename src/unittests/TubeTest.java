package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import geometries.*;
import primitives.*;

public class TubeTest {

	@Test
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest() {
		Tube tu = new Tube(new Ray(new Vector(1, 0, 0), new Point3D(0, 0, 0)), 1);
		assertEquals("Find intersection function error", new Vector(0, 1, 0), tu.getNormal(new Point3D(3, 1, 0)));
	}

	public void findIntersectionsTest() {

	}
}
