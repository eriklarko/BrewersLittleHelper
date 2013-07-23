package org.blh.core.formulas.carbonation;

import org.blh.core.formulas.carbonation.DynamicHenrysLaw;
import org.blh.core.units.CO2Volumes;
import org.blh.core.units.alcohol.ABV;
import org.blh.core.units.alcohol.ABW;
import org.blh.core.units.gravity.SpecificGravity;
import org.blh.core.units.pressure.Bar;
import org.blh.core.units.pressure.BarA;
import org.blh.core.units.pressure.PSI;
import org.blh.core.units.pressure.PSIA;
import org.blh.core.units.temperature.Celsius;
import org.blh.core.units.temperature.Fahrenheit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 6/25/13 10:47 PM
 */
public class DynamicHenrysLawTest {

    @Test
    public void testCalc() throws Exception {
        DynamicHenrysLaw f = new DynamicHenrysLaw();

        CO2Volumes carbonation = new CO2Volumes(2);
        SpecificGravity gravity = new SpecificGravity(1);

        ABV abv = new ABV(5);
        Celsius celsius = new Celsius(18);
        BarA barBaromPressure = new BarA(1);

        ABW abw = new ABW(abv);
        Fahrenheit fahrenheit = new Fahrenheit(celsius);
        PSIA psiBaromPressure = new PSIA(barBaromPressure);

        Bar bar = f.calc(gravity, abv, celsius, carbonation, barBaromPressure);
        PSI psi = f.calc(gravity, abw, fahrenheit, carbonation, psiBaromPressure);

        Assert.assertEquals(psi.toBar().value(), bar.value(), 0.001);
    }
}
