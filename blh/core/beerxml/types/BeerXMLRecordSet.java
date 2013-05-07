package blh.core.beerxml.types;

import java.util.List;

/**
 *
 * @author thinner
 */
public class BeerXMLRecordSet<T extends BeerXMLRecord> {

    /*public static enum TYPE {

     HOPS, YEASTS, FERMENTABLES, WATERS, MISCS, EQUIPMENTS, STYLES, RECIPES, MASHS, MASH_STEPS
     }*/
    private List<T> records;

    public BeerXMLRecordSet(List<T> records) {
        this.records = records;
    }

    public List<T> getRecords() {
        return records;
    }
}
