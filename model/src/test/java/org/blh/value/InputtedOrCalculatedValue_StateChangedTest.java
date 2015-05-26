package org.blh.value;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.blh.core.unit.Unit;
import org.blh.formulas.ObservableFormula;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;

public class InputtedOrCalculatedValue_StateChangedTest {

    public static final Unit<?> MOCK_UNIT = new Unit<Object>(null) {};
    public static final ObservableFormula<Unit<?>> MOCK_FORMULA = Mockito.mock(ObservableFormula.class);

    @Test
    public void listenerIsInvokedAfterStateChange_FromInputtedToCalculated() {
        ChangeListener<InputtedOrCalculatedValue.State> listener = Mockito.mock(ChangeListener.class);
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_UNIT);

        InputtedOrCalculatedValue.State firstState = value.getState();
        assertThat(firstState, is(not(InputtedOrCalculatedValue.State.CALCULATED)));

        value.stateProperty().addListener(listener);
        value.setFormula(MOCK_FORMULA);

        Mockito.verify(listener, times(1)).changed(value.stateProperty(), firstState , InputtedOrCalculatedValue.State.CALCULATED);
    }

    @Test
    public void listenerIsInvokedAfterStateChange_FromCalculatedToInputted() {
        ChangeListener<InputtedOrCalculatedValue.State> listener = Mockito.mock(ChangeListener.class);
        InputtedOrCalculatedValue<Unit<?>> value = new InputtedOrCalculatedValue<>(MOCK_FORMULA);

        InputtedOrCalculatedValue.State firstState = value.getState();
        assertThat(firstState, is(not(InputtedOrCalculatedValue.State.INPUTTED)));

        value.stateProperty().addListener(listener);
        value.set(MOCK_UNIT);

        Mockito.verify(listener, times(1)).changed(value.stateProperty(), firstState , InputtedOrCalculatedValue.State.INPUTTED);
    }
}
