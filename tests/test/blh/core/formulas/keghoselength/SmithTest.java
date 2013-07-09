package test.blh.core.formulas.keghoselength;

import blh.core.formulas.keghoselength.Smith;
import blh.core.uncategorized.FullContext;
import blh.core.units.distance.Meters;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thinner
 */
public class SmithTest {

    /**
     * Test of calc method, of class Smith.
     */
    @Test
    public void testCalc() {
        System.out.println("calc");
        FullContext context = null;
        Smith instance = new Smith();
        Meters expResult = null;
        Meters result = instance.calc(context);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
