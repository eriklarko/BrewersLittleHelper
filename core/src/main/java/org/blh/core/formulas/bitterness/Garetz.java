package org.blh.core.formulas.bitterness;

import org.blh.core.formulas.Formula;
import org.blh.core.recipe.HopAddition;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.distance.Meters;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.time.Minutes;
import org.blh.core.units.volume.Liters;

/**
 * Mark Garetz Using Hops, The Complete Guide to Hops for the Craft Brewer, Hop
 * Tech 1994
 * 
 * Taken from http://realbeer.com/hops/FAQ.html
 *
 * @author thinner
 */
public class Garetz implements Formula<IBU> {

    /**
     * Index maps to minutes in boil
     */
    private static final int[] utilizationTable = new int[18];

    static {
        utilizationTable[0] = 0;   //  0 - 5
        utilizationTable[1] = 0;   //  6 - 10
        utilizationTable[2] = 2;   // 11 - 15
        utilizationTable[3] = 5;   // 16 - 20
        utilizationTable[4] = 8;   // 21 - 25
        utilizationTable[5] = 11;  // 26 - 30
        utilizationTable[6] = 14;  // 21 - 35
        utilizationTable[7] = 16;  // 36 - 40
        utilizationTable[8] = 18;  // 41 - 45
        utilizationTable[9] = 19;  // 46 - 50
        utilizationTable[10] = 20; // 51 - 60
        utilizationTable[11] = 20; // 56 - 65
        utilizationTable[12] = 21; // 61 - 75
        utilizationTable[13] = 21; // 66 - 70
        utilizationTable[14] = 22; // 71 - 75
        utilizationTable[15] = 22; // 76 - 80
        utilizationTable[16] = 23; // 81 - 85
        utilizationTable[17] = 23; // 86 - 90
    }

    private static int closestHigherFive(int n) {
        if (n == 0) return 5;

        int mod = n % 5;
        return 5 - mod + n;
    }

    private static int getUtilizationFromTable(Minutes timeInBoil) {
        int i = closestHigherFive((int) Math.round(timeInBoil.inexactValue()));
        return utilizationTable[i / 5 - 1];
    }

    /**
     * From http://www.beerandloafing.org/hbd/fetch.php?id=30675
     */
    private static double getUtilization(Minutes timeInBoil) {
        if(timeInBoil.inexactValue() < 11.0) return(0.0);
        return 7.2994 + 15.0746 * Math.tanh((timeInBoil.inexactValue() - 21.86) / 24.71);
    }

    @Override
    public IBU calc(FullContext context) {
        double totalIBUs = 0;
        for (HopAddition addition : context.getIngredientsList().getHopAdditions()) {
            totalIBUs += getRawIBUsFromAddition(addition, context.volumePre(context.FINAL),
                     context.getBoilVolumeAtMinutesLeft(addition.getTimeInBoil()),
                     context.preBoilGravity.value(), context.elevation.value());
        }
        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, Liters finalVolume, Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        double IBUs = 1, oldIBUs;
        do {
            oldIBUs = IBUs;

            double utilization = getUtilization(addition.getTimeInBoil());

            double combinedAdjustments = getCombinedAdjustments(finalVolume, boilVolume, preBoilGravity, elevation, IBUs);
            IBUs = utilization * addition.getAmount().inexactValue() * addition.getHop().alphaAcids.inexactValue() * 0.1;
            IBUs = IBUs / (boilVolume.inexactValue() * combinedAdjustments);
        } while (Math.abs(IBUs - oldIBUs) > 0.01);

        return IBUs;
    }
    
    public IBU getIBUsFromAddition(HopAddition addition, Liters finalVolume, Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        return new IBU(getRawIBUsFromAddition(addition, finalVolume, boilVolume, preBoilGravity, elevation));
    }

    private double gravityFactor(double boilGravity) {
        return ((boilGravity -  1.050) / 0.2) + 1;
    }

    private double boilGravity(double concentrationFactor, SpecificGravity preBoilGravity) {
        return (concentrationFactor * (preBoilGravity.inexactValue() - 1)) + 1;
    }

    private double concentrationFactor(Liters finalVolume, Liters boilVolume) {
        return finalVolume.inexactValue() / boilVolume.inexactValue();
    }

    private double hoppingRateFactor(double concentrationFactor, double desiredIBUs) {
        return ((concentrationFactor * desiredIBUs) / 260) + 1;
    }

    private double temperatureFactor(Meters elevation) {
        return ((elevation.inexactValue() / 167.64) * 0.02) + 1;
    }

    private double getCombinedAdjustments(Liters finalVolume, Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation, double desiredIBUs) {
        double concentrationFactor = concentrationFactor(finalVolume, boilVolume);
        double boilGravity = boilGravity(concentrationFactor, preBoilGravity);

        return gravityFactor(boilGravity) * hoppingRateFactor(concentrationFactor, desiredIBUs) * temperatureFactor(elevation);
    }
}
