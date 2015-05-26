package org.blh.value;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

public class InputtedOrCalculatedValue_ValueChangedTest {

    public static final Unit<?> MOCK_UNIT = new Unit<Object>(null) {};
    public static final ObservableFormula<Unit<?>> MOCK_FORMULA = Mockito.mock(ObservableFormula.class);

    @Test
    public void listenerInvokedWhenChangingToCalculatedState() {
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);
        assertThat(value.getState(), is(not(InputtedOrCalculatedValue.State.CALCULATED)));

        ChangeListener<Unit<?>> listener = Mockito.mock(ChangeListener.class);
        value.addListener(listener);
        value.setFormula(MOCK_FORMULA);

        Mockito.verify(listener, times(1)).changed(value, Mockito.any(), MOCK_UNIT);
    }

    @Test
    public void listenerInvokedWhenChangingToInputtedState() {
        Assert.fail();
    }

    @Test
    public void listenerInvokedWhenChangingValue() {
        Assert.fail();
    }

    @Test
    public void listenerInvokedWhenChangingFormula() {
        Assert.fail();
    }
}
