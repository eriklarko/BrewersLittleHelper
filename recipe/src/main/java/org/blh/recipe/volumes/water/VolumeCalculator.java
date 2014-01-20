package org.blh.recipe.volumes.water;

import java.util.LinkedList;
import java.util.ListIterator;
import org.blh.core.unit.volume.Liters;
import org.blh.recipe.uncategorized.FullContext;

/**
 * Calculates the water volume before or after a brew step.
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
        BrewStep measuredStep = findEarlierMeasuredBrewStep(target);
        if (measuredStep == null) {
            measuredStep = findLaterMeasuredBrewStep(measuredStep);
            return calculateVolumeFromLaterStep(measuredStep, context, target);
        } else {
            return calculateVolumeFromEarlierStep(measuredStep, context, target);
        }
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

    private Liters calculateVolumeFromEarlierStep(BrewStep end, FullContext context, BrewStep start) {
        Liters base = start.getMeasuredLiters();
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(start));
        while (it.hasNext()) {
            BrewStep step = it.next();
            if (step == end) {
                return base;
            }
            base = step.calculateVolumeAfterStep(base, context);
        }

        return null;
    }

    private Liters calculateVolumeFromLaterStep(BrewStep end, FullContext context, BrewStep start) {
        Liters base = start.getMeasuredLiters();
        ListIterator<BrewStep> it = brewSteps.listIterator(brewSteps.indexOf(end));
        while (it.hasPrevious()) {
            BrewStep step = it.previous();
            base = step.calculateVolumeBeforeStep(base, context);
        }

        return base;
    }
}
