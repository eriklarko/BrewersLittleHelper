package org.blh.beerxml.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.blh.beerxml.Utils;
import org.blh.core.unit.Lintner;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.weight.Kilograms;

/**
 * Implementation of the BeerXML GrainOrAdjunctFermentable record.
 *
 * @author thinner
 */
public class GrainOrAdjunctFermentable extends Fermentable {

    public static final String COARSE_FINE_DIFF = "COARSE_FINE_DIFF";
    public static final String MOISTURE = "MOISTURE";
    public static final String DIASTATIC_POWER = "DIASTATIC_POWER";
    public static final String PROTEIN = "PROTEIN";
    public static final String RECOMMEND_MASH = "RECOMMEND_MASH";
    private final Percentage coarseFineDiff;
    private final Percentage moisture;
    private final Lintner diastaticPower;
    private final Percentage protein;
    private final boolean recommendMash;

    public GrainOrAdjunctFermentable(String name, FERMENTABLE_TYPE type,
            Kilograms amount, Percentage yield, Lovibond color,
            boolean addAfterBoil, String origin, String supplier, String notes,
            Percentage maxInBatch, Percentage coarseFineDiff,
            Percentage moisture, Lintner diastaticPower, Percentage protein,
            boolean recommendMash) {
        super(0, name, type, amount, yield, color, addAfterBoil, origin, supplier, notes, maxInBatch);

        if (!type.equals(FERMENTABLE_TYPE.ADJUNCT) && !type.equals(FERMENTABLE_TYPE.GRAIN)) {
            throw new IllegalArgumentException("The type must be adjunct or grain");
        }

        this.coarseFineDiff = coarseFineDiff;
        this.moisture = moisture;
        this.diastaticPower = diastaticPower;
        this.protein = protein;
        this.recommendMash = recommendMash;
    }

    public Percentage getCoarseFineDiff() {
        return coarseFineDiff;
    }

    public Percentage getMoisture() {
        return moisture;
    }

    public Lintner getDiastaticPower() {
        return diastaticPower;
    }

    public Percentage getProtein() {
        return protein;
    }

    public boolean recommendsMash() {
        return recommendMash;
    }

    @Override
    public Map<String, String> getBeerXMLTagsAndValues() {
        Map<String, String> tagsAndValues = new HashMap<>(super.getBeerXMLTagsAndValues());

        tagsAndValues.put(COARSE_FINE_DIFF, Utils.toStringOrNull(coarseFineDiff));
        tagsAndValues.put(MOISTURE, Utils.toStringOrNull(moisture));
        tagsAndValues.put(DIASTATIC_POWER, Utils.toStringOrNull(diastaticPower));
        tagsAndValues.put(PROTEIN, Utils.toStringOrNull(protein));
        tagsAndValues.put(RECOMMEND_MASH, Utils.toStringOrNull(recommendMash));

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
        final GrainOrAdjunctFermentable other = (GrainOrAdjunctFermentable) obj;
        if (!Objects.equals(this.coarseFineDiff, other.coarseFineDiff)) {
            return false;
        }
        if (!Objects.equals(this.moisture, other.moisture)) {
            return false;
        }
        if (!Objects.equals(this.diastaticPower, other.diastaticPower)) {
            return false;
        }
        if (!Objects.equals(this.protein, other.protein)) {
            return false;
        }
        return this.recommendMash == other.recommendMash;
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 3 * super.hashCode();
        hash = 83 * hash + Objects.hashCode(this.coarseFineDiff);
        hash = 83 * hash + Objects.hashCode(this.moisture);
        hash = 83 * hash + Objects.hashCode(this.diastaticPower);
        hash = 83 * hash + Objects.hashCode(this.protein);
        hash = 83 * hash + (this.recommendMash ? 1 : 0);
        return hash;
    }
}
