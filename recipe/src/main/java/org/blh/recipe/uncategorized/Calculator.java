package org.blh.recipe.uncategorized;

import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.alcohol.ABV;

/**
 *
 * @author thinner
 */
public class Calculator {
    private Formula<ABV> abvCalculator;
    private Formula<SpecificGravity> ogCalc, fgCalc, preboilCalc;


    public ABV calculateABV(FullContext context) {
        return abvCalculator.calc(context);
    }

    public SpecificGravity calculateOriginalGravity(FullContext context) {
        return ogCalc.calc(context);
    }

    public SpecificGravity calculateFinalGravity(FullContext context) {
        return fgCalc.calc(context);
    }

    public SpecificGravity calulatePrePoilGravity(FullContext context) {
        return preboilCalc.calc(context);
    }
}
