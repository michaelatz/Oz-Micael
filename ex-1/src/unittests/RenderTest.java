/*
package unittests;

import org.junit.Test;

import elements.AmbientLight;
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
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)), 149);
		scene.setBackground(new Color(0, 0, 0));
		AmbientLight ambientLight = new AmbientLight(new Color(255,0,0), 1.0);
		scene.setAmbientLight(ambientLight);
		Material mat = new Material(1, 2, 3);
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		geometries.add(new Sphere(new Color(255,0,0), 49, new Point3D(0, 0, 149)));

		geometries.add(new Triangle(new Color(255,0,0), new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149)));

		geometries.add(new Triangle(new Color(255,0,0), new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149)));

		geometries.add(new Triangle(new Color(255,0,0), new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149)));

		geometries
				.add(new Triangle(new Color(255,0,0), new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 500, 500);
		scene.setGeometries(geometries);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}
}
*/
package unittests;

import org.junit.Test;

import elements.AmbientLight;
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
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)), 150);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		AmbientLight al=new AmbientLight(new Color(0,0,0));
		scene.setAmbientLight(al);
		geometries.add(new Sphere(new Color(70, 70, 70), 50, new Point3D(0, 0, 150)));

		geometries.add(new Triangle(new Color(0, 0, 255), new Point3D(100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(100, 100, 149)));

		geometries.add(new Triangle(new Color(70, 70, 70), new Point3D(100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(100, -100, 149)));

		geometries.add(new Triangle(new Color(255, 0, 0), new Point3D(-100, 0, 149), new Point3D(0, 100, 149),
				new Point3D(-100, 100, 149)));

		geometries.add(new Triangle(new Color(0, 255, 0), new Point3D(-100, 0, 149), new Point3D(0, -100, 149),
				new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test2", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}
}
