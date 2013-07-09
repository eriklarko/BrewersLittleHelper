/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.blh.core.units;

import blh.core.units.BJCPTasteRating;
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
