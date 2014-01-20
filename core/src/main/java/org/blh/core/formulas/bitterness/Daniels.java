package org.blh.core.formulas.bitterness;

import org.blh.core.recipe.HopAddition;
import org.blh.core.units.Factor;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.volume.Liters;
import org.blh.core.units.weight.Grams;

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
public class Daniels  {

    public IBU calc(Iterable<HopAddition> hopAdditions, SpecificGravity originalGravity, Liters postBoilVolume, Liters preFermentationVolume) {
        double totalIBUs = 0;
        for (HopAddition addition : hopAdditions) {
            totalIBUs += getRawIBUsFromAddition(addition.getAmount(), addition.getHop().getAlphaAcids().asFactor(),
                    originalGravity, postBoilVolume, preFermentationVolume);
        }

        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(Grams amount, Factor alphaAcids, SpecificGravity originalGravity, Liters postBoilVolume, Liters preFermentationVolume) {
        double volume = Math.max(postBoilVolume.value(), preFermentationVolume.value());
        double og = originalGravity.value();
        double gravityAdjustment = (og < 1.050) ? 1 : (og - 1.050) / 0.2;

        double IBUs = getUtilization() * amount.value() * alphaAcids.value() * 1000;
        IBUs = IBUs / (volume + gravityAdjustment);
        return IBUs;
    }

    public IBU getIBUsFromAddition(Grams amount, Factor alphaAcids, SpecificGravity originalGravity, Liters postBoilVolume, Liters preFermentationVolume) {
        return new IBU(getRawIBUsFromAddition(amount, alphaAcids, originalGravity, postBoilVolume, preFermentationVolume));
    }

    private double getUtilization() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private double concentrationFactor(Liters finalVolume, Liters boilVolume) {
        return finalVolume.value() / boilVolume.value();
    }

    private double hoppingRateFactor(double concentrationFactor, double desiredIBUs) {
        return ((concentrationFactor * desiredIBUs) / 260) + 1;
    }
}
