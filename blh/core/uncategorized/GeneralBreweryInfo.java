package blh.core.uncategorized;

import blh.core.units.Factor;

/**
 *
 * @author thinner
 */
public class GeneralBreweryInfo {
    private Input<Factor> effiency;

    public Factor getEffiency() {
        return effiency.value();
    }
    
    
}
