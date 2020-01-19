package unittests;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;


public class PolygonTest {

	    /**
	     * test Method for {@link geometries.Polygon#findIntersections}  (geomtries.Polygon)}
	     */
	    @Test
	    public void testFindIntersections() {
	        Point3D p1 = new Point3D(0, 0, 0);
	        Point3D p2 = new Point3D(1, 0, 0);
	        Point3D p3 = new Point3D(1, 1, 0);
	        Point3D p4 = new Point3D(0, 1, 0);
	        Polygon polygon = new Polygon(p1, p2, p3, p4);
	        Ray ray = new Ray(new Vector(0, 0, 1), new Point3D(0.25, 0.25, -1));
			List<GeoPoint> intersections=null;
	        intersections.get(0).point= new Point3D(0.25, 0.25, 0);
	        intersections.get(0).geometry=polygon;
	        assertEquals("Polygon findIntersection error ", intersections, polygon.findIntersections(ray)); //inside square
	        ray = new Ray(new Vector(0, 0, 1), new Point3D(-1, -1, -1));
	        assertEquals("Polygon findIntersection error ", null, polygon.findIntersections(ray)); //outside square
	        p1 = new Point3D(-2, 0, 0);
	        p2 = new Point3D(-1, 2, 0);
	        p3 = new Point3D(1, 2, 0);
	        p4 = new Point3D(2, 0, 0);
	        Point3D p5 = new Point3D(0, -2, 0);
	        Polygon pentagon = new Polygon(p1,p2,p3,p4,p5);
	        ray = new Ray(new Vector(0, 0, 1), new Point3D(0, 0, -1));
	        intersections.get(0).point = new Point3D(0, 0, 0);
	        intersections.get(0).geometry =polygon;
	        assertEquals("Polygon findIntersection error ", intersections, pentagon.findIntersections(ray)); //inside pentagon
	        ray = new Ray(new Vector(0, 0, 1), new Point3D(-3, 3, -1));
	        assertEquals("Polygon findIntersection error ", null, pentagon.findIntersections(ray)); //outside pentagon
	        }
	}