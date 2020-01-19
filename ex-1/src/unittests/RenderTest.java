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
		scene.setBackground(new Color(118, 128, 152));
		AmbientLight ambientLight = new AmbientLight(new Color(java.awt.Color.magenta), 50.0);
		scene.setAmbientLight(ambientLight);
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		geometries.add(new Sphere(49, new Point3D(0, 0, 149)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149)));

		geometries
				.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		scene.setGeometries(geometries);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		render.printGrid(50);
		render.writeToImage();
	}
}