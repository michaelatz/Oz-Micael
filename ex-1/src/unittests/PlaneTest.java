package unittests;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;
import geometries.*;
import primitives.*;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

public class PlaneTest {
	static Point3D p1 = new Point3D(1, 0, 0);
	static Point3D p2 = new Point3D(1, 1, 0);
	static Point3D p3 = new Point3D(0, 1, 0);
	static Plane pl = new Plane(p1, p2, p3);
	@Test
	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	public void getNormaltest() {
        assertEquals("Find intersection function error", new Vector(0.0, 0.0, 1.0), pl.getNormal(p1));
	}

	@Test
	public void findIntersectionsTest() {
		//ray intersects and orthogonal to the plane 1
		Ray ray=new Ray(new Vector(0, 0, 1),new Point3D(0, 0, -1));
		List<GeoPoint> expected = Arrays.asList(new GeoPoint(pl,new Point3D(0, 0, 0)));
		assertEquals("Find intersection function error", expected, pl.findIntersections(ray));
        //ray intersects the plane (not orthogonal) 2
        ray=new Ray(new Vector(1, 1, 1),new Point3D(-1, -1, -1));
		expected = Arrays.asList(new GeoPoint(pl,new Point3D(0, 0, 0)));
        assertEquals("Find intersection function error", expected, pl.findIntersections(ray));
        //ray parallel (not included in the plane) 3
        ray=new Ray(new Vector(1, 0, 0),new Point3D(0, 0, -1));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
        //ray parallel (included in the plane) 4
        ray=new Ray(new Vector(1, 0, 0),new Point3D(0, 0, 0));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
        //ray orthogonal the plane(begins in the plane) 5
        ray=new Ray(new Vector(0, 0, 1),new Point3D(0, 0, 0));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
        //ray intersects the plane (not orthogonal) 6
        ray=new Ray(new Vector(1, 1, 1),new Point3D(0, 0, 0));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
        //ray begins in the same point which appears a reference point on the plane 7
        ray=new Ray(new Vector(1, 1, 1),new Point3D(1, 0, 0));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
        //ray begins after the the plane (doesn't intersects)
        ray=new Ray(new Vector(0, 0, 1),new Point3D(0, 0, 1));
        assertEquals("Find intersection function error", null, pl.findIntersections(ray));
	}
}
