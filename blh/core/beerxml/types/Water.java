package blh.core.beerxml.types;

import blh.core.units.PH;
import blh.core.units.PPM;
import blh.core.units.volume.Liters;

/**
 *
 * @author thinner
 */
public class Water {

    public final String name;
    public final Liters amount;
    public final PPM calcium;
    public final PPM bicarbonate;
    public final PPM sulfate;
    public final PPM chloride;
    public final PPM sodium;
    public final PPM magnesium;
    public final PH ph;
    public final String notes;

    public Water(String name, Liters amount, PPM calcium, PPM bicarbonate, PPM sulfate, PPM chloride, PPM sodium, PPM magnesium, PH ph, String notes) {
        this.name = name;
        this.amount = amount;
        this.calcium = calcium;
        this.bicarbonate = bicarbonate;
        this.sulfate = sulfate;
        this.chloride = chloride;
        this.sodium = sodium;
        this.magnesium = magnesium;
        this.ph = ph;
        this.notes = notes;
    }
}
