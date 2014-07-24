package se.angstroms.blh.anders.view.util.listspinner;

/**
 *
 * @author eriklark
 */
public class ListSpinnerDoubleList extends java.util.AbstractList<Double> {

    private final double delta = 0.00001;

    /**
     *
     */
    public ListSpinnerDoubleList() {
        this((Double.MIN_VALUE / 2) + 1, Double.MAX_VALUE / 2, 1);
    }

    /**
     *
     * @param from
     * @param to
     */
    public ListSpinnerDoubleList(double from, double to) {
        this(from, to, from > to ? -1 : 1);
    }

    /**
     *
     * @param from
     * @param to
     * @param step
     */
    public ListSpinnerDoubleList(double from, double to, double step) {
        this.from = from;
        this.size = (int) ((to - from) / step) + 1;
        if (size < 0) {
            throw new IllegalArgumentException("This results in a negative size: " + from + ", " + to + "," + step);
        }
        this.step = step;
    }
    private double from;
    private int size;
    private double step;

	// ===============================================================================
    // List interface
    @Override
    public Double get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be < 0: " + index);
        }
        Double lValue = this.from + (index * this.step);
        return lValue;
    }

    @Override
    public int indexOf(Object o) {
        if (!(o instanceof Double)) {
            return -1;
        }

        // calculate the index
        Double lValue = ((Double) o);
        double a = lValue - this.from;
        double b = a / this.step;
        int lIndex = (int) Math.round(b);
        if (lIndex < 0 || lIndex > size) {
            return -1;
        }

        // check if that what is at the index matches with out value
        Double lValueAtIndex = get(lIndex);
        if (Math.abs(lValue - lValueAtIndex) < delta) {
            return lIndex;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }
}
