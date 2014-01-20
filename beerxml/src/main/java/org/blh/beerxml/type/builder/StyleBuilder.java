package org.blh.beerxml.type.builder;

import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Style.TYPE;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.gravity.SpecificGravity;

/**
 * Builder for the Style type.
 *
 * @author thinner
 */
public interface StyleBuilder extends Builder<Style> {

    StyleBuilder setAlcoholMax(ABV alcoholMax);

    StyleBuilder setAlcoholMin(ABV alcoholMin);

    StyleBuilder setCarbonationMax(CO2Volumes carbonationMax);

    StyleBuilder setCarbonationMin(CO2Volumes carbonationMin);

    StyleBuilder setCategory(String category);

    StyleBuilder setCategoryNumber(String categoryNumber);

    StyleBuilder setColorMax(SRM colorMax);

    StyleBuilder setColorMin(SRM colorMin);

    StyleBuilder setExamples(String examples);

    StyleBuilder setFinalGravityMax(SpecificGravity finalGravityMax);

    StyleBuilder setFinalGravityMin(SpecificGravity finalGravityMin);

    StyleBuilder setIBUMax(IBU ibuMax);

    StyleBuilder setIBUMin(IBU ibuMin);

    StyleBuilder setIngredients(String ingredients);

    StyleBuilder setName(String name);

    StyleBuilder setNotes(String notes);

    StyleBuilder setOriginalGravityMax(SpecificGravity originalGravityMax);

    StyleBuilder setOriginalGravityMin(SpecificGravity originalGravityMin);

    StyleBuilder setProfile(String profile);

    StyleBuilder setStyleGuide(String styleGuide);

    StyleBuilder setStyleLetter(String styleLetter);

    StyleBuilder setType(TYPE type);

}
