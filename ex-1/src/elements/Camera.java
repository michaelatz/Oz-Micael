package elements;

import static primitives.Util.*;
import primitives.*;

public class Camera {
	private Point3D P0;
	private Vector Vup;
	private Vector Vto;
	private Vector Vright;

// ***************** Constructors ********************** //
	/**
	 * Camera constructor
	 * 
	 * @param location
	 * @param up
	 * @param to
	 */
	public Camera(Point3D location, Vector up, Vector to) {
		if (isZero(up.dotProduct(to))) {
			this.P0 = location;
			this.Vup = up;
			this.Vto = to;
			up.normalization();
			to.normalization();
			this.Vright = to.crossProduct(up);
			Vright.normalization();
		} else
			throw new IllegalArgumentException("the vectors are not orthogonal");
	}

// ***************** Getters/Setters ********************** //
	/**
	 * location getter
	 * 
	 * @return Point3D location
	 */
	public Point3D getP0() {
		return P0;
	}

	/**
	 * vector up getter
	 * 
	 * @return Vector Vup
	 */
	public Vector getUp() {
		return Vup;
	}

	/**
	 * Vector up getter
	 * 
	 * @return Vector Vto
	 */
	public Vector getTo() {
		return Vto;
	}

	/**
	 * Vector right getter
	 * 
	 * @return Vector Vright
	 */
	public Vector getRight() {
		return Vright;
	}

	// ***************** Operations ******************** //
	/**
	 * construct Ray Through Pixel calculation
	 * 
	 * @param nX=amount         of pixels of plane's width
	 * @param nY=amount         of pixels of plane's height
	 * @param j=location        in the plane (height)
	 * @param i=location        in the plane (width)
	 * @param screenDistance=   distance from the plane to the camera
	 * @param screenWidth=width of the plane
	 * @param screenHeight=     height of the plane
	 * @return intersected ray
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth,
			double screenHeight) {
		double Ry = screenHeight / nY;
		double Rx = screenWidth / nX;
		Point3D p0 = new Point3D(0, 0, 0);
		Point3D pc = p0.add(Vto.scale(screenDistance));
		double yi = (i - (nY / 2.0)) * Ry + (Ry / 2.0);
		double xj = (j - (nX / 2.0)) * Rx + (Rx / 2.0);
		Point3D pij = pc;
		if (yi != 0)
			pij = pij.add(Vup.scale(-yi));
		if (xj != 0)
			pij = pij.add(Vright.scale(xj));
		Vector vec = new Vector(pij.subtract(p0));
		return new Ray(vec.normalization(), p0);
	}

}