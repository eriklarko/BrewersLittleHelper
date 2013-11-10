package org.blh.core.units.pressure;

import java.math.BigDecimal;
import org.blh.core.units.pressure.Bar;
import org.blh.core.units.pressure.BarA;
import org.blh.core.units.pressure.PSI;
import org.blh.core.units.pressure.PSIA;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class PSIATest {
    
    @Test
    public void testDouble() {
        PSIA actual = new PSIA(2);
        double expected = 2;
        
        Assert.assertEquals(expected, actual.value());
    }
    
    @Test
    public void testPSI() {
        PSIA actual = new PSIA(new PSI(2));
        PSIA expected = new PSIA(PSI.CONVERSION_FACTOR.add(new BigDecimal(2)));
        
        Assert.assertEquals(expected.value(), actual.value());
    }

    @Test
    public void testBar() {
        PSIA actual = new PSIA(new Bar(1));
        PSIA expected = new PSIA(new BigDecimal(2).multiply(PSI.CONVERSION_FACTOR));
        
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
        PSI actual = new PSIA(new BigDecimal(3).add(PSI.CONVERSION_FACTOR)).toPSI();
        PSI expected = new PSI(3);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
    
    @Test
    public void testToBar() {
        PSIA psiPressure = new PSIA(new BigDecimal(2).multiply(PSI.CONVERSION_FACTOR));
        Bar actual = psiPressure.toBar();
        Bar expected = new Bar(1);
        
        Assert.assertEquals(expected.value(), actual.value());
    }
}
