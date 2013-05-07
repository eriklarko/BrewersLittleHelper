/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Fermentable.TYPE;
import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.weight.Kilograms;

/**
 *
 * @author thinner
 */
public interface FermentableBuilder extends Builder<Fermentable> {

    FermentableBuilder setAddAfterBoil(boolean addAfterBoil);

    FermentableBuilder setAmount(Kilograms amount);

    FermentableBuilder setCoarseFineDiff(Percentage coarseFineDiff);

    FermentableBuilder setColorString(String colorString);

    FermentableBuilder setDiastaticPower(Lintner diastaticPower);

    FermentableBuilder setIBUGallonsPerPound(double IBUGallonsPerPound);

    FermentableBuilder setMaxInBatch(Percentage maxInBatch);

    FermentableBuilder setMoisture(Percentage moisture);

    FermentableBuilder setName(String name);

    FermentableBuilder setNotes(String notes);

    FermentableBuilder setOrigin(String origin);

    FermentableBuilder setProtein(Percentage protein);

    FermentableBuilder setRecommendMash(boolean recommendMash);

    FermentableBuilder setSupplier(String supplier);

    FermentableBuilder setType(TYPE type);

    FermentableBuilder setYield(Percentage yield);
    
}
