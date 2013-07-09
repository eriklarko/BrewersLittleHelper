package blh.core.units.quantity;

import blh.core.units.Unit;

/**
 * Created by Erik Larkö at 7/4/13 10:58 PM
 */
public class YeastCellCount extends Unit<Billion> {

    public YeastCellCount(int cellCount) {
        super(new Billion(cellCount));
    }
    
    public YeastCellCount(Million value) {
        super(new Billion(value.value() / 1000));
    }

    public YeastCellCount(Billion value) {
        super(value);
    }
}
