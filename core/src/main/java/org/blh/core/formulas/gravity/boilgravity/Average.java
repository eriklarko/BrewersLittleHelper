package org.blh.core.formulas.gravity.boilgravity;

import org.blh.core.units.gravity.SpecificGravity;

/**
 *
 * @author thinner
 */
public class Average  {

    public SpecificGravity calc(SpecificGravity preBoil, SpecificGravity postBoil) {
        return new SpecificGravity((preBoil.value()+ postBoil.value()) / 2);
    }
}
