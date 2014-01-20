package org.blh.core.units;

import org.blh.core.unit.BJCPTasteRating;
import org.junit.Test;

/**
 *
 * @author thinner
 */
public class BJCPTasteRatingTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testLowerThanZero() {
        new BJCPTasteRating(-0.1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGreaterThan50() {
        new BJCPTasteRating(50.1);
    }
    
    @Test
    public void testInRange() {
        new BJCPTasteRating(7);
    }
}
