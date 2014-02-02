package org.blh.formuladecorator;

import org.blh.core.unit.Factor;

/**
 * Some data unique to each brewery.
 *
 * @author thinner
 */
public class GeneralBreweryInfo {
    private Input<Factor> effiency;

    public Factor getEffiency() {
        return effiency.value();
    }
}
