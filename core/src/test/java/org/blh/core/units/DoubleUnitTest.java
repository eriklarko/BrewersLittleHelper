package org.blh.core.units;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Erik Larkö
 */
public class DoubleUnitTest {

	@Test
	public void testEqualsDeltaAsDouble() {
		DoubleUnit a = new DoubleUnit(1, 0.001);
		DoubleUnit b = new DoubleUnit(1.0001, 0.001);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDifferentDeltasAsDoubles() {
		DoubleUnit a = new DoubleUnit(1, 0.00000000001);
		DoubleUnit b = new DoubleUnit(1.0001, 0.001);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDeltaAsInt() {
		DoubleUnit a = new DoubleUnit(1, 3);
		DoubleUnit b = new DoubleUnit(1.0001, 3);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDifferentDeltasAsInts() {
		DoubleUnit a = new DoubleUnit(1, 30);
		DoubleUnit b = new DoubleUnit(1.0001, 3);
		Assert.assertTrue(a.equals(b));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsDoubleTooLarge() {
		new DoubleUnit(1, 1d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsDoubleTooSmall() {
		new DoubleUnit(1, -0.1d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsIntTooLarge() {
		new DoubleUnit(1, -1);
	}
}
