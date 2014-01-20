package org.blh.beerxml;

import java.util.HashMap;
import java.util.Map;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.blh.beerxml.type.Equipment;
import org.blh.beerxml.type.Fermentable;
import org.blh.beerxml.type.Hop;
import org.blh.beerxml.type.MashProfile;
import org.blh.beerxml.type.MashStep;
import org.blh.beerxml.type.Misc;
import org.blh.beerxml.type.Recipe;
import org.blh.beerxml.type.Style;
import org.blh.beerxml.type.Water;
import org.blh.beerxml.type.Yeast;

/**
 * A basic implementation of ClassToRecordNameMapper.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class ClassToRecordNameMapperImpl implements ClassToRecordNameMapper {

    private final Map<Class, String> map;

    public ClassToRecordNameMapperImpl() {
        map = new HashMap<>();
        map.put(Equipment.class, "EQUIPMENT");
        map.put(Fermentable.class, "FERMENTABLE");
        map.put(Hop.class, "HOP");
        map.put(MashProfile.class, "MASH");
        map.put(MashStep.class, "MASH_STEP");
        map.put(Misc.class, "MISC");
        map.put(Recipe.class, "RECIPE");
        map.put(Style.class, "STYLE");
        map.put(Water.class, "WATER");
        map.put(Yeast.class, "YEAST");
    }

    @Override
    public String getRecordName(BeerXMLRecord record) throws NoRecordNameException {
        return getRecordNameRecursive(record.getClass());
    }

    private String getRecordNameRecursive(Class c) throws NoRecordNameException {
        String name = map.get(c);
        if (name == null) {
            if (c == Object.class) {
                throw new NoRecordNameException("I don't know what this is.. " + c.toString());
            } else {
                name = getRecordNameRecursive(c.getSuperclass());
            }
        }
        return name;
    }

    @Override
    public String getRecordSetName(BeerXMLRecordSet recordSet) throws NoRecordNameException {
        String singular = getRecordNameRecursive(recordSet.getType());
        return singular + "S";
    }
}
