package se.angstroms.blh.anders.context.serializing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class InputtedOrCalculatedValueJsonSerializer implements JsonSerializer<InputtedOrCalculatedValue<?>>,
        JsonDeserializer<InputtedOrCalculatedValue<?>> {

    private final UnitStringParserFactory unitStringParserFactory;
    private FullContext fullContext;

    public InputtedOrCalculatedValueJsonSerializer(UnitStringParserFactory unitStringParserFactory) {
        this.unitStringParserFactory = unitStringParserFactory;
    }

    public void setFullContext(FullContext fullContext) {
        this.fullContext = fullContext;
    }

    @Override
    public JsonElement serialize(InputtedOrCalculatedValue<?> src, Type typeOfSrc, JsonSerializationContext context) {
        switch (src.stateProperty().get()) {
            case INVALID:
                return null;
            case CALCULATED:
            case INPUTTED:
                JsonObject json = new JsonObject();

                if (src.get() != null && src.formulaProperty().get() != null) {
                    json.addProperty("state", src.stateProperty().get().name());
                }

                if (src.get() != null) {
                    json.addProperty("value", String.valueOf(src.get()));
                }

                if (src.formulaProperty().get() != null) {
                    json.addProperty("formula", src.formulaProperty().get().getClass().getCanonicalName());
                }
                return json;
            default:
                throw new IllegalArgumentException("Unable to parse values in state " + src.stateProperty().get() + " to json");
        }
    }

    @Override
    public InputtedOrCalculatedValue<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;

        JsonElement state = jsonObject.get("state");
        JsonElement value = jsonObject.get("value");
        JsonElement formula = jsonObject.get("formula");
        validateInputAndThrowExceptions(value, formula, state, json);

        InputtedOrCalculatedValue iocv = new InputtedOrCalculatedValue();
        if (value != null && formula != null) {
            // Setup formula and value and enter the specified state

            STATE s = STATE.valueOf(state.getAsString());
            if (s == STATE.CALCULATED) {
                findValueAndEnterInputtedState(value, typeOfT, iocv);
                setupFormulaAndEnterCalculatedState(formula.getAsString(), iocv, fullContext);
            } else if (s == STATE.INPUTTED) {
                setupFormulaAndEnterCalculatedState(formula.getAsString(), iocv, fullContext);
                findValueAndEnterInputtedState(value, typeOfT, iocv);
            }

            return iocv;
        }

        if (value != null) {
            findValueAndEnterInputtedState(value, typeOfT, iocv);
            return iocv;
        }

        if (formula != null) {
            setupFormulaAndEnterCalculatedState(formula.getAsString(), iocv, fullContext);
            return iocv;
        }

        throw new RuntimeException("I should never be thrown...");
    }

    private void validateInputAndThrowExceptions(JsonElement value, JsonElement formula, JsonElement state, JsonElement json) throws JsonParseException {
        if (value == null && formula == null) {
            throw new JsonParseException("I don't know how to parse " + json + ", I don't have any information. I need at least a value or a formula.");
        }

        if (value != null && formula != null && state == null) {
            throw new JsonParseException("I don't know how to parse " + json + ", I can't find the state");
        }
    }

    private void setupFormulaAndEnterCalculatedState(String formula, InputtedOrCalculatedValue iocv, FullContext context) {
        try {
            Class<?> formulaClass = Class.forName(formula);
            Constructor<?> constructor = formulaClass.getConstructor(FullContext.class);
            Object newInstance = constructor.newInstance(context);
            ObservableFormula observableFormula = (ObservableFormula) newInstance;
            iocv.setFormula(observableFormula);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new JsonParseException(ex);
        }
    }

    private void findValueAndEnterInputtedState(JsonElement json, Type typeOfT, InputtedOrCalculatedValue iocv) {
        Unit parsedUnit = DeserializationUtils.getUnit(json, typeOfT, unitStringParserFactory);
        iocv.set(parsedUnit);
    }

}
