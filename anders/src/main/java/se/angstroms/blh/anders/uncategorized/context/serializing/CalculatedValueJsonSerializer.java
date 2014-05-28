package se.angstroms.blh.anders.uncategorized.context.serializing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import se.angstroms.blh.anders.uncategorized.value.CalculatedValue;

/**
 *
 * @author eriklark
 */
public class CalculatedValueJsonSerializer implements JsonSerializer<CalculatedValue<?>>,
        JsonDeserializer<CalculatedValue<?>> {

    public CalculatedValueJsonSerializer() {
    }

    @Override
    public JsonElement serialize(CalculatedValue<?> src, Type typeOfSrc, JsonSerializationContext context) {
        if (src.formulaProperty().get() == null) {
            return null;
        } else {
            return new JsonPrimitive(src.formulaProperty().get().getClass().getCanonicalName());
        }
    }

    @Override
    public CalculatedValue<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
