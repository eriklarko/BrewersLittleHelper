package org.blh.core.uncategorized;

import org.blh.core.formulas.Formula;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class InputtedOrCalculatedValueTest {

    @Test
    public void testInputConstructor() {
        InputtedOrCalculatedValue<Double> v = new InputtedOrCalculatedValue<>(2d);
        
        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(2d, v.value(), 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testInputConstructorNull() {
        new InputtedOrCalculatedValue<>(null);
    }

    @Test
    public void testCalculatedConstructor() {
        Formula<Double> f = new Formula<Double>() {
            
            @Override
            public Double calc(FullContext context) {
                return 1d;
            }
        };
        FullContext context = new FullContext();
        
        InputtedOrCalculatedValue<Double> v = new InputtedOrCalculatedValue<>(f, context);
        
        Assert.assertFalse(v.isInputted());
        Assert.assertEquals(1d, v.value(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void testSetValueNull() {
        InputtedOrCalculatedValue<Double> v = new InputtedOrCalculatedValue<>(2d);
        v.setValue(null);
    }
    
    @Test
    public void testSetValueFromInputted() {
        InputtedOrCalculatedValue<Double> v = new InputtedOrCalculatedValue<>(2d);
        v.setValue(3d);
        
        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(3d, v.value(), 0);
    }
    
    @Test
    public void testSetValueFromCalculated() {
        Formula<Double> f = new Formula<Double>() {
            
            @Override
            public Double calc(FullContext context) {
                return 1d;
            }
        };
        FullContext context = new FullContext();
        InputtedOrCalculatedValue<Double> v = new InputtedOrCalculatedValue<>(f, context);
        
        v.setValue(3d);
        
        Assert.assertTrue(v.isInputted());
        Assert.assertEquals(3d, v.value(), 0);
    }
}