package org.blh.value;

import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;
import org.junit.Test;

public class InputtedOrCalculatedValue_NullTest {

    private static final Unit<?> MOCK_UNIT = new Unit<Object>(null) {};

    @Test(expected = NullPointerException.class)
    public void nullInitialValueThrowsNullPointer() {
        Unit<?> u = null;
        new InputtedOrCalculatedValue<>(u);
    }

    @Test(expected = NullPointerException.class)
    public void nullInitialFormulaThrowsNullPointer() {
        ObservableFormula<Unit<?>> f = null;
        new InputtedOrCalculatedValue<>(f);
    }

    @Test(expected = NullPointerException.class)
    public void setNullValueThrowsNullPointer() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);

        Unit<?> u = null;
        value.setValue(u);
    }

    @Test(expected = NullPointerException.class)
    public void setNullFormulaThrowsNullPointer() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);

        ObservableFormula<Unit<?>> f = null;
        value.setFormula(f);
    }
}