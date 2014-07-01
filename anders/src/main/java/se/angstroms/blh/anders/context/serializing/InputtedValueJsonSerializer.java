package se.angstroms.blh.anders.context.serializing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.value.InputtedValue;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class InputtedValueJsonSerializer implements JsonSerializer<InputtedValue<?>>,
        JsonDeserializer<InputtedValue<?>> {

    private final UnitStringParserFactory unitStringParserFactory;

    public InputtedValueJsonSerializer(UnitStringParserFactory unitStringParserFactory) {
        this.unitStringParserFactory = unitStringParserFactory;
    }

    @Override
    public JsonElement serialize(InputtedValue<?> src, Type typeOfSrc, JsonSerializationContext context) {
        if (src.get() == null) {
            return null;
        } else {
            return new JsonPrimitive(String.valueOf(src.get()));
        }
    }

    @Override
    public InputtedValue<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Unit parsedUnit = DeserializationUtils.getUnit(json, typeOfT, unitStringParserFactory);
        return new InputtedValue(parsedUnit);
    }
}
