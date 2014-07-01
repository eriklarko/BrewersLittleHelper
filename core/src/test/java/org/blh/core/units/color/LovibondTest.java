package org.blh.core.units.color;

import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.color.SRM;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author eriklark
 */
public class LovibondTest {

    @Test
    public void testFromSrm() {
        Lovibond expected = new Lovibond(1.299276539);
        Lovibond actual = new Lovibond(new SRM(1));

        Assert.assertEquals(expected, actual);
    }
}
