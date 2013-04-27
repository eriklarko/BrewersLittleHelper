package blh.core.formulas.color;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.color.EBC;
import blh.core.units.color.MaltColorUnit;

/**
 * Randy Mosher developed a model based on commercial beers whose recipes and
 * color were known. Using this approximation, minimum beer color is 4.7. This
 * is not realistic and the model should only be used for beer with MCU greater
 * than 7.
 *
 * @author thinner
 */
public class Mosher implements Formula<EBC>{

    @Override
    public EBC calc(FullContext context) {
        MaltColorUnit mcu = context.getMaltColorUnit();
        return calc(mcu);
    }

    public EBC calc(MaltColorUnit mcu) {
        return new EBC(((0.3 * mcu.value()) + 4.7) * 1.97);
        
    }
}
