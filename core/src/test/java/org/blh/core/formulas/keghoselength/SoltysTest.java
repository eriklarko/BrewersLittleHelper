package org.blh.core.formulas.keghoselength;

import org.blh.core.formula.keghoselength.Soltys;
import org.blh.core.unit.distance.Feet;
import org.blh.core.unit.distance.Inch;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.pressure.PSI;
import org.blh.core.unit.time.Seconds;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 6/23/13 5:15 PM
 */
public class SoltysTest {

    @Test
    public void testCalc() {
        Soltys calculator = new Soltys();

        SpecificGravity gravity = new SpecificGravity(1.09);
        PSI kegPressure = new PSI(13.9);
        Inch hoseDiam = new Inch(0.1875);
        Feet tapHeight = new Feet(1.5);
        Seconds fillTime = new Seconds(10);

        Feet length = calculator.calc(gravity, kegPressure, hoseDiam, tapHeight, fillTime);
        Feet expected = new Feet(10.8799);

        Assert.assertEquals(expected, length);
    }
}
