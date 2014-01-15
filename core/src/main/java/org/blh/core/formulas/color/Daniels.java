package org.blh.core.formulas.color;

import org.blh.core.units.color.MaltColorUnit;
import org.blh.core.units.color.SRM;

/**
 * Daniels’ model differs from Mosher’s and suggests that homebrew is generally
 * darker than commercial beers. Like Mosher’s model, the formula has a minimum
 * color that is not reasonable. Consequently the formula should only be used
 * for beers with MCU greater than 11.
 *
 * @author thinner
 */
public class Daniels  {

    public SRM calc(MaltColorUnit mcu) {
        return new SRM(((0.2 * mcu.inexactValue()) + 8.4));
    }
}
