package blh.core.beerxml.types;

import blh.core.units.CO2Volumes;
import blh.core.units.alcohol.ABV;
import blh.core.units.bitterness.IBU;
import blh.core.units.color.SRM;
import blh.core.units.gravity.SpecificGravity;

/**
 *
 * @author thinner
 */
public class Style implements BeerXMLRecord {

    public final String name;
    public final String category;
    public final String categoryNumber;
    public final String styleLetter;
    public final String styleGuide;
    public final TYPE type;
    public final SpecificGravity originalGravityMin, originalGravityMax;
    public final SpecificGravity finalGravityMin, finalGravityMax;
    public final IBU IBUMin, IBUMax;
    public final SRM colorMin, colorMax;
    public final CO2Volumes carbonationMin, carbonationMax;
    public final ABV alcoholMin, alcoholMax;
    public final String notes;
    public final String profile;
    public final String ingredients;
    public final String examples;

    public static enum TYPE {

        LAGER, ALE, MEAD, WHEAT, MIXED, CIDER
    }

    public Style(String name, String category, String categoryNumber, String styleLetter, String styleGuide, TYPE type, SpecificGravity originalGravityMin, SpecificGravity originalGravityMax, SpecificGravity finalGravityMin, SpecificGravity finalGravityMax, IBU IBUMin, IBU IBUMax, SRM colorMin, SRM colorMax, CO2Volumes carbonationMin, CO2Volumes carbonationMax, ABV alcoholMin, ABV alcoholMax, String notes, String profile, String ingredients, String examples) {
        this.name = name;
        this.category = category;
        this.categoryNumber = categoryNumber;
        this.styleLetter = styleLetter;
        this.styleGuide = styleGuide;
        this.type = type;
        this.originalGravityMin = originalGravityMin;
        this.originalGravityMax = originalGravityMax;
        this.finalGravityMin = finalGravityMin;
        this.finalGravityMax = finalGravityMax;
        this.IBUMin = IBUMin;
        this.IBUMax = IBUMax;
        this.colorMin = colorMin;
        this.colorMax = colorMax;
        this.carbonationMin = carbonationMin;
        this.carbonationMax = carbonationMax;
        this.alcoholMin = alcoholMin;
        this.alcoholMax = alcoholMax;
        this.notes = notes;
        this.profile = profile;
        this.ingredients = ingredients;
        this.examples = examples;
    }
}
