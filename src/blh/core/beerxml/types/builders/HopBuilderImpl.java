package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.Hop.FORM;
import blh.core.beerxml.types.Hop.TYPE;
import blh.core.beerxml.types.Hop.USE;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

public class HopBuilderImpl implements HopBuilder {

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

    public HopBuilderImpl() {
    }

    @Override
    public HopBuilderImpl setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public HopBuilderImpl setAlpha(Percentage alpha) {
        this.alpha = alpha;
        return this;
    }

    @Override
    public HopBuilderImpl setAmount(Kilograms amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public HopBuilderImpl setUse(USE use) {
        this.use = use;
        return this;
    }

    @Override
    public HopBuilderImpl setTime(Minutes time) {
        this.time = time;
        return this;
    }

    @Override
    public HopBuilderImpl setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @Override
    public HopBuilderImpl setType(TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public HopBuilderImpl setForm(FORM form) {
        this.form = form;
        return this;
    }

    @Override
    public HopBuilderImpl setBeta(Percentage beta) {
        this.beta = beta;
        return this;
    }

    @Override
    public HopBuilderImpl setHopStabilityIndex(Percentage hopStabilityIndex) {
        this.hopStabilityIndex = hopStabilityIndex;
        return this;
    }

    @Override
    public HopBuilderImpl setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public HopBuilderImpl setSubstitutes(String substitutes) {
        this.substitutes = substitutes;
        return this;
    }

    @Override
    public HopBuilderImpl setHumulene(Percentage humulene) {
        this.humulene = humulene;
        return this;
    }

    @Override
    public HopBuilderImpl setCaryophyllene(Percentage caryophyllene) {
        this.caryophyllene = caryophyllene;
        return this;
    }

    @Override
    public HopBuilderImpl setCohumulone(Percentage cohumulone) {
        this.cohumulone = cohumulone;
        return this;
    }

    @Override
    public HopBuilderImpl setMyrcene(Percentage myrcene) {
        this.myrcene = myrcene;
        return this;
    }

    public HopBuilderImpl set(String tagName, String value) {
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

    @Override
    public Hop create() {
        return new Hop(name, alpha, amount, use, time, notes, type, form, beta, hopStabilityIndex, origin, substitutes, humulene, caryophyllene, cohumulone, myrcene);
    }
}
