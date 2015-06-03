package org.blh.value;

import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputtedOrCalculatedValue_InitialValuesTest {

    public static final Unit<Double> MOCK_UNIT = new Unit<Double>(1d) {};
    public static final ObservableFormula<Unit<Double>> MOCK_FORMULA = Mockito.mock(ObservableFormula.class);

    @Test
    public void valueIsSetToFormulasValue() {
        Mockito.when(MOCK_FORMULA.calc()).thenReturn(MOCK_UNIT);
        InputtedOrCalculatedValue<Unit<Double>> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);

        assertThat(value.get(), is(MOCK_UNIT));
    }
}
