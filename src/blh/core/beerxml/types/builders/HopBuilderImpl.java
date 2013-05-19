package blh.core.beerxml.types.builders;

import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.Hop.HOP_FORM;
import blh.core.beerxml.types.Hop.HOP_TYPE;
import blh.core.beerxml.types.Hop.HOP_USE;
import blh.core.units.Percentage;
import blh.core.units.time.Minutes;
import blh.core.units.weight.Kilograms;

public class HopBuilderImpl implements HopBuilder {

    private String name;
    private Percentage alpha;
    private Kilograms amount;
    private HOP_USE use;
    private Minutes time;
    private String notes;
    private HOP_TYPE type;
    private HOP_FORM form;
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
    public HopBuilderImpl setUse(HOP_USE use) {
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
    public HopBuilderImpl setType(HOP_TYPE type) {
        this.type = type;
        return this;
    }

    @Override
    public HopBuilderImpl setForm(HOP_FORM form) {
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
            case Hop.NAME:
                name = value;
                break;
            case Hop.ALPHA:
                alpha = new Percentage(Double.parseDouble(value));
                break;
            case Hop.AMOUNT:
                amount = new Kilograms(Double.parseDouble(value));
                break;
            case Hop.USE:
                use = Hop.HOP_USE.valueOf(value.toUpperCase());
                break;
            case Hop.TIME:
                time = new Minutes(Integer.parseInt(value));
                break;
            case Hop.NOTES:
                notes = value;
                break;
            case Hop.TYPE:
                type = Hop.HOP_TYPE.valueOf(value.toUpperCase());
                break;
            case Hop.FORM:
                form = Hop.HOP_FORM.valueOf(value.toUpperCase());
                break;
            case Hop.BETA:
                beta = new Percentage(Double.parseDouble(value));
                break;
            case Hop.HOP_STABILITY_INDEX:
                hopStabilityIndex = new Percentage(Double.parseDouble(value));
                break;
            case Hop.ORIGIN:
                origin = value;
                break;
            case Hop.SUBSTITUTES:
                substitutes = value;
                break;
            case Hop.HUMULENE:
                humulene = new Percentage(Double.parseDouble(value));
                break;
            case Hop.CARYOPHYLLENE:
                caryophyllene = new Percentage(Double.parseDouble(value));
                break;
            case Hop.COHUMULONE:
                cohumulone = new Percentage(Double.parseDouble(value));
                break;
            case Hop.MYRCENE:
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
