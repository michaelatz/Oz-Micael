package elements;

import primitives.*;

public interface LightSource {
	
	Color getIntensity(Point3D p);

	Vector getL(Point3D p);
}
