package org.blh.beerxml;

import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;
import org.blh.beerxml.types.Equipment;
import org.blh.beerxml.types.Fermentable;
import org.blh.beerxml.types.Hop;
import org.blh.beerxml.types.MashProfile;
import org.blh.beerxml.types.MashStep;
import org.blh.beerxml.types.Misc;
import org.blh.beerxml.types.Recipe;
import org.blh.beerxml.types.Style;
import org.blh.beerxml.types.Water;
import org.blh.beerxml.types.Yeast;
import java.util.HashMap;
import java.util.Map;

public class ClassToRecordNameMapperImpl implements ClassToRecordNameMapper {

    private Map<Class, String> map;

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
    public String getRecordName(BeerXMLRecord record) throws UnknownRecordSetException {
        return getRecordName(record.getClass());
    }

    private String getRecordName(Class c) throws UnknownRecordSetException {
        String name = map.get(c);
        if (name == null) {
            if (c == Object.class) {
                throw new UnknownRecordSetException("I don't know what this is.. " + c.toString());
            } else {
                name = getRecordName(c.getSuperclass());
            }
        }
        return name;
    }

    @Override
    public String getRecordSetName(BeerXMLRecordSet recordSet) throws UnknownRecordSetException {
        String singular = getRecordName(recordSet.getType());
        return singular + "S";
    }
}
