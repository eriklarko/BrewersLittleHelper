package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.weight.Kilograms;

/**
 * Implementation of the BeerXML Liquid Fermentable record.
 *
 * @author thinner
 */
public class LiquidFermentable extends Fermentable {

    public static final String IBU_GALLONS_PER_POUND = "IBU_GAL_PER_LB";
    private final SRM color;
    private final double ibuGallonsPerPound;

    public LiquidFermentable(String name, Kilograms amount,
            Percentage yield, boolean addAfterBoil,
            String origin, String supplier, String notes, Percentage maxInBatch,
            SRM color, double ibuGallonsPerPound) {
        super(0, name, Fermentable.FERMENTABLE_TYPE.EXTRACT, amount, yield, null, addAfterBoil, origin, supplier, notes, maxInBatch);
        this.color = color;
        this.ibuGallonsPerPound = ibuGallonsPerPound;
    }

    public double getIBUGallonsPerPound() {
        return ibuGallonsPerPound;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>(super.getBeerXMLTagsAndValues());

        tagsAndValues.put(COLOR, Utils.toStringOrNull(color));
        tagsAndValues.put(IBU_GALLONS_PER_POUND, Utils.toStringOrNull(ibuGallonsPerPound));

        return tagsAndValues;
    }

    // This is as complex as it needs to be
    @Override
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
        return Double.doubleToLongBits(this.ibuGallonsPerPound) == Double.doubleToLongBits(other.ibuGallonsPerPound);
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 7 * super.hashCode();
        hash = 29 * hash + Objects.hashCode(this.color);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.ibuGallonsPerPound)
                ^ (Double.doubleToLongBits(this.ibuGallonsPerPound) >>> 32));
        return hash;
    }
}
