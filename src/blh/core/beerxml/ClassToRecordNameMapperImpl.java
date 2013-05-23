package blh.core.beerxml;

import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.BeerXMLRecordSet;
import blh.core.beerxml.types.Equipment;
import blh.core.beerxml.types.Fermentable;
import blh.core.beerxml.types.Hop;
import blh.core.beerxml.types.MashProfile;
import blh.core.beerxml.types.MashStep;
import blh.core.beerxml.types.Misc;
import blh.core.beerxml.types.Recipe;
import blh.core.beerxml.types.Style;
import blh.core.beerxml.types.Water;
import blh.core.beerxml.types.Yeast;
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
