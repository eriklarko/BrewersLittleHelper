package blh.core.formulas.bitterness;

import blh.core.formulas.Formula;
import blh.core.recipe.HopAddition;
import blh.core.uncategorized.FullContext;
import blh.core.units.bitterness.IBU;
import blh.core.units.distance.Meters;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Liters;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Mark Garetz Using Hops, The Complete Guide to Hops for the Craft Brewer, Hop
 * Tech 1994
 * <p/>
 * Taken from http://realbeer.com/hops/FAQ.html
 *
 * @author thinner
 */
public class Garetz implements Formula<IBU> {

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;
        for (HopAddition addition : context.getRecipe().getHopAdditions()) {
            totalIBUs += getRawIBUsFromAddition(addition, context.finalVolume.value(),
                    context.getBoilVolumeAtMinutesLeft(addition.getTimeInBoil()),
                    context.preBoilGravity.value(), context.elevation.value());
        }
        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, Liters finalVolume, Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        double IBUs = 1, oldIBUs = 0;
        do {
            oldIBUs = IBUs;

            double combinedAdjustments = getCombinedAdjustments(finalVolume, boilVolume, preBoilGravity, elevation, IBUs);
            IBUs = getUtilization() * addition.getAmount().value() * addition.getHop().alphaAcids * 1000;
            IBUs = IBUs / (boilVolume.value() * combinedAdjustments);
        } while (IBUs - oldIBUs > 0.01);

        return IBUs;
    }

    private double getUtilization() {
        throw new NotImplementedException();
    }

    private double gravityFactor(double boilGravity) {
        return ((boilGravity - 1.050) / 0.2) + 1;
    }

    private double boilGravity(double concentrationFactor, SpecificGravity preBoilGravity) {
        return (concentrationFactor * (preBoilGravity.value() - 1)) + 1;
    }

    private double concentrationFactor(Liters finalVolume, Liters boilVolume) {
        return finalVolume.value() / boilVolume.value();
    }

    private double hoppingRateFactor(double concentrationFactor, double desiredIBUs) {
        return ((concentrationFactor * desiredIBUs) / 260) + 1;
    }

    private double temperatureFactor(Meters elevation) {
        return ((elevation.value() / 167.64) * 0.02) + 1;
    }

    private double getCombinedAdjustments(Liters finalVolume, Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation, double desiredIBUs) {
        double concentrationFactor = concentrationFactor(finalVolume, boilVolume);
        double boilGravity = boilGravity(concentrationFactor, preBoilGravity);

        return gravityFactor(boilGravity) * hoppingRateFactor(concentrationFactor, desiredIBUs) * temperatureFactor(elevation);
    }
}
