package org.blh.formulas;

import org.blh.formuladecorator.formulas.Formula;
import org.blh.formuladecorator.FullContext;
import org.blh.formuladecorator.InputtedOrCalculatedValue;
import org.blh.core.unit.DoubleUnit;
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

    @Test(expected = NullPointerException.class)
    public void testInputConstructorNull() {
        new InputtedOrCalculatedValue<>(null);
    }

    @Test
    public void testCalculatedConstructor() {
        Formula<DoubleUnit> f = new Formula<DoubleUnit>() {

            @Override
            public DoubleUnit calc(FullContext context) {
                return new DoubleUnit(1) {};
            }

            @Override
            public String getSomeMathLangRepresentation() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        FullContext context = new FullContext();

        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f, context);

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
        Formula<DoubleUnit> f = new Formula<DoubleUnit>() {

            @Override
            public DoubleUnit calc(FullContext context) {
                return new DoubleUnit(1) {};
            }

            @Override
            public String getSomeMathLangRepresentation() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        FullContext context = new FullContext();
        InputtedOrCalculatedValue<DoubleUnit> v = new InputtedOrCalculatedValue<>(f, context);

        v.setValue(new DoubleUnit(3d) {});

        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(3d, v.value().value(), 0);
    }
}