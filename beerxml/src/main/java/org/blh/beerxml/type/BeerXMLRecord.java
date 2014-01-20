package org.blh.beerxml.type;

import java.util.List;
import java.util.Map;

/**
 * Defines a BeerXML record.
 * 
 * @author thinner
 */
public interface BeerXMLRecord {

    String VERSION = "VERSION";

    /**
     * Represents valid BeerXML 1 record types.
     */
    public static enum RECORD_TYPE {

        HOP, YEAST, FERMENTABLE, WATER, MISC, EQUIPMENT, STYLE, RECIPE, MASH, MASH_STEP
    }

    Map<String, String> getBeerXMLTagsAndValues();

    List<BeerXMLRecord> getSubRecords();

    List<BeerXMLRecordSet> getSubRecordSets();
}
