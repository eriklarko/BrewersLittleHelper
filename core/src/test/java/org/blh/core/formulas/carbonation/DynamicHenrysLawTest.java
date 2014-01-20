package org.blh.core.formulas.carbonation;

import org.blh.core.formula.carbonation.DynamicHenrysLaw;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.alcohol.ABW;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.pressure.Bar;
import org.blh.core.unit.pressure.BarA;
import org.blh.core.unit.pressure.PSI;
import org.blh.core.unit.pressure.PSIA;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.temperature.Fahrenheit;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Erik Larkö at 6/25/13 10:47 PM
 */
@Ignore
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

        Assert.assertEquals(psi.toBar().value(), bar.value());
    }
}
