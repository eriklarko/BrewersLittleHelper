package blh.core.formulas.volumes.water;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 15, 2013 8:39:55 PM
 */
public class MultiplicativeStep extends BrewStep {

    private final double term;

    public MultiplicativeStep(double term) {
        this.term = term;
    }

    @Override
    protected Liters forward(Liters base, FullContext context) {
        return new Liters(base.value() * term);
    }

    @Override
    protected Liters backward(Liters base, FullContext context) {
        return new Liters(base.value() / term);
    }
}
