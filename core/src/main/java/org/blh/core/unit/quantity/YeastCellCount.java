package org.blh.core.unit.quantity;

import org.blh.core.unit.Unit;

/**
 * Created by Erik Larkö at 7/4/13 10:58 PM
 */
public class YeastCellCount extends Unit<Billion> {

    public static final int CONVERSION_FACTOR = 1000;

    public YeastCellCount(int cellCountInBillions) {
        super(new Billion(cellCountInBillions));
    }

    public YeastCellCount(Million value) {
        super(new Billion(value.value() / CONVERSION_FACTOR));
    }

    public YeastCellCount(Billion value) {
        super(value);
    }

	public YeastCellCount deriveNew(int d) {
		return new YeastCellCount(d);
	}
}
