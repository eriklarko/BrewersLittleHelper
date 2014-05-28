package se.angstroms.blh.anders.uncategorized.context.serializing;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.value.parsing.ParseException;
import se.angstroms.blh.anders.uncategorized.value.parsing.UnitStringParser;
import se.angstroms.blh.anders.uncategorized.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class DeserializationUtils {

    public static Unit<?> getUnit(JsonElement json, Type typeOfT, UnitStringParserFactory unitStringParserFactory) {
        try {
            Class<? extends Unit<?>> unitClass = getUnitClass(json, typeOfT);
            UnitStringParser<Unit<?>> parser = unitStringParserFactory.getParserFor(unitClass);
            Unit<?> parsedUnit = parser.parse(json.getAsString());
            return parsedUnit;
        } catch (ClassNotFoundException | ParseException ex) {
            throw new JsonParseException(ex);
        }
    }

    private static Class<? extends Unit<?>> getUnitClass(JsonElement json, Type typeOfT) throws ClassNotFoundException {
        if (typeOfT instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) typeOfT;
            Type unitType = pt.getActualTypeArguments()[0];
            Class<?> typeClass = Class.forName(unitType.getTypeName());
            return (Class<? extends Unit<?>>) typeClass;
        } else {
            throw new JsonParseException("I cannot parse " + json.getAsString() + ", I don't know its type");
        }
    }

    private DeserializationUtils(){}
}
