package org.blh.beerxml.types;

import org.blh.beerxml.Utils;
import org.blh.core.units.CO2Volumes;
import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.color.SRM;
import org.blh.core.units.gravity.SpecificGravity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>();

        tagsAndValues.put(NAME, Utils.toStringOrNull(name));
        tagsAndValues.put(CATEGORY, Utils.toStringOrNull(category));
        tagsAndValues.put(CATEGORY_NUMBER, Utils.toStringOrNull(categoryNumber));
        tagsAndValues.put(STYLE_LETTER, Utils.toStringOrNull(styleLetter));
        tagsAndValues.put(STYLE_GUIDE, Utils.toStringOrNull(styleGuide));
        tagsAndValues.put(TYPE, Utils.toStringOrNull(type));
        tagsAndValues.put(ORIGINAL_GRAVITY_MIN, Utils.toStringOrNull(originalGravityMin));
        tagsAndValues.put(ORIGINAL_GRAVITY_MAX, Utils.toStringOrNull(originalGravityMax));
        tagsAndValues.put(FINAL_GRAVITY_MIN, Utils.toStringOrNull(finalGravityMin));
        tagsAndValues.put(FINAL_GRAVITY_MAX, Utils.toStringOrNull(finalGravityMax));
        tagsAndValues.put(IBU_MIN, Utils.toStringOrNull(IBUMin));
        tagsAndValues.put(IBU_MAX, Utils.toStringOrNull(IBUMax));
        tagsAndValues.put(COLOR_MIN, Utils.toStringOrNull(colorMin));
        tagsAndValues.put(COLOR_MAX, Utils.toStringOrNull(colorMax));
        tagsAndValues.put(CARBONATION_MIN, Utils.toStringOrNull(carbonationMin));
        tagsAndValues.put(CARBONATION_MAX, Utils.toStringOrNull(carbonationMax));
        tagsAndValues.put(ALCOHOL_MIN, Utils.toStringOrNull(alcoholMin));
        tagsAndValues.put(ALCOHOL_MAX, Utils.toStringOrNull(alcoholMax));
        tagsAndValues.put(NOTES, Utils.toStringOrNull(notes));
        tagsAndValues.put(PROFILE, Utils.toStringOrNull(profile));
        tagsAndValues.put(INGREDIENTS, Utils.toStringOrNull(ingredients));
        tagsAndValues.put(EXAMPLES, Utils.toStringOrNull(examples));

        return tagsAndValues;
    }
    
    @Override
    public List<BeerXMLRecord> getSubRecords() {
        return null;
    }

    @Override
    public List<BeerXMLRecordSet> getSubRecordSets() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Style other = (Style) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.categoryNumber, other.categoryNumber)) {
            return false;
        }
        if (!Objects.equals(this.styleLetter, other.styleLetter)) {
            return false;
        }
        if (!Objects.equals(this.styleGuide, other.styleGuide)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.originalGravityMin, other.originalGravityMin)) {
            return false;
        }
        if (!Objects.equals(this.originalGravityMax, other.originalGravityMax)) {
            return false;
        }
        if (!Objects.equals(this.finalGravityMin, other.finalGravityMin)) {
            return false;
        }
        if (!Objects.equals(this.finalGravityMax, other.finalGravityMax)) {
            return false;
        }
        if (!Objects.equals(this.IBUMin, other.IBUMin)) {
            return false;
        }
        if (!Objects.equals(this.IBUMax, other.IBUMax)) {
            return false;
        }
        if (!Objects.equals(this.colorMin, other.colorMin)) {
            return false;
        }
        if (!Objects.equals(this.colorMax, other.colorMax)) {
            return false;
        }
        if (!Objects.equals(this.carbonationMin, other.carbonationMin)) {
            return false;
        }
        if (!Objects.equals(this.carbonationMax, other.carbonationMax)) {
            return false;
        }
        if (!Objects.equals(this.alcoholMin, other.alcoholMin)) {
            return false;
        }
        if (!Objects.equals(this.alcoholMax, other.alcoholMax)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        if (!Objects.equals(this.examples, other.examples)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.category);
        hash = 37 * hash + Objects.hashCode(this.categoryNumber);
        hash = 37 * hash + Objects.hashCode(this.styleLetter);
        hash = 37 * hash + Objects.hashCode(this.styleGuide);
        hash = 37 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 37 * hash + Objects.hashCode(this.originalGravityMin);
        hash = 37 * hash + Objects.hashCode(this.originalGravityMax);
        hash = 37 * hash + Objects.hashCode(this.finalGravityMin);
        hash = 37 * hash + Objects.hashCode(this.finalGravityMax);
        hash = 37 * hash + Objects.hashCode(this.IBUMin);
        hash = 37 * hash + Objects.hashCode(this.IBUMax);
        hash = 37 * hash + Objects.hashCode(this.colorMin);
        hash = 37 * hash + Objects.hashCode(this.colorMax);
        hash = 37 * hash + Objects.hashCode(this.carbonationMin);
        hash = 37 * hash + Objects.hashCode(this.carbonationMax);
        hash = 37 * hash + Objects.hashCode(this.alcoholMin);
        hash = 37 * hash + Objects.hashCode(this.alcoholMax);
        hash = 37 * hash + Objects.hashCode(this.notes);
        hash = 37 * hash + Objects.hashCode(this.profile);
        hash = 37 * hash + Objects.hashCode(this.ingredients);
        hash = 37 * hash + Objects.hashCode(this.examples);
        return hash;
    }
}
