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

    public static final String NAME = "NAME";
    public static final String CATEGORY = "CATEGORY";
    public static final String CATEGORY_NUMBER = "CATEGORY_NUMBER";
    public static final String STYLE_LETTER = "STYLE_LETTER";
    public static final String STYLE_GUIDE = "STYLE_GUIDE";
    public static final String TYPE = "TYPE";
    public static final String ORIGINAL_GRAVITY_MIN = "OG_MIN";
    public static final String ORIGINAL_GRAVITY_MAX = "OG_MAX";
    public static final String FINAL_GRAVITY_MIN = "FG_MIN";
    public static final String FINAL_GRAVITY_MAX = "FG_MAX";
    public static final String IBU_MIN = "IBU_MIN";
    public static final String IBU_MAX = "IBU_MAX";
    public static final String COLOR_MIN = "COLOR_MIN";
    public static final String COLOR_MAX = "COLOR_MAX";
    public static final String CARBONATION_MIN = "CARB_MIN";
    public static final String CARBONATION_MAX = "CARB_MAX";
    public static final String ALCOHOL_MIN = "ABV_MIN";
    public static final String ALCOHOL_MAX = "ABV_MAX";
    public static final String NOTES = "NOTES";
    public static final String PROFILE = "PROFILE";
    public static final String INGREDIENTS = "INGREDIENTS";
    public static final String EXAMPLES = "EXAMPLES";
    
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
