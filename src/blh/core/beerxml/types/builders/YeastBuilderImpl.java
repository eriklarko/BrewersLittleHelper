package blh.core.beerxml.types.builders;

import blh.core.beerxml.UnknownTagException;
import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.Yeast.YEAST_FLOCCULATION;
import blh.core.beerxml.types.Yeast.YEAST_FORM;
import blh.core.beerxml.types.Yeast.YEAST_TYPE;
import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

public class YeastBuilderImpl implements YeastBuilder {

    private String name;
    private YEAST_TYPE type;
    private YEAST_FORM form;
    private double amount;
    private boolean amountIsWeight;
    private String laboratory;
    private String productId;
    private Celcius minTemperature;
    private Celcius maxTemperature;
    private YEAST_FLOCCULATION flocculation;
    private Percentage attenuation;
    private String notes;
    private String bestFor;
    private int timesCultured;
    private int maxReuse;
    private boolean addToSecondary;

    public YeastBuilderImpl() {
    }

    @Override
    public YeastBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public YeastBuilderImpl setType(YEAST_TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public YeastBuilderImpl setForm(YEAST_FORM form) {
        this.form = form;
        return this;
    }

    @Override
    public YeastBuilderImpl setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public YeastBuilderImpl setAmountIsWeight(boolean amountIsWeight) {
        this.amountIsWeight = amountIsWeight;
        return this;
    }

    @Override
    public YeastBuilderImpl setLaboratory(String laboratory) {
        this.laboratory = laboratory;
        return this;
    }

    @Override
    public YeastBuilderImpl setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    @Override
    public YeastBuilderImpl setMinTemperature(Celcius minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }

    @Override
    public YeastBuilderImpl setMaxTemperature(Celcius maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    @Override
    public YeastBuilderImpl setFlocculation(YEAST_FLOCCULATION flocculation) {
        this.flocculation = flocculation;
        return this;
    }

    @Override
    public YeastBuilderImpl setAttenuation(Percentage attenuation) {
        this.attenuation = attenuation;
        return this;
    }

    @Override
    public YeastBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public YeastBuilderImpl setBestFor(String bestFor) {
        this.bestFor = bestFor;
        return this;
    }

    @Override
    public YeastBuilderImpl setTimesCultured(int timesCultured) {
        this.timesCultured = timesCultured;
        return this;
    }

    @Override
    public YeastBuilderImpl setMaxReuse(int maxReuse) {
        this.maxReuse = maxReuse;
        return this;
    }

    @Override
    public YeastBuilderImpl setAddToSecondary(boolean addToSecondary) {
        this.addToSecondary = addToSecondary;
        return this;
    }

    @Override
    public Builder<Yeast> set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case Yeast.NAME:
                name = value;
                break;
            case Yeast.TYPE:
                type = Yeast.YEAST_TYPE.valueOf(value.toUpperCase());
                break;
            case Yeast.FORM:
                form = Yeast.YEAST_FORM.valueOf(value.toUpperCase());
                break;
            case Yeast.AMOUNT:
                amount = Double.parseDouble(value);
                break;
            case Yeast.AMOUNT_IS_WEIGHT:
                amountIsWeight = Boolean.parseBoolean(value);
                break;
            case Yeast.LABORATORY:
                laboratory = value;
                break;
            case Yeast.PRODUCT_ID:
                productId = value;
                break;
            case Yeast.MIN_TEMPERATURE:
                minTemperature = new Celcius(Double.parseDouble(value));
                break;
            case Yeast.MAX_TEMPERATURE:
                maxTemperature = new Celcius(Double.parseDouble(value));
                break;
            case Yeast.FLOCCULATION:
                flocculation = Yeast.YEAST_FLOCCULATION.valueOf(value.toUpperCase());
                break;
            case Yeast.ATTENUATION:
                attenuation = new Percentage(Double.parseDouble(value));
                break;
            case Yeast.NOTES:
                notes = value;
                break;
            case Yeast.BEST_FOR:
                bestFor = value;
                break;
            case Yeast.TIMES_CULTURED:
                timesCultured = Integer.parseInt(value);
                break;
            case Yeast.MAX_REUSE:
                maxReuse = Integer.parseInt(value);
                break;
            case Yeast.ADD_TO_SECONDARY:
                addToSecondary = Boolean.parseBoolean(value);
                break;
            default:
                throw new UnknownTagException("Unknown yeast tag: " + tagName);
        }

        return this;
    }

    @Override
    public Yeast create() {
        return new Yeast(name, type, form, amount, amountIsWeight, laboratory, productId, minTemperature, maxTemperature, flocculation, attenuation, notes, bestFor, timesCultured, maxReuse, addToSecondary);
    }
}
