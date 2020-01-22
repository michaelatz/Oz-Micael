package unittests;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import geometries.*;
import primitives.*;

public class CylinderTest {

	@Test
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest1() {
		Cylinder cy = new Cylinder(10, 10, new Ray(new Vector(0, 0, 1), new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", new Vector(0, 1, 0), cy.getNormal(new Point3D(0, 10, 4)));
	}

	@Test
	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest2() {
		Cylinder cy = new Cylinder(3, 5, new Ray(new Vector(1, 0, 0), new Point3D(0, 0, 0)));
        assertEquals("Find intersection function error", new Vector(0, 1, 0), cy.getNormal(new Point3D(3,1,0)));
	}
	public void findIntersectionsTest() {

	}
}
