package test.blh.core.formulas.carbonation;

import blh.core.formulas.carbonation.SimpleHenrysLaw;
import blh.core.units.CO2Volumes;
import blh.core.units.pressure.Bar;
import blh.core.units.pressure.PSI;
import blh.core.units.temperature.Celsius;
import blh.core.units.temperature.Fahrenheit;
import junit.framework.Assert;
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
        Assert.assertEquals(resultPSI.value(), barResultAsPSI.value(), 0.001);
    }
}
