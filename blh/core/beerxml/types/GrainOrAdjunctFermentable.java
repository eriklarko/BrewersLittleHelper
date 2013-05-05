package blh.core.beerxml.types;

import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public class GrainOrAdjunctFermentable extends Fermentable {

    public final Percentage coarseFineDiff;
    public final Percentage moisture;
    public final Lintner diastaticPower;
    public final Percentage protein;
    public final boolean recommendMash;

    public GrainOrAdjunctFermentable(String name, TYPE type,
            Kilograms amount, Percentage yield, Lovibond color,
            boolean addAfterBoil, String origin, String supplier, String notes,
            Percentage maxInBatch, Percentage coarseFineDiff,
            Percentage moisture, Lintner diastaticPower, Percentage protein,
            boolean recommendMash) {
        super(0, name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        
        if(!type.equals(TYPE.ADJUNCT) && !type.equals(TYPE.GRAIN)) {
            throw new IllegalArgumentException("The type must be adjunct or grain");
        }
        
        this.coarseFineDiff = coarseFineDiff;
        this.moisture = moisture;
        this.diastaticPower = diastaticPower;
        this.protein = protein;
        this.recommendMash = recommendMash;
    }
}
