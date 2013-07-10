package blh.core.formulas.bitterness;

import blh.core.formulas.Formula;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.bitterness.IBU;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Liters;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Ray Daniels Designing Great Beers, Brewers Publications 1996
 * 
 * Taken from
 * https://www.google.se/url?sa=t&rct=j&q=&esrc=s&source=web&cd=4&ved=0CEUQFjAD&url=http%3A%2F%2Fwww.nthba.org%2Fwww%2Fdocs%2FBrew%2520Day%2520Presentation%2520-%2520Hop%2520Bittering.ppt&ei=J-x6UbGtN4mI4gSK84GgCg&usg=AFQjCNH03nlWzfx6z1ZMThbFdQaaadc_kA&bvm=bv.45645796,d.bGE&cad=rja
 * 
 * IBU = (%U * Woz * %A * 7489) / (Vgal * (1 + GA)) 
 *     = (%U * Wg * %A * 1000) / * (Vl * (1 + GA))
 * where GA = (GB – 1.050)/.2
 *       GB = (OG-1)*(Ferment Vol)/(Boil * Vol) + 1
 *
 * @author thinner
 */
public class Daniels implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;

        for (HopAddition addition : context.getIngredientsList().getHopAdditions()) {
            SpecificGravity boilGravity = context.getBoilGravityAtMinutesLeft(addition.getTimeInBoil());
            Liters boilVolume = context.getBoilVolumeAtMinutesLeft(addition.getTimeInBoil());
            totalIBUs += getRawIBUsFromAddition(addition, boilGravity, boilVolume);
        }

        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, SpecificGravity originalGravity, Liters boilVolume) {
        double og = originalGravity.value();
        double gravityAdjustment = (og - 1.050) / 0.2;

        double IBUs = getUtilization() * addition.getAmount().value() * addition.getHop().alphaAcids * 1000;
        IBUs = IBUs / (boilVolume.value() + gravityAdjustment);
        return IBUs;
    }

    private double getUtilization() {
        throw new NotImplementedException();
    }
}
