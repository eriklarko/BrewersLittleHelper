package org.blh.core.units.color;

import org.blh.core.units.NumericalUnit;
import org.blh.core.units.weight.Lbs;

/**
 *
 * @author thinner
 */
public class ColorPotential extends NumericalUnit {

    public ColorPotential() {
        super(0d);
    }

    public ColorPotential(Lovibond color, Lbs amount) {
        super(color.value().multiply(amount.value()));
    }

    public void add(Lovibond color, Lbs amount) {
        value = value.add(color.value().multiply(amount.value()));
    }
}
