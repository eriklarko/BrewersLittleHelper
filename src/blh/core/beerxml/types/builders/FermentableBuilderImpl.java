package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Fermentable.TYPE;
import blh.core.beerxml.types.GrainOrAdjunctFermentable;
import blh.core.beerxml.types.LiquidFermentable;
import blh.core.units.Lintner;
import blh.core.units.Percentage;
import blh.core.units.color.Lovibond;
import blh.core.units.color.SRM;
import blh.core.units.weight.Kilograms;

public class FermentableBuilderImpl implements Builder<Fermentable>, FermentableBuilder {

    private String name;
    private TYPE type;
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
    public FermentableBuilderImpl setType(TYPE type) {
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
    public Builder<Fermentable> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "TYPE":
                type = Fermentable.TYPE.valueOf(value.replace(" ", "_").toUpperCase());
                break;
            case "AMOUNT":
                amount = new Kilograms(Double.parseDouble(value));
                break;
            case "YIELD":
                yield = new Percentage(Double.parseDouble(value));
                break;
            case "COLOR":
                colorString = value;
                break;
            case "ADD_AFTER_BOIL":
                addAfterBoil = Boolean.parseBoolean(value);
                break;
            case "ORIGIN":
                origin = value;
                break;
            case "SUPPLIER":
                supplier = value;
                break;
            case "NOTES":
                notes = value;
                break;
            case "COARSE_FINE_DIFF":
                coarseFineDiff = new Percentage(Double.parseDouble(value));
                break;
            case "MOISTURE":
                moisture = new Percentage(Double.parseDouble(value));
                break;
            case "DIASTATIC_POWER":
                diastaticPower = new Lintner(Double.parseDouble(value));
                break;
            case "PROTEIN":
                protein = new Percentage(Double.parseDouble(value));
                break;
            case "MAX_IN_BATCH":
                maxInBatch = new Percentage(Double.parseDouble(value));
                break;
            case "RECOMMENDED_MASH":
                recommendMash = Boolean.parseBoolean(value);
                break;
            case "IBU_GAL_PER_LB":
                IBUGallonsPerPound = Double.parseDouble(value);
                break;
            default:
                System.out.println("Unknown fermentable value: " + tagName);
                break;
        }
        return this;
    }

    @Override
    public Fermentable create() {
        Fermentable toReturn;
        if (type.equals(Fermentable.TYPE.EXTRACT)) {
            SRM color = new SRM(Double.parseDouble(colorString));
            toReturn = new LiquidFermentable(name, amount, yield, addAfterBoil, origin, supplier, notes, maxInBatch, color, IBUGallonsPerPound);
        } else if (type.equals(Fermentable.TYPE.ADJUNCT) || type.equals(Fermentable.TYPE.GRAIN)) {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new GrainOrAdjunctFermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch, coarseFineDiff, moisture, diastaticPower, protein, recommendMash);
        } else {
            Lovibond color = new Lovibond(Double.parseDouble(colorString));
            toReturn = new Fermentable(name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);
        }

        return toReturn;

    }
}
