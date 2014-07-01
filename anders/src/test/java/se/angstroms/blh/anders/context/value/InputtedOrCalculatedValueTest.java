package se.angstroms.blh.anders.context.value;

import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import org.blh.core.unit.DoubleUnit;
import se.angstroms.blh.anders.formulas.NopFormula;
import org.junit.Assert;
import org.junit.Test;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue.STATE;

/**
 *
 * @author thinner
 */
public class InputtedOrCalculatedValueTest {

    private static class DoubleUnitImpl extends DoubleUnit {

        public DoubleUnitImpl(double value) {
            super(value);
        }
    }

    @Test
    public void testInputConstructor() {
        DoubleUnit two = new DoubleUnit(2d) {};
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(two, new NopFormula<>(new DoubleUnitImpl(2), null));

        Assert.assertEquals(STATE.INPUTTED, v.stateProperty().get());
        Assert.assertEquals(2d, v.get().value(), 0);
    }

    @Test
    public void testCalculatedConstructor() {
        ObservableFormula<DoubleUnit> f = new NopFormula<>(new DoubleUnitImpl(1), null);

        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f);

        Assert.assertEquals(STATE.CALCULATED, v.stateProperty().get());
        Assert.assertEquals(1d, v.get().value(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testSetValueNull() {
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(new DoubleUnitImpl(2), new NopFormula<>(new DoubleUnitImpl(2), null));
        v.set(null);
    }

    @Test
    public void testSetValueFromInputted() {
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(new DoubleUnitImpl(2d), new NopFormula<>(new DoubleUnitImpl(2), null));
        v.set(new DoubleUnit(3d) {});

        Assert.assertEquals(STATE.INPUTTED, v.stateProperty().get());
        Assert.assertEquals(3d, v.get().value(), 0);
    }

    @Test
    public void testSetValueFromCalculated() {
        ObservableFormula<DoubleUnit> f = new NopFormula<>(new DoubleUnitImpl(1), null);
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f);

        v.set(new DoubleUnit(3d) {});

        Assert.assertEquals(STATE.INPUTTED, v.stateProperty().get());
        Assert.assertEquals(3d, v.get().value(), 0);
    }
}