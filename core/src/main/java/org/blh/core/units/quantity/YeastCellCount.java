package org.blh.core.units.quantity;

import org.blh.core.units.Unit;

/**
 * Created by Erik Larkö at 7/4/13 10:58 PM
 */
public class YeastCellCount extends Unit<Billion> {

    public YeastCellCount(int cellCountInBillions) {
        super(new Billion(cellCountInBillions));
    }
    
    public YeastCellCount(Million value) {
        super(new Billion(value.value() / 1000));
    }

    public YeastCellCount(Billion value) {
        super(value);
    }
}
