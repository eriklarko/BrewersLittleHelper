package org.blh.core.units;

import org.blh.core.unit.DoubleUnit;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Erik Larkö
 */
public class DoubleUnitTest {

    private class EmptyDoubleUnitImpl extends DoubleUnit {

        public EmptyDoubleUnitImpl(double value) {
            super(value);
        }

        public EmptyDoubleUnitImpl(double value, int equalsToDecimalPlace) {
            super(value, equalsToDecimalPlace);
        }

        public EmptyDoubleUnitImpl(double value, double delta) {
            super(value, delta);
        }

		@Override
		public EmptyDoubleUnitImpl deriveNew(double d) {
			return new EmptyDoubleUnitImpl(d);
		}
    }

	@Test
	public void testEqualsDeltaAsDouble() {
		DoubleUnit a = new EmptyDoubleUnitImpl(1, 0.001);
		DoubleUnit b = new EmptyDoubleUnitImpl(1.0001, 0.001);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDifferentDeltasAsDoubles() {
		DoubleUnit a = new EmptyDoubleUnitImpl(1, 0.00000000001);
		DoubleUnit b = new EmptyDoubleUnitImpl(1.0001, 0.001);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDeltaAsInt() {
		DoubleUnit a = new EmptyDoubleUnitImpl(1, 3);
		DoubleUnit b = new EmptyDoubleUnitImpl(1.0001, 3);
		Assert.assertTrue(a.equals(b));
	}

	@Test
	public void testEqualsDifferentDeltasAsInts() {
		DoubleUnit a = new EmptyDoubleUnitImpl(1, 30);
		DoubleUnit b = new EmptyDoubleUnitImpl(1.0001, 3);
		Assert.assertTrue(a.equals(b));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsDoubleTooLarge() {
		new EmptyDoubleUnitImpl(1, 1d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsDoubleTooSmall() {
		new EmptyDoubleUnitImpl(1, -0.1d);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidDeltaAsIntTooLarge() {
		new EmptyDoubleUnitImpl(1, -1);
	}
}
