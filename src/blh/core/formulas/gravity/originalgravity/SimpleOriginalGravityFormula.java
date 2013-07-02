package blh.core.formulas.gravity.originalgravity;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.ExtractPotential;
import blh.core.units.Factor;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.volume.Liters;
import blh.core.units.weight.Kilograms;

/**
 * Taken from
 * http://byo.com/european-pale-lager/item/409-calculating-gravity-bitterness-and-color-techniques
 *
 * SGP(GP/gal) = [W(lb.) * EP(GP/lb.) * EE] / V(gallons) --> SGP(GP/l) = [W(kg) * EP(GP/kg) * EE] / V(l)
 *
 * SG = SGP / 1000
 *
 * @author thinner
 */
public class SimpleOriginalGravityFormula implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        Kilograms grainWeight = context.totalGrainWeight.value();
        Liters volume = getVolume(context);
        ExtractPotential extractPotential = context.totalExtractPotential.value();
        Factor extractionEffiency = context.extractionEffiency.value();

        return calc(grainWeight, extractPotential, extractionEffiency, volume);
    }

    protected Liters getVolume(FullContext context) {
        return context.preBoilVolume.value();
    }

    public SpecificGravity calc(Kilograms grainWeight, ExtractPotential extractPotential, Factor extractionEffiency, Liters volume) {
        double nominator = grainWeight.value() * extractPotential.value() * extractionEffiency.value();
        double specificGravityPoints = nominator / volume.value();

        return new SpecificGravity(specificGravityPoints / 1000);
    }
}
