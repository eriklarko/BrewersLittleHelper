package test.blh.core.units.temperature;

import blh.core.units.temperature.Celsius;
import blh.core.units.temperature.Fahrenheit;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class FahrenheitTest {
    
    @Test
    public void testDouble() {
        Fahrenheit actual = new Fahrenheit(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testCelsius() {
        Fahrenheit actual = new Fahrenheit(new Celsius(2));
        Fahrenheit expected = new Fahrenheit(35.6);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToCelsius() {
        Celsius actual = new Fahrenheit(5).toCelsius();
        Celsius expected = new Celsius(-15);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}
