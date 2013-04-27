package formulas.gravity.originalgravity;

import formulas.Formula;
import uncategorized.FullContext;
import units.ExtractPotential;
import units.gravity.SpecificGravity;
import units.volume.Liters;
import units.weight.Kilograms;

/**
 * Taken from http://byo.com/european-pale-lager/item/409-calculating-gravity-bitterness-and-color-techniques
 * 
 * SGP(GP/gal) = [W(lb.) * EP(GP/lb.) * EE] / V(gallons) -->
 * SGP(GP/l)   = [W(kg)  * EP(GP/kg)  * EE] / V(l)
 * 
 * SG = SGP / 1000
 * 
 * @author thinner
 */
public class SimpleOriginalGravityFormula implements Formula<SpecificGravity> {

    @Override
    public SpecificGravity calc(FullContext context) {
        Kilograms grainWeight = context.getTotalGrainWeight();
        Liters volume = getVolume(context);
        ExtractPotential extractPotential = context.getTotalExtractPotential();
        double extractionEffiency = context.getExtractionEffiency();
        
        return calc(grainWeight, extractPotential, extractionEffiency, volume);
    }
    
    public Liters getVolume(FullContext context) {
        return context.getPreBoilVolume();
    }

    public SpecificGravity calc(Kilograms grainWeight, ExtractPotential extractPotential, double extractionEffiency, Liters volume) {
        double nominator = grainWeight.value() * extractPotential.value() * extractionEffiency;
        double specificGravityPoints = nominator / volume.value(); 
        
        return new SpecificGravity(specificGravityPoints / 1000);
    }
}
