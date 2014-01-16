package org.blh.core.units;

/**
 * A class to handle double uncertainties in eg equals
 *
 * @author Erik Larkö
 */
public class DoubleUnit extends Unit<Double> {

	private static final double getDeltaFromDecimalPlaces(int decimalPlaces) {
		return 1 / (Math.pow(10, decimalPlaces));
	}

	private double delta;

	public DoubleUnit(double value) {
		this(value, 0.0001);
	}

	public DoubleUnit(double value, int equalsToDecimalPlace) {
		this(value, getDeltaFromDecimalPlaces(equalsToDecimalPlace));
	}

	public DoubleUnit(double value, double delta) {
		super(value);
		if(delta >= 1 || delta < 0) {
			throw new IllegalArgumentException("The delta should be in the range [0, 1)");
		}
		this.delta = delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public void setDelta(int equalsToDecimalPlace) {
		this.delta = getDeltaFromDecimalPlaces(equalsToDecimalPlace);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		if (obj instanceof DoubleUnit) {
			final DoubleUnit other = (DoubleUnit) obj;

			double diff = Math.abs(this.value() - other.value());
			double deltaClosestToZero = Math.max(this.delta, other.delta);
			return diff < deltaClosestToZero;
		} else {
			return false;
		}
	}
}
