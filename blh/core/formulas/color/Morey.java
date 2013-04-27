package blh.core.formulas.color;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.color.EBC;
import blh.core.units.color.MaltColorUnit;

/**
 * Taken from
 * http://www.beersmith.com/blog/2008/04/29/beer-color-understanding-srm-lovibond-and-ebc/
 *
 * Morey's approximation is based on five assumptions.
 *
 * 1. SRM is approximately equal to MCU for values from 0 to 10.
 *
 * 2. Homebrew is generally darker than commercial beer.
 *
 * 3. Base on the previous qualitative postulate, Morey assumed that Ray
 * Daniels’ predicted relationship exists for beers with color greater than 10.
 *
 * 4. Since Mosher’s equation predicts darker color than Daniels’ model for
 * values of MCU greater than 37, Morey assumed that Mosher’s approximation
 * governed beer color for all values more than 37 MCUs.
 *
 * 5. Difference in color for beers greater than 40 SRM are essentially
 * impossible to detect visually; therefore, Morey limited the analysis to SRM
 * of 50 and less.
 *
 * @author thinner
 */
public class Morey implements Formula<EBC> {

    @Override
    public EBC calc(FullContext context) {
        MaltColorUnit mcu = context.getMaltColorUnit();
        return calc(mcu);
    }

    public EBC calc(MaltColorUnit mcu) {
        return new EBC((1.4922 * Math.pow(mcu.value(), 0.6859)) * 1.97);
    }
}
