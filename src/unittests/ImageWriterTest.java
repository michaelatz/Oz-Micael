package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import renderer.*;
import geometries.*;
import primitives.*;

public class ImageWriterTest {

	
			ImageWriter image= new ImageWriter("Moshe",500,500,1000,1000);
			@Test
			public void renderTest() {
				for(int i=0;i<image.getNx();i++)
				{
					for(int j=0;j<image.getNy();j++)
					{
						if(i%50==0||j%50==0)
						{
							image.writePixel(j, i, java.awt.Color.red);
						}
					}
				}
				image.writeToimage();
			}
}
