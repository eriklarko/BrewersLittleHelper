package org.blh.core.formula.carbonation;

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

/**
 * From:
 * https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1
 *     &ved=0CCkQFjAA&url=http%3A%2F%2Fwww.iul-instruments.de%2Fpdf%2Fvitalsensors_2.pdf
 *     &ei=EqvIUdHiHcr24QSFkoCoBg&usg=AFQjCNHuDfxIMs31EoA_WvOCVycK0BD1Tg&bvm=bv.48293060,d.bGE&cad=rja
 *
 * Created by Erik Larkö at 5/28/13 7:10 AM
 */
public class DynamicHenrysLaw  {

    public PSI calc(SpecificGravity gravity, ABW abw, Fahrenheit temperature, CO2Volumes desiredVolumes, PSIA barometricPressure) {
        double d = (temperature.value() + 12.4) * gravity.value() * (1 + abw.value().value() / 0.789);
        d = d * desiredVolumes.value();
        d = d / 5.16;
        d -= barometricPressure.value();

        return new PSI(d);
    }

    public Bar calc(SpecificGravity gravity, ABV abv, Celsius temperature, CO2Volumes desiredVolumes, BarA barometricPressure) {
        double d = (1.8 * temperature.value() + 44.4) * gravity.value() * (1 + abv.value().value() / 0.98625);
        d = d * desiredVolumes.value();
        d = d / 5.16;
        d -= barometricPressure.value();

        return new PSI(d).toBar();
    }
}
