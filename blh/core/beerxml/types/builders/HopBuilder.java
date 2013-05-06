package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.Hop.FORM;
import blh.core.beerxml.types.Hop.TYPE;
import blh.core.beerxml.types.Hop.USE;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

public class HopBuilder {

    private String name;
    private Percentage alpha;
    private Kilograms amount;
    private USE use;
    private Minutes time;
    private String notes;
    private TYPE type;
    private FORM form;
    private Percentage beta;
    private Percentage hopStabilityIndex;
    private String origin;
    private String substitutes;
    private Percentage humulene;
    private Percentage caryophyllene;
    private Percentage cohumulone;
    private Percentage myrcene;

    public HopBuilder() {
    }

    public HopBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HopBuilder setAlpha(Percentage alpha) {
        this.alpha = alpha;
        return this;
    }

    public HopBuilder setAmount(Kilograms amount) {
        this.amount = amount;
        return this;
    }

    public HopBuilder setUse(USE use) {
        this.use = use;
        return this;
    }

    public HopBuilder setTime(Minutes time) {
        this.time = time;
        return this;
    }

    public HopBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public HopBuilder setType(TYPE type) {
        this.type = type;
        return this;
    }

    public HopBuilder setForm(FORM form) {
        this.form = form;
        return this;
    }

    public HopBuilder setBeta(Percentage beta) {
        this.beta = beta;
        return this;
    }

    public HopBuilder setHopStabilityIndex(Percentage hopStabilityIndex) {
        this.hopStabilityIndex = hopStabilityIndex;
        return this;
    }

    public HopBuilder setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public HopBuilder setSubstitutes(String substitutes) {
        this.substitutes = substitutes;
        return this;
    }

    public HopBuilder setHumulene(Percentage humulene) {
        this.humulene = humulene;
        return this;
    }

    public HopBuilder setCaryophyllene(Percentage caryophyllene) {
        this.caryophyllene = caryophyllene;
        return this;
    }

    public HopBuilder setCohumulone(Percentage cohumulone) {
        this.cohumulone = cohumulone;
        return this;
    }

    public HopBuilder setMyrcene(Percentage myrcene) {
        this.myrcene = myrcene;
        return this;
    }

    public HopBuilder set(String tagName, String value) {
        switch (tagName.toUpperCase()) {
            case "NAME":
                name = value;
                break;
            case "ALPHA":
                alpha = new Percentage(Double.parseDouble(value));
                break;
            case "AMOUNT":
                amount = new Kilograms(Double.parseDouble(value));
                break;
            case "USE":
                use = Hop.USE.valueOf(value.toUpperCase());
                break;
            case "TIME":
                time = new Minutes(Integer.parseInt(value));
                break;
            case "NOTES":
                notes = value;
                break;
            case "TYPE":
                type = Hop.TYPE.valueOf(value.toUpperCase());
                break;
            case "FORM":
                form = Hop.FORM.valueOf(value.toUpperCase());
                break;
            case "BETA":
                beta = new Percentage(Double.parseDouble(value));
                break;
            case "HSI":
                hopStabilityIndex = new Percentage(Double.parseDouble(value));
                break;
            case "ORIGIN":
                origin = value;
                break;
            case "SUBSTITUTES":
                substitutes = value;
                break;
            case "HUMULENE":
                humulene = new Percentage(Double.parseDouble(value));
                break;
            case "CARYOPHYLLENE":
                caryophyllene = new Percentage(Double.parseDouble(value));
                break;
            case "COHUMULONE":
                cohumulone = new Percentage(Double.parseDouble(value));
                break;
            case "MYRCENE":
                myrcene = new Percentage(Double.parseDouble(value));
                break;
            default:
                System.out.println("UNKNOWN HOP VALUE: " + tagName);
                break;
        }
        return this;
    }

    public Hop createHop() {
        return new Hop(name, alpha, amount, use, time, notes, type, form, beta, hopStabilityIndex, origin, substitutes, humulene, caryophyllene, cohumulone, myrcene);
    }
}
