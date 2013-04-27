package formulas.gravity.boilgravity;

import formulas.Formula;
import uncategorized.FullContext;
import units.gravity.SpecificGravity;

/**
 *
 * @author thinner
 */
public class Average implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        SpecificGravity preBoil = context.getPreBoilGravity();
        SpecificGravity postBoil = context.getPostBoilGravity();

        return calc(preBoil, postBoil);
    }

    public SpecificGravity calc(SpecificGravity preBoil, SpecificGravity postBoil) {
        return new SpecificGravity((preBoil.value() + postBoil.value()) / 2);
    }
}
