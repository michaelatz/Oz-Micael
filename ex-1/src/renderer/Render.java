package renderer;

import java.util.*;

import elements.*;
import geometries.Intersectable.GeoPoint;
import geometries.*;
import primitives.*;
import primitives.Vector;
import scene.Scene;

public class Render {
	ImageWriter IWriter;
	Scene scene;

	/**
	 * @param iWriter
	 * @param scene
	 */
	public Render(ImageWriter iWriter, Scene scene) {
		this.IWriter = iWriter;
		this.scene = scene;
	}

	public void renderImage() {
		for (int i = 0; i < IWriter.getHeight(); i++) {
			for (int j = 0; j < IWriter.getWidth(); j++) {
				Ray ray = scene.getCamera().constructRayThroughPixel(IWriter.getNx(), IWriter.getNy(), j, i,
						scene.getScreenDistance(), IWriter.getWidth(), IWriter.getHeight());
				if (scene.getGeometries().findIntersections(ray) == null) {
					IWriter.writePixel(j, i, scene.getBackground().getColor());
				} else {
					List<GeoPoint> intersectionPoints = scene.getGeometries().findIntersections(ray);
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					if (scene.getAmbientLight() != null)
						IWriter.writePixel(j, i, calcColor(closestPoint).getColor());
				}
			}
		}
	}

	/**
	 * get Closest Point function
	 * 
	 * @param intersectionPoints
	 * @return closest point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		GeoPoint closest = intersectionPoints.get(0);
		for (int i = 1; i < intersectionPoints.size(); i++) {
			GeoPoint a = intersectionPoints.get(i);
			GeoPoint b = intersectionPoints.get(i - 1);
			if (a.point.distance(scene.getCamera().getLocation()) > b.point.distance(scene.getCamera().getLocation())) {
				closest = a;
			}
		}
		return closest;
	}

	/**
	 * calcColor function
	 * 
	 * @param point
	 * @return color
	 */
	private Color calcColor(GeoPoint intersection) {
		Color color = new Color(scene.getAmbientLight().getIntensity());
		color = color.add(intersection.geometry.getEmmission());
		Vector l = new Vector(intersection.point.subtract(scene.getCamera().getLocation()));
		Vector n = new Vector(intersection.geometry.getNormal(intersection.point));
		double d=l.dotProduct(n);
		d*=intersection.geometry.getMaterial().getkD();
		color=color.add(colors)
		return color;
	}

	/**
	 * print Grid
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		for (int i = 0; i < IWriter.getNx(); i++) {
			for (int j = 0; j < IWriter.getNy(); j++) {
				if (i % interval == 0 || j % interval == 0) {
					this.IWriter.writePixel(j, i, java.awt.Color.yellow);
				}
			}
		}

	}

	/**
	 * write to image
	 */
	public void writeToImage() {
		IWriter.writeToimage();
	}

}
