package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Yeast;
import blh.core.beerxml.types.Yeast.FLOCCULATION;
import blh.core.beerxml.types.Yeast.FORM;
import blh.core.beerxml.types.Yeast.TYPE;
import blh.core.units.Percentage;
import blh.core.units.temperature.Celcius;

public class YeastBuilder implements Builder<Yeast> {

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

    public YeastBuilder() {
    }

    public YeastBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public YeastBuilder setType(TYPE type) {
        this.type = type;
        return this;
    }

    public YeastBuilder setForm(FORM form) {
        this.form = form;
        return this;
    }

    public YeastBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public YeastBuilder setAmountIsWeight(boolean amountIsWeight) {
        this.amountIsWeight = amountIsWeight;
        return this;
    }

    public YeastBuilder setLaboratory(String laboratory) {
        this.laboratory = laboratory;
        return this;
    }

    public YeastBuilder setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public YeastBuilder setMinTemperature(Celcius minTemperature) {
        this.minTemperature = minTemperature;
        return this;
    }

    public YeastBuilder setMaxTemperature(Celcius maxTemperature) {
        this.maxTemperature = maxTemperature;
        return this;
    }

    public YeastBuilder setFlocculation(FLOCCULATION flocculation) {
        this.flocculation = flocculation;
        return this;
    }

    public YeastBuilder setAttenuation(Percentage attenuation) {
        this.attenuation = attenuation;
        return this;
    }

    public YeastBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public YeastBuilder setBestFor(String bestFor) {
        this.bestFor = bestFor;
        return this;
    }

    public YeastBuilder setTimesCultured(int timesCultured) {
        this.timesCultured = timesCultured;
        return this;
    }

    public YeastBuilder setMaxReuse(int maxReuse) {
        this.maxReuse = maxReuse;
        return this;
    }

    public YeastBuilder setAddToSecondary(boolean addToSecondary) {
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
