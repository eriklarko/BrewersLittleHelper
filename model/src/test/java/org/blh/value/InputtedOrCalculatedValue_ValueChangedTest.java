package org.blh.value;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.core.unit.distance.Meters;
import org.blh.formulas.ObservableFormula;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

public class InputtedOrCalculatedValue_ValueChangedTest {

    public static final Unit<Double> MOCK_UNIT = new Unit<Double>(1d) {};
    public static final ObservableFormula<Unit<Double>> MOCK_FORMULA = Mockito.mock(ObservableFormula.class);

    @Test
    public void invalidationListenerInvokedWhenChangingToCalculatedState() {
        Unit<Double> newValue = new Meters(1337);
        assertThat(newValue, is(not(MOCK_UNIT)));

        InputtedOrCalculatedValue<Unit<Double>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);
        Mockito.when(MOCK_FORMULA.calc()).thenReturn(newValue);

        ChangeListener<Unit<?>> listener = Mockito.mock(ChangeListener.class);
        value.addListener(listener);
        value.setFormula(MOCK_FORMULA);

        Mockito.verify(listener, times(1)).changed(value, MOCK_UNIT, newValue);
    }

    @Test
    public void listenerInvokedWhenChangingToInputtedState() {
        Unit<Double> newValue = new Meters(1337);
        assertThat(newValue, is(not(MOCK_UNIT)));
        Mockito.when(MOCK_FORMULA.calc()).thenReturn(MOCK_UNIT);

        InputtedOrCalculatedValue<Unit<Double>> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);
        ChangeListener<Unit<?>> listener = Mockito.mock(ChangeListener.class);
        value.addListener(listener);
        value.set(newValue);

        Mockito.verify(listener, times(1)).changed(value, MOCK_UNIT, newValue);
    }

    @Test
    public void listenerInvokedWhenChangingValue() {
        Unit<Double> newValue = new Meters(2d);
        assertThat(newValue, is(not(MOCK_UNIT)));

        InputtedOrCalculatedValue<Unit<Double>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);
        ChangeListener<Unit<?>> listener = Mockito.mock(ChangeListener.class);
        value.addListener(listener);
        value.set(newValue);

        Mockito.verify(listener, times(1)).changed(value, MOCK_UNIT, newValue);
    }

    @Test
    public void listenerInvokedWhenChangingFormula() {
        Unit<Double> firstValue = new Meters(1337);
        Unit<Double> secondValue = new Meters(1339);
        assertThat(firstValue, is(not(secondValue)));

        ObservableFormula<Unit<Double>> firstFormula = Mockito.mock(ObservableFormula.class);
        ObservableFormula<Unit<Double>> secondFormula = Mockito.mock(ObservableFormula.class);
        Mockito.when(firstFormula.calc()).thenReturn(firstValue);
        Mockito.when(secondFormula.calc()).thenReturn(secondValue);

        InputtedOrCalculatedValue<Unit<Double>> value = new InputtedOrCalculatedValue<>(firstFormula);
        ChangeListener<Unit<?>> listener = Mockito.mock(ChangeListener.class);
        value.addListener(listener);
        value.setFormula(secondFormula);

        Mockito.verify(listener, times(1)).changed(value, firstValue, secondValue);
    }
}
