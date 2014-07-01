package se.angstroms.blh.anders.context;

import org.blh.core.unit.Factor;
import se.angstroms.blh.anders.context.value.InputtedValue;

/**
 * Some data unique to each brewery.
 *
 * @author thinner
 */
public class GeneralBreweryInfo {
    private InputtedValue<Factor> effiency;

    public Factor getEffiency() {
        return effiency.get();
    }
}
