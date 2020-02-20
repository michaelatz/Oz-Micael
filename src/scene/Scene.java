package scene;

import java.util.ArrayList;
import java.util.List;

import elements.*;
import geometries.*;
import primitives.*;

/**
 * class representing the scene
 * 
 * @author michael
 * @author oz
 */
public class Scene {
	String name;
	Color background ;
	AmbientLight ambientLight ;
	Geometries geometries = new Geometries();
	List<LightSource> _lights = new ArrayList<LightSource>();
	Camera camera;
	double screenDistance;

	// ***************** Constructors ********************** //
	/**
	 * Scene constructor
	 * 
	 * @param name
	 */
	public Scene(String name) {
		this.name = name;
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * name getter
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * background getter
	 * 
	 * @return the background
	 */
	public Color getBackground() {
		return background;
	}

	/**
	 * background setter
	 * 
	 * @param background the background to set
	 */
	public void setBackground(Color background) {
		this.background = background;
	}

	/**
	 * ambientLight getter
	 * 
	 * @return the ambientLight
	 */
	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	/**
	 * ambientLight setter
	 * 
	 * @param the ambientLight
	 */
	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	/**
	 * geometries getter
	 * 
	 * @return the geometries
	 */
	public Geometries getGeometries() {
		return geometries;
	}
	
	/**
	 * geometries setter
	 * 
	 * @param geometries
	 */
	public void setGeometries(Geometries geometries) {
		this.geometries = geometries;
	}

	/**
	 * camera getter
	 * 
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * camera and screenDistance setter
	 * 
	 * @param the camera
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**
	 * screenDistance getter
	 * 
	 * @return the screenDistance
	 */
	public double getScreenDistance() {
		return screenDistance;
	}

	/**
	 * screen Distance setter
	 * 
	 * @param distance
	 */
	public void setScreenDistance(double distance) {
		this.screenDistance = distance;
	}

	/**
	 * lights getter
	 * 
	 * @return the lights
	 */
	public List<LightSource> getLights() {
		return _lights;
	}


	/**
	 * lights setter
	 * 
	 * @param lights
	 */
	public void setLights(List<LightSource> lights) {
		this._lights = lights;
	}

    // ***************** Operations ******************** //

    /**
     * changes the camera location and distance from view plane
     *
     * @param cam
     * @param dist
     */
    public void updateCamera(Camera cam, double distance) {
        camera = cam;
        screenDistance = distance;
    }

    /**
     * changes the background color
     *
     * @param color
     */
    public void updateBackground(Color color) {
        background = color;
    }

    /**
     * changes the ambient light
     *
     * @param amb
     */
    public void updateAmbient(AmbientLight amb) {
        ambientLight = amb;
    }

    /**
     * adds shapes to the 3D model
     *
     * @param shapes
     */
    public void addIntersectable(Intersectable... shapes) {
        geometries.add(shapes);
    }

    /**
     * adds lights to the scene
     *
     * @param lights
     */
    public void addLight(LightSource... lights) {
        for (int i = 0; i < lights.length; ++i)
            _lights.add(lights[i]);
    }

}