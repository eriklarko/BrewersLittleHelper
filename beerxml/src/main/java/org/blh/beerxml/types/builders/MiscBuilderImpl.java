package org.blh.beerxml.types.builders;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.Misc;
import org.blh.beerxml.types.Misc.MISC_TYPE;
import org.blh.beerxml.types.Misc.MISC_USE;
import org.blh.core.units.time.Minutes;

public class MiscBuilderImpl implements MiscBuilder {

    private String name;
    private MISC_TYPE type;
    private MISC_USE use;
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
    public MiscBuilderImpl setType(MISC_TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public MiscBuilderImpl setUse(MISC_USE use) {
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
    public MiscBuilderImpl set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case Misc.NAME:
                name = value;
                break;
            case Misc.TYPE:
                type = Misc.MISC_TYPE.valueOf(value.toUpperCase());
                break;
            case Misc.USE:
                use = Misc.MISC_USE.valueOf(value.toUpperCase());
                break;
            case Misc.TIME:
                time = new Minutes(Double.parseDouble(value));
                break;
            case Misc.AMOUNT:
                amount = Double.parseDouble(value);
                break;
            case Misc.AMOUNT_IS_WEIGHT:
                amountIsWeight = Boolean.parseBoolean(value);
                break;
            case Misc.USE_FOR:
                useFor = value;
                break;
            case Misc.NOTES:
                notes = value;
                break;
            default:
                throw new UnknownTagException("Unknown misc tag: " + tagName);
        }

        return this;
    }

    @Override
    public Misc create() {
        return new Misc(name, type, use, time, amount, amountIsWeight, useFor, notes);
    }
}
