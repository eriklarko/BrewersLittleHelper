package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Style.TYPE;
import blh.core.units.CO2Volumes;
import blh.core.units.alcohol.ABV;
import blh.core.units.bitterness.IBU;
import blh.core.units.color.SRM;
import blh.core.units.gravity.SpecificGravity;

public class StyleBuilder implements Builder<Style>{

    private String name;
    private String category;
    private String categoryNumber;
    private String styleLetter;
    private String styleGuide;
    private TYPE type;
    private SpecificGravity originalGravityMin;
    private SpecificGravity originalGravityMax;
    private SpecificGravity finalGravityMin;
    private SpecificGravity finalGravityMax;
    private IBU IBUMin;
    private IBU IBUMax;
    private SRM colorMin;
    private SRM colorMax;
    private CO2Volumes carbonationMin;
    private CO2Volumes carbonationMax;
    private ABV alcoholMin;
    private ABV alcoholMax;
    private String notes;
    private String profile;
    private String ingredients;
    private String examples;

    public StyleBuilder() {
    }

    public StyleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StyleBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public StyleBuilder setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
        return this;
    }

    public StyleBuilder setStyleLetter(String styleLetter) {
        this.styleLetter = styleLetter;
        return this;
    }

    public StyleBuilder setStyleGuide(String styleGuide) {
        this.styleGuide = styleGuide;
        return this;
    }

    public StyleBuilder setType(TYPE type) {
        this.type = type;
        return this;
    }

    public StyleBuilder setOriginalGravityMin(SpecificGravity originalGravityMin) {
        this.originalGravityMin = originalGravityMin;
        return this;
    }

    public StyleBuilder setOriginalGravityMax(SpecificGravity originalGravityMax) {
        this.originalGravityMax = originalGravityMax;
        return this;
    }

    public StyleBuilder setFinalGravityMin(SpecificGravity finalGravityMin) {
        this.finalGravityMin = finalGravityMin;
        return this;
    }

    public StyleBuilder setFinalGravityMax(SpecificGravity finalGravityMax) {
        this.finalGravityMax = finalGravityMax;
        return this;
    }

    public StyleBuilder setIBUMin(IBU IBUMin) {
        this.IBUMin = IBUMin;
        return this;
    }

    public StyleBuilder setIBUMax(IBU IBUMax) {
        this.IBUMax = IBUMax;
        return this;
    }

    public StyleBuilder setColorMin(SRM colorMin) {
        this.colorMin = colorMin;
        return this;
    }

    public StyleBuilder setColorMax(SRM colorMax) {
        this.colorMax = colorMax;
        return this;
    }

    public StyleBuilder setCarbonationMin(CO2Volumes carbonationMin) {
        this.carbonationMin = carbonationMin;
        return this;
    }

    public StyleBuilder setCarbonationMax(CO2Volumes carbonationMax) {
        this.carbonationMax = carbonationMax;
        return this;
    }

    public StyleBuilder setAlcoholMin(ABV alcoholMin) {
        this.alcoholMin = alcoholMin;
        return this;
    }

    public StyleBuilder setAlcoholMax(ABV alcoholMax) {
        this.alcoholMax = alcoholMax;
        return this;
    }

    public StyleBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public StyleBuilder setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public StyleBuilder setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public StyleBuilder setExamples(String examples) {
        this.examples = examples;
        return this;
    }

    @Override
    public StyleBuilder set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                this.name = value;
                break;
            case "CATEGORY":
                this.category = value;
                break;
            case "CATEGORY_NUMBER":
                this.categoryNumber = value;
                break;
            case "STYLE_LETTER":
                this.styleLetter = value;
                break;
            case "STYLE_GUIDE":
                this.styleGuide = value;
                break;
            case "TYPE":
                this.type = TYPE.valueOf(value.toUpperCase());
                break;
            case "OG_MIN":
                this.originalGravityMin = new SpecificGravity(Double.parseDouble(value));
                break;
            case "OG_MAX":
                this.originalGravityMax = new SpecificGravity(Double.parseDouble(value));
                break;
            case "FG_MIN":
                this.finalGravityMin = new SpecificGravity(Double.parseDouble(value));
                break;
            case "FG_MAX":
                this.finalGravityMax = new SpecificGravity(Double.parseDouble(value));
                break;
            case "IBU_MIN":
                this.IBUMin = new IBU(Double.parseDouble(value));
                break;
            case "IBU_MAX":
                this.IBUMax = new IBU(Double.parseDouble(value));
                break;
            case "COLOR_MIN":
                this.colorMin = new SRM(Double.parseDouble(value));
                break;
            case "COLOR_MAX":
                this.colorMax = new SRM(Double.parseDouble(value));
                break;
            case "CARB_MIN":
                this.carbonationMin = new CO2Volumes(Double.parseDouble(value));
                break;
            case "CARB_MAX":
                this.carbonationMax = new CO2Volumes(Double.parseDouble(value));
                break;
            case "ABV_MIN":
                this.alcoholMin = new ABV(Double.parseDouble(value));
                break;
            case "ABV_MAX":
                this.alcoholMax = new ABV(Double.parseDouble(value));
                break;
            case "NOTES":
                this.notes = value;
                break;
            case "PROFILE":
                this.profile = value;
                break;
            case "INGREDIENTS":
                this.ingredients = value;
                break;
            case "EXAMPLES":
                this.examples = value;
                break;
        }

        return this;
    }

    @Override
    public Style create() {
        return new Style(name, category, categoryNumber, styleLetter, styleGuide,
                type, originalGravityMin, originalGravityMax, finalGravityMin,
                finalGravityMax, IBUMin, IBUMax, colorMin, colorMax,
                carbonationMin, carbonationMax, alcoholMin, alcoholMax, notes, profile, ingredients, examples);
    }
}
