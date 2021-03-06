package org.blh.core.units.alcohol;

import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.alcohol.ABW;
import org.blh.core.unit.Factor;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class ABWTest {

    @Test
    public void testFactor() {
        ABW actual = new ABW(new Factor(1));
        Factor expected = new Factor(1);

        Assert.assertEquals(expected, actual.value());
    }

	@Test
    public void testDouble() {
        ABW actual = new ABW(1);
        double expected = 1;

        Assert.assertEquals(expected, actual.value().value(), 0.0001);
    }

    @Test
    public void testABV() {
        ABW actual = new ABW(new ABV(1));
        ABW expected = new ABW(0.8);

        Assert.assertEquals(expected, actual);
    }

    @Test
    /**
     * At relatively low abv, the alcohol percentage by weight is about 4/5 of the abv (e.g., 3.2% abw is equivalent to 4.0% abv)
     */
    public void testToABV() {
        ABV actual = new ABW(3.2).toABV();
        ABV expected = new ABV(4);

        Assert.assertEquals(expected, actual);
    }
}
