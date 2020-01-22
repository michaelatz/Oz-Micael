package unittests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import primitives.*;

public class VectorTest {
	static Vector myVector = new Vector(1.0, 2.0, 3.0);

	/**
	 * test Method for {@link primitives.Vector#Zero(primitives.Vector)}.
	 */
	@Test
	public void testVectorZero() {

		try {
			Point3D p = new Point3D(Coordinate.ZERO, Coordinate.ZERO, Coordinate.ZERO);
			Vector myVector = new Vector(new Point3D(Coordinate.ZERO, Coordinate.ZERO, Coordinate.ZERO));
			Assert.assertNotEquals(myVector, p);
			Assert.fail("Test failed should throw excepion");
		} catch (IllegalArgumentException e) {
			Assert.assertTrue("Test PASS  throw excepion in case of Zero", true);

		}

	}

	/**
	 * test Vector administration
	 */
	@Test
	public void testVector() {
		// test Method for {@link primitives.Vector#add(primitives.Vector)}.
		Vector other = new Vector(1.0, 1.0, 1.0);
		Assert.assertEquals(new Vector(2.0, 3.0, 4.0), myVector.add(other));
		// test Method for {@link primitives.Vector#subtract(primitives.Vector)}.
		Assert.assertEquals(new Vector(0.0, 1.0, 2.0), myVector.subtract(other));
		// test Method for {@link primitives.Vector#scale(primitives.Vector)}.
		double a = 2.0;
		Assert.assertEquals(new Vector(2.0, 4.0, 6.0), myVector.scale(a));
		// test Method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
		Assert.assertEquals(6, myVector.dotProduct(other), 0.0);
		// test Method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
		Assert.assertEquals(new Vector(-1, 2, -1), myVector.crossProduct(other));
		// test Method for {@link primitives.Vector#normalization(primitives.Vector)}.
		Vector myVector1 = new Vector(1.0, 2.0, 2.0);
		Assert.assertEquals(new Vector((double) 1 / 3, (double) 2 / 3, ((double) 2 / 3)), myVector1.normalization());

	}
}

	