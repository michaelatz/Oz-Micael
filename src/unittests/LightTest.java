package unittests;

/**
 * @author michael
 * @author oz
 */

import elements.*;
import geometries.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;



public class LightTest {
    public Scene createScene(String name) {
        Scene scene = new Scene(name);
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);
        return scene;
    }

    @Test
    public void lightingTest() {
        Scene scene = new Scene("Basic Light Tests");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);
        scene.setScreenDistance(100);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Triangle triangle1 = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 0), new Point3D(-250, 250, 90),
                new Point3D(-250, -250, 120), new Point3D(250, 250, 90));
        Triangle triangle2 = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 0), new Point3D(248, 248, 90),
                new Point3D(248, -248, 120), new Point3D(-248, -248, 120));
        geometries.add(triangle1, triangle2);
        Point3D pos = new Point3D(0, -30, 90);
        Color color = new Color(java.awt.Color.green);

        // test 1 - 2 triangles with point light
        ImageWriter imageWriter = new ImageWriter("Triangles PL", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
        lights.add(light1);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - 2 triangles with spot light
        imageWriter = new ImageWriter("Triangles SL", 500, 500, 600, 600);
        lights.clear();
        SpotLight light2 = new SpotLight(color, pos, 0.0000005, 1, 0.00005, new Vector(0, 0.7, 0.3));
        lights.add(light2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - 2 triangles with directional light
        imageWriter = new ImageWriter("Triangles DL", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight light3 = new DirectionalLight(new Color(400, 300, 300), new Vector(0, 0, 1));
        lights.add(light3);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere = new Sphere(new Material(0.5, 0.5, 300), new Color(0, 0, 100), 50, new Point3D(0, 0, 100));
        geometries.add(sphere);
        color = new Color(400, 300, 300);
        pos = new Point3D(-50, 50, 0);

        // test 1 - sphere with point light
        imageWriter = new ImageWriter("Sphere PL", 500, 500, 600, 600);
        lights = new ArrayList<LightSource>();
        PointLight pointLight2 = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(pointLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 2 - sphere with spot light
        imageWriter = new ImageWriter("Sphere SL", 500, 500, 600, 600);
        lights.clear();
        SpotLight spotLight2 = new SpotLight(color, pos, 0.000005, 1, 0.0005, new Vector(1, -1, 2));
        lights.add(spotLight2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test 3 - sphere with directional light
        imageWriter = new ImageWriter("Sphere DL", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight Directional2 = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
        lights.add(Directional2);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void shadowTest() {
        Scene scene = new Scene("Shadow tests");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere = new Sphere(new Material(0.5, 0.5, 300), new Color(0, 0, 100), 50, new Point3D(0, 0, 100));
        Triangle triangle = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 100), new Point3D(-30, 25, 40), new Point3D(-30, 15, 40),
                new Point3D(-20, 25, 40));
        geometries.add(sphere);
        geometries.add(triangle);
        Color color = new Color(400, 300, 300);
        Point3D pos = new Point3D(-35, 30, 0);

        ImageWriter imageWriter = new ImageWriter("sphere shadow PL", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("Sphere shadow DL", 500, 500, 600, 600);
        lights.clear();
        DirectionalLight directionalLight = new DirectionalLight(new Color(400, 300, 300), new Vector(1, -1, 2));
        lights.add(directionalLight);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("Sphere shadow SL", 500, 500, 600, 600);
        lights.clear();
        SpotLight spotLight = new SpotLight(color, pos, 0.000005, 1, 0.0005, new Vector(1, 2, 3));
        lights.add(spotLight);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        triangle = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 100), new Point3D(-30, 20, 40), new Point3D(-30, 10, 40),
                new Point3D(-20, 20, 40));
        geometries = new Geometries();
        scene.setGeometries(geometries);
        geometries.add(sphere);
        geometries.add(triangle);

        imageWriter = new ImageWriter("sphere shadow PL pos2", 500, 500, 600, 600);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        triangle = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 100), new Point3D(-20, 20, 40), new Point3D(-20, 10, 40),
                new Point3D(-10, 20, 40));
        geometries = new Geometries();
        geometries.add(sphere);
        geometries.add(triangle);
        scene.setGeometries(geometries);

        imageWriter = new ImageWriter("sphere shadow PL pos3", 500, 500, 600, 600);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        // test case : changing light position
        Triangle triangle1 = new Triangle(new Material(0.5, 0.5, 300), new Color(0, 0, 100), new Point3D(-30, 20, 40), new Point3D(-30, 10, 40),
                new Point3D(-20, 20, 40));

        // point light
        geometries = new Geometries();
        geometries.add(triangle1);
        scene.setGeometries(geometries);
        geometries.add(sphere);
        geometries.add(triangle1);

        imageWriter = new ImageWriter("sphere shadow PL lightPos2", 500, 500, 600, 600);
        pos = new Point3D(-25, 20, 20);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();

        imageWriter = new ImageWriter("sphere shadow PL lightPos3", 500, 500, 600, 600);
        pos = new Point3D(-35, 30, 20);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        lights.clear();
        lights = new ArrayList<LightSource>();
        point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        lights.add(point_light);
        scene.setLights(lights);
        render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void transparencyTest() {
        Scene scene = new Scene("Transparency tests");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setBackground(Color.BLACK);
        Geometries geometries = new Geometries();
        scene.setGeometries(geometries);
        Sphere sphere1 = new Sphere(new Material(0.5, 0.5, 300, 0, 0.3), new Color(0, 0, 100), 30, new Point3D(0, 0, 60));
        Sphere sphere2 = new Sphere(new Material(0.5, 0.5, 300, 0, 1), new Color(0, 0, 100), 10, new Point3D(0, 0, 60));
        geometries.add(sphere1, sphere2);
        Point3D pos = new Point3D(-50, 50, 0);
        Color color = new Color(400, 300, 300);

        ImageWriter imageWriter = new ImageWriter("Sphere within a sphere", 500, 500, 600, 600);
        List<LightSource> lights = new ArrayList<LightSource>();
        PointLight light1 = new PointLight(color, pos, 1, 0.00005, 0.0000005);
        lights.add(light1);
        scene.setLights(lights);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
