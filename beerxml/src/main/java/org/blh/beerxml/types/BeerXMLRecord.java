package org.blh.beerxml.types;

import java.util.List;
import java.util.Map;

/**
 *
 * @author thinner
 */
public interface BeerXMLRecord {
    
    public static final String VERSION = "VERSION";

    public static enum RECORD_TYPE {

        HOP, YEAST, FERMENTABLE, WATER, MISC, EQUIPMENT, STYLE, RECIPE, MASH, MASH_STEP
    }
    
    public Map<String, String> getBeerXMLTagsAndValues();
    
    public List<BeerXMLRecord> getSubRecords();
    
    public List<BeerXMLRecordSet> getSubRecordSets();
}
