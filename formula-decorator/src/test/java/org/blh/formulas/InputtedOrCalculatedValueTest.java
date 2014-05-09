package org.blh.formulas;

import org.blh.formuladecorator.formulas.ObservableFormula;
import org.blh.formuladecorator.FullContext;
import org.blh.core.unit.DoubleUnit;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class InputtedOrCalculatedValueTest {

    @Test
    public void testInputConstructor() {
        DoubleUnit two = new DoubleUnit(2d) {};
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(two);

        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(2d, v.value().value(), 0);
    }

    @Test
    public void testCalculatedConstructor() {
        ObservableFormula<DoubleUnit> f = new ObservableFormula<DoubleUnit>(null) {

            @Override
            public DoubleUnit calc() {
                return new DoubleUnit(1) {};
            }

            @Override
            public String getSomeMathLangRepresentation() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

			@Override
			protected void registerDependentVariables(FullContext context) {
			}
        };
        FullContext context = new FullContext();

        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f);

        Assert.assertFalse(v.isInputted());
        Assert.assertEquals(1d, v.value().value(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testSetValueNull() {
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<DoubleUnit>(new DoubleUnit(2) {});
        v.setValue(null);
    }

    @Test
    public void testSetValueFromInputted() {
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<DoubleUnit>(new DoubleUnit(2d) {});
        v.setValue(new DoubleUnit(3d) {});

        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(3d, v.value().value(), 0);
    }

    @Test
    public void testSetValueFromCalculated() {
        ObservableFormula<DoubleUnit> f = new ObservableFormula<DoubleUnit>(null) {

            @Override
            public DoubleUnit calc() {
                return new DoubleUnit(1) {};
            }

            @Override
            public String getSomeMathLangRepresentation() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

			@Override
			protected void registerDependentVariables(FullContext context) {
			}
        };
        FullContext context = new FullContext();
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f);

        v.setValue(new DoubleUnit(3d) {});

        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(3d, v.value().value(), 0);
    }
}