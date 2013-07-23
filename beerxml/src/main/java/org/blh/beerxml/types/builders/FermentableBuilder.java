/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.types.Fermentable;
import org.blh.beerxml.types.Fermentable.FERMENTABLE_TYPE;
import org.blh.core.units.Lintner;
import org.blh.core.units.Percentage;
import org.blh.core.units.weight.Kilograms;

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

    FermentableBuilder setType(FERMENTABLE_TYPE type);

    FermentableBuilder setYield(Percentage yield);
    
}
