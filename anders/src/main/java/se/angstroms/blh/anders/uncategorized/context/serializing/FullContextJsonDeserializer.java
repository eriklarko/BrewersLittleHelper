/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.angstroms.blh.anders.uncategorized.context.serializing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.CalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.uncategorized.value.InputtedValue;

/**
 *
 * @author eriklark
 */
public class FullContextJsonDeserializer implements JsonDeserializer<FullContext> {

    private final FullContext context;

    public FullContextJsonDeserializer(FullContext context) {
        this.context = context;
    }
    @Override
    public FullContext deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonContext) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String name = entry.getKey();

            if (name.equalsIgnoreCase("recipeProperty")) {
                continue; //TODO: Fix :)
            }

            try {
                Field field = context.getClass().getDeclaredField(name);
                Object o2 = jsonContext.deserialize(entry.getValue(), field.getGenericType());

                if (o2 == null) {
                    throw new JsonParseException(name + " deserialized to null.");
                }

                field.setAccessible(true);
                Object o = field.get(context);

                if (o instanceof InputtedOrCalculatedValue && o2 instanceof InputtedOrCalculatedValue) {
                    InputtedOrCalculatedValue iocv = (InputtedOrCalculatedValue<? extends Unit<?>>) o;
                    InputtedOrCalculatedValue<? extends Unit<?>> deserialized = (InputtedOrCalculatedValue<? extends Unit<?>>) o2;

                    if (deserialized.stateProperty().get() == STATE.CALCULATED) {
                        if (deserialized.get() != null ) {
                            iocv.set(deserialized.get());
                        }
                        iocv.setFormula(deserialized.formulaProperty().get());
                    } else if (deserialized.stateProperty().get() == STATE.INPUTTED) {

                        if (deserialized.formulaProperty().get() != null) {
                            iocv.setFormula(deserialized.formulaProperty().get());
                        }
                        iocv.set(deserialized.get());
                    }

                } else if (o instanceof InputtedValue && o2 instanceof InputtedValue) {
                    ((InputtedValue) o).set(((InputtedValue) o2).get());
                } if (o instanceof CalculatedValue && o2 instanceof CalculatedValue) {
                    ((CalculatedValue) o).formulaProperty().set(((CalculatedValue) o2).formulaProperty().get());
                }

            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                throw new JsonParseException(ex);
            }
        }

        return context;
    }
}
