package org.blh.beerxml.types;

import org.blh.beerxml.Utils;
import org.blh.core.units.Percentage;
import org.blh.core.units.color.SRM;
import org.blh.core.units.weight.Kilograms;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author thinner
 */
public class LiquidFermentable extends Fermentable {

    public static final String IBU_GALLONS_PER_POUND = "IBU_GAL_PER_LB";
    public final SRM color;
    public final double IBUGallonsPerPound;

    public LiquidFermentable(String name, Kilograms amount,
            Percentage yield, boolean addAfterBoil,
            String origin, String supplier, String notes, Percentage maxInBatch,
            SRM color, double IBUGallonsPerPound) {
        super(0, name, Fermentable.FERMENTABLE_TYPE.EXTRACT, amount, yield, null, addAfterBoil, origin, supplier, notes, maxInBatch);
        this.color = color;
        this.IBUGallonsPerPound = IBUGallonsPerPound;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>(super.getBeerXMLTagsAndValues());

        tagsAndValues.put(COLOR, Utils.toStringOrNull(color));
        tagsAndValues.put(IBU_GALLONS_PER_POUND, Utils.toStringOrNull(IBUGallonsPerPound));

        return tagsAndValues;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LiquidFermentable other = (LiquidFermentable) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (Double.doubleToLongBits(this.IBUGallonsPerPound) != Double.doubleToLongBits(other.IBUGallonsPerPound)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 7 * super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.color);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.IBUGallonsPerPound) ^ (Double.doubleToLongBits(this.IBUGallonsPerPound) >>> 32));
        return hash;
    }
}
