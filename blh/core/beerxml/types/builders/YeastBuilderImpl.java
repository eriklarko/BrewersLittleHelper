package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.Yeast.FLOCCULATION;
import blh.core.beerxml.types.Yeast.FORM;
import blh.core.beerxml.types.Yeast.TYPE;
import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

public class YeastBuilderImpl implements YeastBuilder {

    private String name;
    private TYPE type;
    private FORM form;
    private double amount;
    private boolean amountIsWeight;
    private String laboratory;
    private String productId;
    private Celcius minTemperature;
    private Celcius maxTemperature;
    private FLOCCULATION flocculation;
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
    public YeastBuilderImpl setType(TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public YeastBuilderImpl setForm(FORM form) {
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
    public YeastBuilderImpl setFlocculation(FLOCCULATION flocculation) {
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
    public Builder<Yeast> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "TYPE":
                type = Yeast.TYPE.valueOf(value.toUpperCase());
                break;
            case "FORM":
                form = Yeast.FORM.valueOf(value.toUpperCase());
                break;
            case "AMOUNT":
                amount = Double.parseDouble(value);
                break;
            case "AMOUNT_IS_WEIGHT":
                amountIsWeight = Boolean.parseBoolean(value);
                break;
            case "LABORATORY":
                laboratory = value;
                break;
            case "PRODUCT_ID":
                productId = value;
                break;
            case "MIN_TEMPERATURE":
                minTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "MAX_TEMPERATURE":
                maxTemperature = new Celcius(Double.parseDouble(value));
                break;
            case "FLOCCULATION":
                flocculation = Yeast.FLOCCULATION.valueOf(value.toUpperCase());
                break;
            case "ATTENUATION":
                attenuation = new Percentage(Double.parseDouble(value));
                break;
            case "NOTES":
                notes = value;
                break;
            case "BEST_FOR":
                bestFor = value;
                break;
            case "TIMES_CULTURED":
                timesCultured = Integer.parseInt(value);
                break;
            case "MAX_REUSE":
                maxReuse = Integer.parseInt(value);
                break;
            case "ADD_TO_SECONDARY":
                addToSecondary = Boolean.parseBoolean(value);
                break;
            default:
                System.out.println("Unknown yeast value: " + tagName);
                break;
        }

        return this;
    }

    @Override
    public Yeast create() {
        return new Yeast(name, type, form, amount, amountIsWeight, laboratory, productId, minTemperature, maxTemperature, flocculation, attenuation, notes, bestFor, timesCultured, maxReuse, addToSecondary);
    }
}
