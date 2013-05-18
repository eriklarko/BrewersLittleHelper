package blh.core.formulas.gravity.boilgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.gravity.SpecificGravity;

/**
 *
 * @author thinner
 */
public class Average implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        SpecificGravity preBoil = context.preBoilGravity.value();
        SpecificGravity postBoil = context.postBoilGravity.value();

        return calc(preBoil, postBoil);
    }

    public SpecificGravity calc(SpecificGravity preBoil, SpecificGravity postBoil) {
        return new SpecificGravity((preBoil.value() + postBoil.value()) / 2);
    }
}
