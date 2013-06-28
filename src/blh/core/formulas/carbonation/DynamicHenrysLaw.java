package blh.core.formulas.carbonation;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.CO2Volumes;
import blh.core.units.alcohol.ABV;
import blh.core.units.alcohol.ABW;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.pressure.Bar;
import blh.core.units.pressure.BarA;
import blh.core.units.pressure.PSI;
import blh.core.units.pressure.PSIA;
import blh.core.units.temperature.Celsius;
import blh.core.units.temperature.Fahrenheit;

/**
 * From: https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CCkQFjAA&url=http%3A%2F%2Fwww.iul-instruments.de%2Fpdf%2Fvitalsensors_2.pdf&ei=EqvIUdHiHcr24QSFkoCoBg&usg=AFQjCNHuDfxIMs31EoA_WvOCVycK0BD1Tg&bvm=bv.48293060,d.bGE&cad=rja
 *
 * Created by Erik Larkö at 5/28/13 7:10 AM
 */
public class DynamicHenrysLaw implements Formula<PSI>{
    @Override
    public PSI calc(FullContext context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PSI calc(SpecificGravity gravity, ABW abw, Fahrenheit temperature, CO2Volumes desiredVolumes, PSIA barometricPressure) {
        double d = (temperature.value() + 12.4) * gravity.value()  * (1 + abw.value().value() / 0.789);
        d = d * desiredVolumes.value();
        d = d / 5.16;
        d -= barometricPressure.value();

        return new PSI(d);
    }

    public Bar calc(SpecificGravity gravity, ABV abv, Celsius temperature, CO2Volumes desiredVolumes, BarA barometricPressure) {
        double d = (1.8 * temperature.value() + 44.4) * gravity.value()  * (1 + abv.value().value() / 0.98625);
        d = d * desiredVolumes.value();
        d = d / 5.16;
        d -= barometricPressure.value();

        return new PSI(d).asBar();
    }
}
