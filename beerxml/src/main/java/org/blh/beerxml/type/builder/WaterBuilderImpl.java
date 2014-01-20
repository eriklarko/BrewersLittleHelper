package org.blh.beerxml.type.builder;

import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.Water;
import org.blh.core.unit.PH;
import org.blh.core.unit.PPM;
import org.blh.core.unit.volume.Liters;

/**
 * Builder for the Water type.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class WaterBuilderImpl implements WaterBuilder {

    private String name;
    private Liters amount;
    private PPM calcium;
    private PPM bicarbonate;
    private PPM sulfate;
    private PPM chloride;
    private PPM sodium;
    private PPM magnesium;
    private PH ph;
    private String notes;

    public WaterBuilderImpl() {
    }

    @Override
    public WaterBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public WaterBuilderImpl setAmount(Liters amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public WaterBuilderImpl setCalcium(PPM calcium) {
        this.calcium = calcium;
        return this;
    }

    @Override
    public WaterBuilderImpl setBicarbonate(PPM bicarbonate) {
        this.bicarbonate = bicarbonate;
        return this;
    }

    @Override
    public WaterBuilderImpl setSulfate(PPM sulfate) {
        this.sulfate = sulfate;
        return this;
    }

    @Override
    public WaterBuilderImpl setChloride(PPM chloride) {
        this.chloride = chloride;
        return this;
    }

    @Override
    public WaterBuilderImpl setSodium(PPM sodium) {
        this.sodium = sodium;
        return this;
    }

    @Override
    public WaterBuilderImpl setMagnesium(PPM magnesium) {
        this.magnesium = magnesium;
        return this;
    }

    @Override
    public WaterBuilderImpl setPh(PH ph) {
        this.ph = ph;
        return this;
    }

    @Override
    public WaterBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    // This is as complex as it needs to be
    @Override
    public Builder<Water> set(String tagName, String value) throws UnknownTagException {
        switch (tagName.toUpperCase()) {
            case BeerXMLRecord.VERSION: break;
            case Water.NAME:
                name = value;
                break;
            case Water.AMOUNT:
                amount = new Liters(Double.parseDouble(value));
                break;
            case Water.CALCIUM:
                calcium = new PPM(Double.parseDouble(value));
                break;
            case Water.BICARBONATE:
                bicarbonate = new PPM(Double.parseDouble(value));
                break;
            case Water.SULFATE:
                sulfate = new PPM(Double.parseDouble(value));
                break;
            case Water.CHLORIDE:
                chloride = new PPM(Double.parseDouble(value));
                break;
            case Water.SODIUM:
                sodium = new PPM(Double.parseDouble(value));
                break;
            case Water.MAGNESIUM:
                magnesium = new PPM(Double.parseDouble(value));
                break;
            case Water.PH:
                ph = new PH(Double.parseDouble(value));
                break;
            case Water.NOTES:
                notes = value;
                break;
            default:
                throw new UnknownTagException("Unknown water tag: " + tagName);
        }

        return this;
    }

    @Override
    public Water create() {
        return new Water(name, amount, calcium, bicarbonate, sulfate, chloride, sodium, magnesium, ph, notes);
    }
}
