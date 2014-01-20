package org.blh.core.formulas.color;

import org.blh.core.formula.color.Daniels;
import org.blh.core.formula.color.Mosher;
import org.blh.core.formula.color.Morey;
import org.blh.core.unit.color.ColorPotential;
import org.blh.core.unit.color.EBC;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.color.MaltColorUnit;
import org.blh.core.unit.volume.USGallons;
import org.blh.core.unit.weight.Lbs;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 6/28/13 5:20 PM
 */
public class ColorFormulasTest {

    @Test
    public void danielsTest() {
        Daniels f = new Daniels();
        double mcuValue = 12;

        MaltColorUnit mcu = maltColorUnitValueOf(mcuValue);
        EBC expectedEBCForMCU12 = new EBC(21.276);
        EBC actual = f.calc(mcu).toEBC();
        Assert.assertEquals(expectedEBCForMCU12, actual);
    }

    @Test
    public void moreyTest() {
        Morey f = new Morey();
        double mcuValue = 12;

        MaltColorUnit mcu = maltColorUnitValueOf(mcuValue);
        EBC expectedEBCForMCU12 = new EBC(16.1623191555);
        EBC actual = f.calc(mcu);
        Assert.assertEquals(expectedEBCForMCU12, actual);
    }

    @Test
    public void mosherTest() {
        Mosher f = new Mosher();
        double mcuValue = 12;

        MaltColorUnit mcu = maltColorUnitValueOf(mcuValue);
        EBC expectedEBCForMCU12 = new EBC(16.35);
		expectedEBCForMCU12.setDelta(2);
        EBC actual = f.calc(mcu);
        Assert.assertEquals(expectedEBCForMCU12, actual);
    }

    private MaltColorUnit maltColorUnitValueOf(double mcuValue) {
        return new MaltColorUnit(new ColorPotential(new Lovibond(mcuValue), new Lbs(1)), new USGallons(1));
    }
}
