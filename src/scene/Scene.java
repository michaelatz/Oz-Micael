package scene;

import elements.*;
import geometries.*;
import primitives.*;
import elements.*;

public class Scene {
	String name;
	Color background;
	AmbientLight ambientLight;
	Geometries geometries;
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
		this.geometries = null;
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
	 * @param the geometries
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
	 * @param the screenDistance
	 */
	public void setCamera(Camera camera, double screenDistance) {
		this.camera = camera;
		this.screenDistance = screenDistance;
	}

	/**
	 * screenDistance getter
	 * 
	 * @return the screenDistance
	 */
	public double getScreenDistance() {
		return screenDistance;
	}

	public void add(Intersectable... _geometries) {
		if (!geometries.equals(null)) {
			for (int i = 0; i < _geometries.length; ++i) {
				geometries.add(_geometries[i]);
			}
		}
	}

}
