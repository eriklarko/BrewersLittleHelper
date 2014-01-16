package org.blh.core.units.pressure;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class PSIATest {

	private static final double DELTA = 0.00001;
    
    @Test
    public void testDouble() {
        PSIA actual = new PSIA(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value(), DELTA);
    }
    
    @Test
    public void testPSI() {
        PSIA actual = new PSIA(new PSI(2));
        PSIA expected = new PSIA(PSI.CONVERSION_FACTOR + 2);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testBar() {
        PSIA actual = new PSIA(new Bar(1));
        PSIA expected = new PSIA(2 * PSI.CONVERSION_FACTOR);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
    
    @Test
    public void testBarA() {
        PSIA actual = new PSIA(new BarA(1));
        PSIA expected = new PSIA(PSI.CONVERSION_FACTOR);
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testToPSI() {
        PSI actual = new PSIA(3 + PSI.CONVERSION_FACTOR).toPSI();
        PSI expected = new PSI(3);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
    
    @Test
    public void testToBar() {
        PSIA psiPressure = new PSIA(2 * PSI.CONVERSION_FACTOR);
        Bar actual = psiPressure.toBar();
        Bar expected = new Bar(1);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}
