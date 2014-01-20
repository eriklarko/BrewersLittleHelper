package org.blh.core.formula.color;

import org.blh.core.unit.color.EBC;
import org.blh.core.unit.color.MaltColorUnit;

/**
 * Randy Mosher developed a model based on commercial beers whose recipes and
 * color were known. Using this approximation, minimum beer color is 4.7. This
 * is not realistic and the model should only be used for beer with MCU greater
 * than 7.
 *
 * @author thinner
 */
public class Mosher  {

    public EBC calc(MaltColorUnit mcu) {
        return new EBC(((0.3 * mcu.value()) + 4.7) * 1.97);
    }
}
