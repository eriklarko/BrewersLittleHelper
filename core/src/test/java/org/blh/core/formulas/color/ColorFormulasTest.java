package org.blh.core.formulas.color;

import org.blh.core.formulas.Formula;
import org.blh.core.formulas.color.Daniels;
import org.blh.core.formulas.color.Morey;
import org.blh.core.formulas.color.Mosher;
import org.blh.core.uncategorized.FullContext;
import org.blh.core.uncategorized.InputtedOrCalculatedValue;
import org.blh.core.units.color.ColorPotential;
import org.blh.core.units.color.EBC;
import org.blh.core.units.color.Lovibond;
import org.blh.core.units.color.MaltColorUnit;
import org.blh.core.units.volume.USGallons;
import org.blh.core.units.weight.Lbs;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 6/28/13 5:20 PM
 */
public class ColorFormulasTest {

    @Test
    public void danielsTest() {
        Daniels f = new Daniels();
        double mcu = 12;
        EBC expectedEBCForMCU12 = new EBC(21.276);
        doAssert(f, mcu, expectedEBCForMCU12);
    }

    @Test
    public void moreyTest() {
        Morey f = new Morey();
        double mcu = 12;
        EBC expectedEBCForMCU12 = new EBC(16.1623191555);
        doAssert(f, mcu, expectedEBCForMCU12);
    }

    @Test
    public void mosherTest() {
        Mosher f = new Mosher();
        double mcu = 12;
        EBC expectedEBCForMCU12 = new EBC(16.35);
        doAssert(f, mcu, expectedEBCForMCU12);
    }


    private void doAssert(Formula<EBC> f, double mcuValue, EBC expected) {
        MaltColorUnit mcu = maltColorUnitValueOf(mcuValue);
        FullContext context = new FullContext();
        context.maltColorUnit = new InputtedOrCalculatedValue<MaltColorUnit>(mcu);

        EBC actual = f.calc(context);

        Assert.assertEquals(expected.value(), actual.value(), 0.01);
    }


    private MaltColorUnit maltColorUnitValueOf(double mcuValue) {
        return new MaltColorUnit(new ColorPotential(new Lovibond(mcuValue), new Lbs(1)), new USGallons(1));
    }
}