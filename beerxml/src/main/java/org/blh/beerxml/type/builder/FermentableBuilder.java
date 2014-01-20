package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Fermentable.FERMENTABLE_TYPE;
import org.blh.core.unit.Lintner;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.weight.Kilograms;

/**
 * Builder for the Fermentable type.
 *
 * @author thinner
 */
public interface FermentableBuilder extends Builder<Fermentable> {

    FermentableBuilder setAddAfterBoil(boolean addAfterBoil);

    FermentableBuilder setAmount(Kilograms amount);

    FermentableBuilder setCoarseFineDiff(Percentage coarseFineDiff);

    FermentableBuilder setColorString(String colorString);

    FermentableBuilder setDiastaticPower(Lintner diastaticPower);

    FermentableBuilder setIBUGallonsPerPound(double ibuGallonsPerPound);

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
