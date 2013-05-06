package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Water;
import blh.core.units.PH;
import blh.core.units.PPM;
import blh.core.units.volume.Liters;

public class WaterBuilder implements Builder<Water> {

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

    public WaterBuilder() {
    }

    public WaterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WaterBuilder setAmount(Liters amount) {
        this.amount = amount;
        return this;
    }

    public WaterBuilder setCalcium(PPM calcium) {
        this.calcium = calcium;
        return this;
    }

    public WaterBuilder setBicarbonate(PPM bicarbonate) {
        this.bicarbonate = bicarbonate;
        return this;
    }

    public WaterBuilder setSulfate(PPM sulfate) {
        this.sulfate = sulfate;
        return this;
    }

    public WaterBuilder setChloride(PPM chloride) {
        this.chloride = chloride;
        return this;
    }

    public WaterBuilder setSodium(PPM sodium) {
        this.sodium = sodium;
        return this;
    }

    public WaterBuilder setMagnesium(PPM magnesium) {
        this.magnesium = magnesium;
        return this;
    }

    public WaterBuilder setPh(PH ph) {
        this.ph = ph;
        return this;
    }

    public WaterBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public Builder<Water> set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "AMOUNT":
                amount = new Liters(Double.parseDouble(value));
                break;
            case "CALCIUM":
                calcium = new PPM(Double.parseDouble(value));
                break;
            case "BICARBONATE":
                bicarbonate = new PPM(Double.parseDouble(value));
                break;
            case "SULFATE":
                sulfate = new PPM(Double.parseDouble(value));
                break;
            case "CHLORIDE":
                chloride = new PPM(Double.parseDouble(value));
                break;
            case "SODIUM":
                sodium = new PPM(Double.parseDouble(value));
                break;
            case "MAGNESIUM":
                magnesium = new PPM(Double.parseDouble(value));
                break;
            case "PH":
                ph = new PH(Double.parseDouble(value));
                break;
            case "NOTES":
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
