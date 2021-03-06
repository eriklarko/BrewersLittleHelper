package org.blh.core.formulas.carbonation;

import org.blh.core.formula.carbonation.SimpleHenrysLaw;
import org.blh.core.unit.CO2Volumes;
import org.blh.core.unit.pressure.Bar;
import org.blh.core.unit.pressure.PSI;
import org.blh.core.unit.temperature.Celsius;
import org.blh.core.unit.temperature.Fahrenheit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Erik Larkö at 5/29/13 2:35 PM
 */
public class SimpleHenrysLawTest {

    @Test
    public void testCalc() throws Exception {
        SimpleHenrysLaw henrysLaw = new SimpleHenrysLaw();
        CO2Volumes vols = new CO2Volumes(2);

        Fahrenheit temperatureF = new Fahrenheit(64.4);
        PSI resultPSI = henrysLaw.calc(vols, temperatureF);

        Celsius temperatureC = new Celsius(18);
        Bar resultBar = henrysLaw.calc(vols, temperatureC);

        PSI barResultAsPSI = new PSI(resultBar);
        Assert.assertEquals(resultPSI, barResultAsPSI);
    }
}
