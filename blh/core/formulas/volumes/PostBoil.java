package blh.core.formulas.volumes;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.time.Minutes;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class PostBoil implements Formula<Liters> {

    @Override
    public Liters calc(FullContext context) {
        Minutes boilTime = context.boilTime.value();
        Liters boilOffPerHour = context.boilOffPerHour.value();
        Liters preBoilVolume = context.preBoilVolume.value();
        
        return calc(boilTime, boilOffPerHour, preBoilVolume);
    }

    public Liters calc(Minutes boilTime, Liters boilOffPerHour, Liters preBoilVolume) {
        double boilTimeInHours = boilTime.value() / 60d;
        double totalBoilOff = boilOffPerHour.value() * boilTimeInHours;

        return new Liters(preBoilVolume.value() - totalBoilOff);
    }
}
