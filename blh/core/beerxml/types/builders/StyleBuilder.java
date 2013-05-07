/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Style.TYPE;
import blh.core.units.CO2Volumes;
import blh.core.units.alcohol.ABV;
import blh.core.units.bitterness.IBU;
import blh.core.units.color.SRM;
import blh.core.units.gravity.SpecificGravity;

/**
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

    StyleBuilder setIBUMax(IBU IBUMax);

    StyleBuilder setIBUMin(IBU IBUMin);

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
