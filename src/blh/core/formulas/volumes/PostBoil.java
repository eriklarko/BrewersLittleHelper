package blh.core.formulas.volumes;

import blh.core.uncategorized.FullContext;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostBoil implements VolumeStepFormula {

    @Override
    public Liters postStep(Liters preStep, FullContext context) {
        Minutes boilTime = context.boilTime.value();
        Liters boilOffPerHour = context.boilOffPerHour.value();
        
        return calc(boilTime, boilOffPerHour, preStep);
    }

    public Liters calc(Minutes boilTime, Liters boilOffPerHour, Liters preBoilVolume) {
        double boilTimeInHours = boilTime.value() / 60d;
        double totalBoilOff = boilOffPerHour.value() * boilTimeInHours;

        return new Liters(preBoilVolume.value() - totalBoilOff);
    }
}
