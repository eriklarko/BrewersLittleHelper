package org.blh.core.units.distance;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class InchTest {

    @Test
    public void testDouble() {
        Inch actual = new Inch(2);
        BigDecimal expected = new BigDecimal(2);
        
        Assert.assertEquals(expected, actual.value());
    }

    @Test
    public void testMeters() {
        Inch actual = new Inch(new Meters(2));
        Inch expected = new Inch(78.7401575);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToMeters() {
        Meters actual = new Inch(2).toMeters();
        Meters expected = new Meters(0.0508);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}
