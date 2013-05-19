package blh.core.beerxml.types;

import blh.core.units.Percentage;
import blh.core.units.color.SRM;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class LiquidFermentable extends Fermentable {
    public static final String IBU_GALLONS_PER_POUND = "IBU_GAL_PER_LB";
    
    public final SRM color;
    public final double IBUGallonsPerPound;

    public LiquidFermentable(String name, Kilograms amount, 
            Percentage yield, boolean addAfterBoil, 
            String origin, String supplier, String notes, Percentage maxInBatch, 
            SRM color, double IBUGallonsPerPound) {
        super(0, name, Fermentable.FERMENTABLE_TYPE.EXTRACT, amount, yield, null, addAfterBoil, origin, supplier, notes, maxInBatch);
        this.color = color;
        this.IBUGallonsPerPound = IBUGallonsPerPound;
    }
    
    
}
