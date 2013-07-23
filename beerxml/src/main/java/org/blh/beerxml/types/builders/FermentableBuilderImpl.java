package org.blh.beerxml.types.builders;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.Fermentable;
import org.blh.beerxml.types.Fermentable.FERMENTABLE_TYPE;
import org.blh.beerxml.types.GrainOrAdjunctFermentable;
import org.blh.beerxml.types.LiquidFermentable;
import org.blh.core.units.Lintner;
import org.blh.core.units.Percentage;
import org.blh.core.units.color.Lovibond;
import org.blh.core.units.color.SRM;
import org.blh.core.units.weight.Kilograms;

public class FermentableBuilderImpl implements FermentableBuilder {

    private String name;
    private FERMENTABLE_TYPE type;
    private Kilograms amount;
    private Percentage yield;
    private String colorString;
    private boolean addAfterBoil;
    private String origin;
    private String supplier;
    private String notes;
    private Percentage maxInBatch;
    private double IBUGallonsPerPound;
    private Percentage coarseFineDiff;
    private Percentage moisture;
    private Lintner diastaticPower;
    private Percentage protein;
    private boolean recommendMash;

    public FermentableBuilderImpl() {
    }

    @Override
    public FermentableBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public FermentableBuilderImpl setType(FERMENTABLE_TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public FermentableBuilderImpl setAmount(Kilograms amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public FermentableBuilderImpl setYield(Percentage yield) {
        this.yield = yield;
        return this;
    }

    @Override
    public FermentableBuilderImpl setColorString(String colorString) {
        this.colorString = colorString;
        return this;
    }

    @Override
    public FermentableBuilderImpl setAddAfterBoil(boolean addAfterBoil) {
        this.addAfterBoil = addAfterBoil;
        return this;
    }

    @Override
    public FermentableBuilderImpl setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public FermentableBuilderImpl setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    @Override
    public FermentableBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public FermentableBuilderImpl setMaxInBatch(Percentage maxInBatch) {
        this.maxInBatch = maxInBatch;
        return this;
    }

    @Override
    public FermentableBuilderImpl setIBUGallonsPerPound(double IBUGallonsPerPound) {
        this.IBUGallonsPerPound = IBUGallonsPerPound;
        return this;
    }

    @Override
    public FermentableBuilderImpl setCoarseFineDiff(Percentage coarseFineDiff) {
        this.coarseFineDiff = coarseFineDiff;
        return this;
    }

    @Override
    public FermentableBuilderImpl setMoisture(Percentage moisture) {
        this.moisture = moisture;
        return this;
    }

    @Override
    public FermentableBuilderImpl setDiastaticPower(Lintner diastaticPower) {
        this.diastaticPower = diastaticPower;
        return this;
    }

    @Override
    public FermentableBuilderImpl setProtein(Percentage protein) {
        this.protein = protein;
        return this;
    }

    @Override
    public FermentableBuilderImpl setRecommendMash(boolean recommendMash) {
        this.recommendMash = recommendMash;
        return this;
    }

    @Override
    public Builder<Fermentable> set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION:
                break;
            case Fermentable.NAME:
                name = value;
                break;
            case Fermentable.TYPE:
                type = Fermentable.FERMENTABLE_TYPE.valueOf(value.replace(" ", "_").toUpperCase());
                break;
            case Fermentable.AMOUNT:
                amount = new Kilograms(Double.parseDouble(value));
                break;
            case Fermentable.YIELD:
                yield = new Percentage(Double.parseDouble(value));
                break;
            case Fermentable.COLOR:
                colorString = value;
                break;
            case Fermentable.ADD_AFTER_BOIL:
                addAfterBoil = Boolean.parseBoolean(value);
                break;
            case Fermentable.ORIGIN:
                origin = value;
                break;
            case Fermentable.SUPPLIER:
                supplier = value;
                break;
            case Fermentable.NOTES:
                notes = value;
                break;
            case GrainOrAdjunctFermentable.COARSE_FINE_DIFF:
                coarseFineDiff = new Percentage(Double.parseDouble(value));
                break;
            case GrainOrAdjunctFermentable.MOISTURE:
                moisture = new Percentage(Double.parseDouble(value));
                break;
            case GrainOrAdjunctFermentable.DIASTATIC_POWER:
                diastaticPower = new Lintner(Double.parseDouble(value));
                break;
            case GrainOrAdjunctFermentable.PROTEIN:
                protein = new Percentage(Double.parseDouble(value));
                break;
            case Fermentable.MAX_IN_BATCH:
                maxInBatch = new Percentage(Double.parseDouble(value));
                break;
            case GrainOrAdjunctFermentable.RECOMMEND_MASH:
                recommendMash = Boolean.parseBoolean(value);
                break;

            case LiquidFermentable.IBU_GALLONS_PER_POUND:
            case "IBU_GAL_PER_POUND":
                IBUGallonsPerPound = Double.parseDouble(value);
                break;
            default:
                throw new UnknownTagException("Unknown fermentable tag: " + tagName);
        }
        return this;
    }

    @Override
    public Fermentable create() {
        Fermentable toReturn;
        if (type.equals(Fermentable.FERMENTABLE_TYPE.EXTRACT)) {
            SRM color = new SRM(Double.parseDouble(colorString));
            toReturn = new LiquidFermentable(name, amount, yield, addAfterBoil, origin, supplier, notes, maxInBatch, color, IBUGallonsPerPound);
        } else if (type.equals(Fermentable.FERMENTABLE_TYPE.ADJUNCT) || type.equals(Fermentable.FERMENTABLE_TYPE.GRAIN)) {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new GrainOrAdjunctFermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch, coarseFineDiff, moisture, diastaticPower, protein, recommendMash);
        } else {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new Fermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        }

        return toReturn;

    }
}
