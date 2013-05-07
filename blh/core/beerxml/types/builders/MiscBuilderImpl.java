package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Misc.TYPE;
import blh.core.beerxml.types.Misc.USE;
import blh.core.units.time.Minutes;

public class MiscBuilderImpl implements MiscBuilder {

    private String name;
    private TYPE type;
    private USE use;
    private Minutes time;
    private double amount;
    private boolean amountIsWeight;
    private String useFor;
    private String notes;

    public MiscBuilderImpl() {
    }

    @Override
    public MiscBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public MiscBuilderImpl setType(TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public MiscBuilderImpl setUse(USE use) {
        this.use = use;
        return this;
    }

    @Override
    public MiscBuilderImpl setTime(Minutes time) {
        this.time = time;
        return this;
    }

    @Override
    public MiscBuilderImpl setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public MiscBuilderImpl setAmountIsWeight(boolean amountIsWeight) {
        this.amountIsWeight = amountIsWeight;
        return this;
    }

    @Override
    public MiscBuilderImpl setUseFor(String useFor) {
        this.useFor = useFor;
        return this;
    }

    @Override
    public MiscBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public MiscBuilderImpl set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "TYPE":
                type = Misc.TYPE.valueOf(value.toUpperCase());
                break;
            case "USE":
                use = Misc.USE.valueOf(value.toUpperCase());
                break;
            case "TIME":
                time = new Minutes(Integer.parseInt(value));
                break;
            case "AMOUNT":
                amount = Double.parseDouble(value);
                break;
            case "AMOUNT_IS_WEIGHT":
                amountIsWeight = Boolean.parseBoolean(value);
                break;
            case "USE_FOR":
                useFor = value;
                break;
            case "NOTES":
                notes = value;
                break;
            default:
                System.out.println("Unknown misc value: " + tagName);
                break;
        }

        return this;
    }

    @Override
    public Misc create() {
        return new Misc(name, type, use, time, amount, amountIsWeight, useFor, notes);
    }
}
