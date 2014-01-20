package org.blh.beerxml.type;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents a BeerXML record set, or a record that contains sub-records.
 *
 * @author thinner
 */
public class BeerXMLRecordSet<T extends BeerXMLRecord> {

    /**
     * Represent valid BeerXML 1 record sets
     */
    public static enum TYPE {

        HOPS, YEASTS, FERMENTABLES, WATERS, MISCS, EQUIPMENTS, STYLES, RECIPES, MASHS, MASH_STEPS
    }
    private final Class<T> type;
    private final List<T> records;

    public BeerXMLRecordSet(Class<T> type, List<T> records) {
        this.type = type;
        this.records = records;
    }

    public BeerXMLRecordSet(Class<T> type, T... records) {
        this.type = type;
        this.records = Arrays.asList(records);
    }

    public List<T> getRecords() {
        return records;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeerXMLRecordSet<T> other = (BeerXMLRecordSet<T>) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }

        if (!Objects.equals(this.records, other.records)) {
            return false;
        }
        return true;
    }

    // This hash is ok
    @Override
    public int hashCode() {
        int hash = 7 * this.type.hashCode();
        for (BeerXMLRecord record : this.records) {
            hash += 11 * record.hashCode();
        }
        return hash;
    }
}
