package elements;

import primitives.*;

public abstract class Light {
	private Color _intensity;
	
	public Light(Color _intensity) {
		this._intensity = _intensity;
	}
	
	/**
	 * intensity getter
	 * @return the _intensity
	 */
	public Color getIntensity()
	{
		return _intensity;
	}

}
