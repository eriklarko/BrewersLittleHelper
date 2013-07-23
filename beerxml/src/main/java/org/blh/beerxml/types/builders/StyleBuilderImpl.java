package org.blh.beerxml.types.builders;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.Style;
import org.blh.beerxml.types.Style.TYPE;
import org.blh.core.units.CO2Volumes;
import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.bitterness.IBU;
import org.blh.core.units.color.SRM;
import org.blh.core.units.gravity.SpecificGravity;

public class StyleBuilderImpl implements StyleBuilder{

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

    public StyleBuilderImpl() {
    }

    @Override
    public StyleBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public StyleBuilderImpl setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public StyleBuilderImpl setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
        return this;
    }

    @Override
    public StyleBuilderImpl setStyleLetter(String styleLetter) {
        this.styleLetter = styleLetter;
        return this;
    }

    @Override
    public StyleBuilderImpl setStyleGuide(String styleGuide) {
        this.styleGuide = styleGuide;
        return this;
    }

    @Override
    public StyleBuilderImpl setType(TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public StyleBuilderImpl setOriginalGravityMin(SpecificGravity originalGravityMin) {
        this.originalGravityMin = originalGravityMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setOriginalGravityMax(SpecificGravity originalGravityMax) {
        this.originalGravityMax = originalGravityMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setFinalGravityMin(SpecificGravity finalGravityMin) {
        this.finalGravityMin = finalGravityMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setFinalGravityMax(SpecificGravity finalGravityMax) {
        this.finalGravityMax = finalGravityMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setIBUMin(IBU IBUMin) {
        this.IBUMin = IBUMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setIBUMax(IBU IBUMax) {
        this.IBUMax = IBUMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setColorMin(SRM colorMin) {
        this.colorMin = colorMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setColorMax(SRM colorMax) {
        this.colorMax = colorMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setCarbonationMin(CO2Volumes carbonationMin) {
        this.carbonationMin = carbonationMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setCarbonationMax(CO2Volumes carbonationMax) {
        this.carbonationMax = carbonationMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setAlcoholMin(ABV alcoholMin) {
        this.alcoholMin = alcoholMin;
        return this;
    }

    @Override
    public StyleBuilderImpl setAlcoholMax(ABV alcoholMax) {
        this.alcoholMax = alcoholMax;
        return this;
    }

    @Override
    public StyleBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public StyleBuilderImpl setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public StyleBuilderImpl setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public StyleBuilderImpl setExamples(String examples) {
        this.examples = examples;
        return this;
    }

    @Override
    public StyleBuilderImpl set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case Style.NAME:
                this.name = value;
                break;
            case Style.CATEGORY:
                this.category = value;
                break;
            case Style.CATEGORY_NUMBER:
                this.categoryNumber = value;
                break;
            case Style.STYLE_LETTER:
                this.styleLetter = value;
                break;
            case Style.STYLE_GUIDE:
                this.styleGuide = value;
                break;
            case Style.TYPE:
                this.type = TYPE.valueOf(value.toUpperCase());
                break;
            case Style.ORIGINAL_GRAVITY_MIN:
                this.originalGravityMin = new SpecificGravity(Double.parseDouble(value));
                break;
            case Style.ORIGINAL_GRAVITY_MAX:
                this.originalGravityMax = new SpecificGravity(Double.parseDouble(value));
                break;
            case Style.FINAL_GRAVITY_MIN:
                this.finalGravityMin = new SpecificGravity(Double.parseDouble(value));
                break;
            case Style.FINAL_GRAVITY_MAX:
                this.finalGravityMax = new SpecificGravity(Double.parseDouble(value));
                break;
            case Style.IBU_MIN:
                this.IBUMin = new IBU(Double.parseDouble(value));
                break;
            case Style.IBU_MAX:
                this.IBUMax = new IBU(Double.parseDouble(value));
                break;
            case Style.COLOR_MIN:
                this.colorMin = new SRM(Double.parseDouble(value));
                break;
            case Style.COLOR_MAX:
                this.colorMax = new SRM(Double.parseDouble(value));
                break;
            case Style.CARBONATION_MIN:
                this.carbonationMin = new CO2Volumes(Double.parseDouble(value));
                break;
            case Style.CARBONATION_MAX:
                this.carbonationMax = new CO2Volumes(Double.parseDouble(value));
                break;
            case Style.ALCOHOL_MIN:
                this.alcoholMin = new ABV(Double.parseDouble(value));
                break;
            case Style.ALCOHOL_MAX:
                this.alcoholMax = new ABV(Double.parseDouble(value));
                break;
            case Style.NOTES:
                this.notes = value;
                break;
            case Style.PROFILE:
                this.profile = value;
                break;
            case Style.INGREDIENTS:
                this.ingredients = value;
                break;
            case Style.EXAMPLES:
                this.examples = value;
                break;
            default:
                throw new UnknownTagException("Unknown style tag: " + tagName);
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
