package test.blh.core.formulas.keghoselength;

import blh.core.formulas.keghoselength.Soltys;
import blh.core.units.CO2Volumes;
import blh.core.units.distance.Feet;
import blh.core.units.distance.Inch;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.time.Seconds;

/**
 * Created by Erik Larkö at 6/23/13 5:15 PM
 */
public class SoltysTest {

    public void testCalc() {
        Soltys calculator = new Soltys();

        SpecificGravity gravity = new SpecificGravity(1);
        CO2Volumes volumes = new CO2Volumes(2);
        Inch hoseDiam = new Inch(3);
        Feet tapHeight = new Feet(4);
        Seconds fillTime = new Seconds(5);

        Feet length = calculator.calc(gravity, volumes, hoseDiam, tapHeight, fillTime);
    }
}
