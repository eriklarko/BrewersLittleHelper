package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Water;
import blh.core.units.PH;
import blh.core.units.PPM;
import blh.core.units.volume.Liters;

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

    @Override
    public Builder<Water> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
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
                System.out.println("Unknown water value: " + tagName);
                break;
        }

        return this;
    }

    @Override
    public Water create() {
        return new Water(name, amount, calcium, bicarbonate, sulfate, chloride, sodium, magnesium, ph, notes);
    }
}
