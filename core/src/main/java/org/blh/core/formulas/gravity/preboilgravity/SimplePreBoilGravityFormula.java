package org.blh.core.formulas.gravity.preboilgravity;

import org.blh.core.formulas.gravity.originalgravity.SimpleOriginalGravityFormula;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class SimplePreBoilGravityFormula extends SimpleOriginalGravityFormula {

    @Override
    protected Liters getVolume(FullContext context) {
        return context.volumePre(context.BOIL);
    }
}
