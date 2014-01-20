package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.gravity.SpecificGravity;

/**
 * Implementation of the BeerXML Equipment record.
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
    private final String name;
    private final String category;
    private final String categoryNumber;
    private final String styleLetter;
    private final String styleGuide;
    private final TYPE type;
    private final SpecificGravity originalGravityMin, originalGravityMax;
    private final SpecificGravity finalGravityMin, finalGravityMax;
    private final IBU ibuMin, ibuMax;
    private final SRM colorMin, colorMax;
    private final CO2Volumes carbonationMin, carbonationMax;
    private final ABV alcoholMin, alcoholMax;
    private final String notes;
    private final String profile;
    private final String ingredients;
    private final String examples;

    /**
     * Valid values for style type
     */
    public static enum TYPE {

        LAGER, ALE, MEAD, WHEAT, MIXED, CIDER
    }

    public Style(String name, String category, String categoryNumber, String styleLetter,
            String styleGuide, TYPE type, SpecificGravity originalGravityMin,
            SpecificGravity originalGravityMax, SpecificGravity finalGravityMin,
            SpecificGravity finalGravityMax, IBU ibuMin, IBU ibuMax, SRM colorMin,
            SRM colorMax, CO2Volumes carbonationMin, CO2Volumes carbonationMax,
            ABV alcoholMin, ABV alcoholMax, String notes, String profile,
            String ingredients, String examples) {
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
        this.ibuMin = ibuMin;
        this.ibuMax = ibuMax;
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

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public String getStyleLetter() {
        return styleLetter;
    }

    public String getStyleGuide() {
        return styleGuide;
    }

    public TYPE getType() {
        return type;
    }

    public SpecificGravity getOriginalGravityMin() {
        return originalGravityMin;
    }

    public SpecificGravity getOriginalGravityMax() {
        return originalGravityMax;
    }

    public SpecificGravity getFinalGravityMin() {
        return finalGravityMin;
    }

    public SpecificGravity getFinalGravityMax() {
        return finalGravityMax;
    }

    public IBU getIBUMin() {
        return ibuMin;
    }

    public IBU getIBUMax() {
        return ibuMax;
    }

    public SRM getColorMin() {
        return colorMin;
    }

    public SRM getColorMax() {
        return colorMax;
    }

    public CO2Volumes getCarbonationMin() {
        return carbonationMin;
    }

    public CO2Volumes getCarbonationMax() {
        return carbonationMax;
    }

    public ABV getAlcoholMin() {
        return alcoholMin;
    }

    public ABV getAlcoholMax() {
        return alcoholMax;
    }

    public String getNotes() {
        return notes;
    }

    public String getProfile() {
        return profile;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getExamples() {
        return examples;
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
        tagsAndValues.put(IBU_MIN, Utils.toStringOrNull(ibuMin));
        tagsAndValues.put(IBU_MAX, Utils.toStringOrNull(ibuMax));
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

    // This is as complex as it needs to be
    @Override
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
        if (!Objects.equals(this.ibuMin, other.ibuMin)) {
            return false;
        }
        if (!Objects.equals(this.ibuMax, other.ibuMax)) {
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
        return Objects.equals(this.examples, other.examples);
    }

    // This hash is ok
    @Override
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
        hash = 37 * hash + Objects.hashCode(this.ibuMin);
        hash = 37 * hash + Objects.hashCode(this.ibuMax);
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
