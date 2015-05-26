package org.blh.value;

import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputtedOrCalculatedValue_StateTest {

    public static final Unit<?> MOCK_UNIT = new Unit<Object>(null) {};
    public static final ObservableFormula<Unit<?>> MOCK_FORMULA = Mockito.mock(ObservableFormula.class);

    @Test
    public void stateIsInputted_FromConstructor() {
        InputtedOrCalculatedValue<?> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);

        InputtedOrCalculatedValue.State expected = InputtedOrCalculatedValue.State.INPUTTED;
        InputtedOrCalculatedValue.State actual = value.getState();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stateIsInputted_AfterInvokingSet() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);
        assertThat(value.getState(), is(not(InputtedOrCalculatedValue.State.INPUTTED)));

        value.set(MOCK_UNIT);

        InputtedOrCalculatedValue.State expected = InputtedOrCalculatedValue.State.INPUTTED;
        InputtedOrCalculatedValue.State actual = value.getState();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stateIsInputted_AfterInvokingSetValue() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);
        assertThat(value.getState(), is(not(InputtedOrCalculatedValue.State.INPUTTED)));

        value.setValue(MOCK_UNIT);

        InputtedOrCalculatedValue.State expected = InputtedOrCalculatedValue.State.INPUTTED;
        InputtedOrCalculatedValue.State actual = value.getState();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stateIsCalculated_FromConstructor() {
        InputtedOrCalculatedValue<?> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);

        InputtedOrCalculatedValue.State expected = InputtedOrCalculatedValue.State.CALCULATED;
        InputtedOrCalculatedValue.State actual = value.getState();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stateIsCalculated_AfterInvokingSetFormula() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);
        assertThat(value.getState(), is(not(InputtedOrCalculatedValue.State.CALCULATED)));

        value.setFormula(MOCK_FORMULA);

        InputtedOrCalculatedValue.State expected = InputtedOrCalculatedValue.State.CALCULATED;
        InputtedOrCalculatedValue.State actual = value.getState();

        Assert.assertEquals(expected, actual);
    }
}
