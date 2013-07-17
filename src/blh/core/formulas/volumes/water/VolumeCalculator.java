package blh.core.formulas.volumes.water;

import blh.core.uncategorized.FullContext;
import blh.core.units.volume.Liters;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 15, 2013 8:45:32 PM
 */
public class VolumeCalculator {

    private final LinkedList<BrewStep> brewSteps;

    public VolumeCalculator(LinkedList<BrewStep> brewSteps) {
        this.brewSteps = brewSteps;
    }

    public Liters pre(BrewStep target, FullContext context) {

        return null;
    }

    public Liters post(BrewStep target, FullContext context) {

        return null;
    }

    private BrewStep findLaterMeasuredBrewStep(BrewStep end) {
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(end));
        while (it.hasNext()) {
            BrewStep step = it.next();
            if (step.isMeasured()) {
                return step;
            }
        }

        return null;
    }

    private BrewStep findEarlierMeasuredBrewStep(BrewStep end) {
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(end));
        while (it.hasPrevious()) {
            BrewStep step = it.previous();
            if (step.isMeasured()) {
                return step;
            }
        }

        return null;
    }

    private Liters calculateVolumeFromEarlierStep(BrewStep end, FullContext context) {
        BrewStep start = findEarlierMeasuredBrewStep(end);
        Liters base = start.getMeasuredLiters();
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(start));
        while (it.hasNext()) {
            BrewStep step = it.next();
            if(step == end) {
                return base;
            }
            base = step.calculateVolumeAfterStep(base, context);
        }

        return null;
    }

    private Liters calculateVolumeFromLaterStep(BrewStep end, FullContext context) {
        BrewStep start = findEarlierMeasuredBrewStep(end);
        Liters base = start.getMeasuredLiters();
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(end));
        while (it.hasPrevious()) {
            BrewStep step = it.previous();
            base = step.calculateVolumeBeforeStep(base, context);
        }

        return base;
    }
}
