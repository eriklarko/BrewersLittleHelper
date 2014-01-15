package org.blh.core.formulas.alcohol;

import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.gravity.SpecificGravity;

/**
 * Taken from http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
 * 
 * ABV = (76.08 * (og-fg) / (1.775-og)) * (fg / 0.794)
 *
 * @author thinner
 */
public class Daniels  {
    
    public ABV calc(SpecificGravity originalGravity, SpecificGravity finalGravity) {
        double og = originalGravity.inexactValue();
        double fg = finalGravity.inexactValue();
        
        return new ABV((76.08 * (og-fg) / (1.775-og)) * (fg / 0.794));
    }

}
