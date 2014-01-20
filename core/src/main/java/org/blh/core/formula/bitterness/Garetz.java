package org.blh.core.formula.bitterness;

import org.blh.core.recipe.HopAddition;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.distance.Meters;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;

/**
 * Mark Garetz Using Hops, The Complete Guide to Hops for the Craft Brewer, Hop
 * Tech 1994
 *
 * Taken from http://realbeer.com/hops/FAQ.html
 *
 * @author thinner
 */
public class Garetz {

    /**
     * Index maps to minutes in boil
     */
    private static final int[] UTILIZATION_TABLE = new int[18];

    static {
        UTILIZATION_TABLE[0] = 0;   //  0 - 5
        UTILIZATION_TABLE[1] = 0;   //  6 - 10
        UTILIZATION_TABLE[2] = 2;   // 11 - 15
        UTILIZATION_TABLE[3] = 5;   // 16 - 20
        UTILIZATION_TABLE[4] = 8;   // 21 - 25
        UTILIZATION_TABLE[5] = 11;  // 26 - 30
        UTILIZATION_TABLE[6] = 14;  // 21 - 35
        UTILIZATION_TABLE[7] = 16;  // 36 - 40
        UTILIZATION_TABLE[8] = 18;  // 41 - 45
        UTILIZATION_TABLE[9] = 19;  // 46 - 50
        UTILIZATION_TABLE[10] = 20; // 51 - 60
        UTILIZATION_TABLE[11] = 20; // 56 - 65
        UTILIZATION_TABLE[12] = 21; // 61 - 75
        UTILIZATION_TABLE[13] = 21; // 66 - 70
        UTILIZATION_TABLE[14] = 22; // 71 - 75
        UTILIZATION_TABLE[15] = 22; // 76 - 80
        UTILIZATION_TABLE[16] = 23; // 81 - 85
        UTILIZATION_TABLE[17] = 23; // 86 - 90
    }

    private static int closestHigherFive(int n) {
        if (n == 0) {
            return 5;
        }

        int mod = n % 5;
        return 5 - mod + n;
    }

    private static int getUtilizationFromTable(Minutes timeInBoil) {
        int i = closestHigherFive((int) Math.round(timeInBoil.value()));
        return UTILIZATION_TABLE[i / 5 - 1];
    }

    /**
     * From http://www.beerandloafing.org/hbd/fetch.php?id=30675
     */
    private static double getUtilization(Minutes timeInBoil) {
        if (timeInBoil.value() < 11.0) {
            return 0.0;
        }
        return 7.2994 + 15.0746 * Math.tanh((timeInBoil.value() - 21.86) / 24.71);
    }

    public IBU calc(Iterable<HopAddition> hopAdditions, Liters finalVolume,
            Liters currentBoilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        double totalIBUs = 0;
        for (HopAddition addition : hopAdditions) {
            totalIBUs += getRawIBUsFromAddition(addition, finalVolume, currentBoilVolume,
                    preBoilGravity, elevation);
        }
        return new IBU(totalIBUs);
    }

    private double getRawIBUsFromAddition(HopAddition addition, Liters finalVolume,
            Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        double ibus = 1, oldIBUs;
        do {
            oldIBUs = ibus;

            double utilization = getUtilization(addition.getTimeInBoil());

            double combinedAdjustments = getCombinedAdjustments(finalVolume, boilVolume, preBoilGravity, elevation, ibus);
            ibus = utilization * addition.getAmount().value() * addition.getHop().getAlphaAcids().value() * 0.1;
            ibus = ibus / (boilVolume.value() * combinedAdjustments);
        } while (Math.abs(ibus - oldIBUs) > 0.01);

        return ibus;
    }

    public IBU getIBUsFromAddition(HopAddition addition, Liters finalVolume,
            Liters boilVolume, SpecificGravity preBoilGravity, Meters elevation) {
        return new IBU(getRawIBUsFromAddition(addition, finalVolume, boilVolume, preBoilGravity, elevation));
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

    private double getCombinedAdjustments(Liters finalVolume, Liters boilVolume,
            SpecificGravity preBoilGravity, Meters elevation, double desiredIBUs) {
        double concentrationFactor = concentrationFactor(finalVolume, boilVolume);
        double boilGravity = boilGravity(concentrationFactor, preBoilGravity);

        return gravityFactor(boilGravity) * hoppingRateFactor(concentrationFactor, desiredIBUs) * temperatureFactor(elevation);
    }
}
