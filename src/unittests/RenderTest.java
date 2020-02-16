package unittests;

import elements.AmbientLight;
import org.junit.Test;

import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setScreenDistance(150);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.white), 1));
		Geometries geometries = new Geometries();
		scene.setBackground(new Color(0, 0, 0));
		geometries.add(new Sphere(50, new Point3D(0, 0, 150)));

		geometries.add(
				new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149)));

		geometries.add(
				new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149)));

		geometries.add(
				new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149)));

		geometries.add(
				new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50, new Color(java.awt.Color.blue));
		render.writeToImage();
	}

	@Test
	public void renderEmission() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setScreenDistance(150);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(new Color(java.awt.Color.gray), 50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Color(java.awt.Color.red), new Point3D(100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(100, 100, 149)));

		geometries.add(new Triangle(new Color(java.awt.Color.blue), new Point3D(100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(100, -100, 149)));

		geometries.add(new Triangle(new Color(java.awt.Color.yellow), new Point3D(-100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(new Color(java.awt.Color.green), new Point3D(-100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50, new Color(0, 0, 255));
		render.writeToImage();
	}
}