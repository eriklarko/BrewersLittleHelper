package org.blh.core.unit;

/**
 * A class to handle double uncertainties in eg equals.
 *
 * @author Erik Larkö
 */
public abstract class DoubleUnit extends Unit<Double> {

    private static double getDeltaFromDecimalPlaces(int decimalPlaces) {
        return 1 / (Math.pow(10, decimalPlaces));
    }

    private static void setDelta(DoubleUnit unit, double delta) {
        if (delta >= 1 || delta < 0) {
            throw new IllegalArgumentException("The delta should be in the range [0, 1)");
        }
        unit.delta = delta;
    }

    public static final double DEFAULT_DELTA = 0.0001;
    private double delta;

    public DoubleUnit(double value) {
        this(value, DEFAULT_DELTA);
    }

    public DoubleUnit(double value, int equalsToDecimalPlace) {
        this(value, getDeltaFromDecimalPlaces(equalsToDecimalPlace));
    }

    public DoubleUnit(double value, double delta) {
        super(value);
        setDelta(this, delta);
    }

    public void setDelta(double delta) {
        setDelta(this, delta);
    }

    public void setDelta(int equalsToDecimalPlace) {
        this.delta = getDeltaFromDecimalPlaces(equalsToDecimalPlace);
    }

	public abstract <T> T deriveNew(double d);

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

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.delta) ^ (Double.doubleToLongBits(this.delta) >>> 32));
        hash = 79 * hash + super.hashCode();
        return hash;
    }
    // End this hash is ok
}
