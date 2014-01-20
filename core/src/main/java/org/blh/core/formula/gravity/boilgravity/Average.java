package org.blh.core.formula.gravity.boilgravity;

import org.blh.core.unit.gravity.SpecificGravity;

/**
 * Calculates the boil gravity as the mean of the pre-boil gravity and
 * post-boil gravity.
 *
 * @author thinner
 */
public class Average {

    public SpecificGravity calc(SpecificGravity preBoil, SpecificGravity postBoil) {
        return new SpecificGravity((preBoil.value() + postBoil.value()) / 2);
    }
}
