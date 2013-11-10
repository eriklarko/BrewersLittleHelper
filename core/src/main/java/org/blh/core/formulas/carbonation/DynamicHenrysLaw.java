package org.blh.core.formulas.carbonation;

import org.blh.core.formulas.Formula;
import org.blh.core.uncategorized.FullContext;
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

/**
 * From:
 * https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&ved=0CCkQFjAA&url=http%3A%2F%2Fwww.iul-instruments.de%2Fpdf%2Fvitalsensors_2.pdf&ei=EqvIUdHiHcr24QSFkoCoBg&usg=AFQjCNHuDfxIMs31EoA_WvOCVycK0BD1Tg&bvm=bv.48293060,d.bGE&cad=rja
 *
 * Created by Erik Larkö at 5/28/13 7:10 AM
 */
public class DynamicHenrysLaw implements Formula<Bar> {

    @Override
    public Bar calc(FullContext context) {
        SpecificGravity finalGravity = context.finalGravity.value();
        ABV abv = context.alcoholContent.value();
        //Celsius carbonationTemperature = context.fer
        //CO2Volumes desiredVolumes = context.
        //BarA barometricPressure = context.

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PSI calc(SpecificGravity gravity, ABW abw, Fahrenheit temperature, CO2Volumes desiredVolumes, PSIA barometricPressure) {
        double d = (temperature.inexactValue() + 12.4) * gravity.inexactValue() * (1 + abw.value().inexactValue() / 0.789);
        d = d * desiredVolumes.inexactValue();
        d = d / 5.16;
        d -= barometricPressure.inexactValue();

        return new PSI(d);
    }

    public Bar calc(SpecificGravity gravity, ABV abv, Celsius temperature, CO2Volumes desiredVolumes, BarA barometricPressure) {
        double d = (1.8 * temperature.inexactValue() + 44.4) * gravity.inexactValue() * (1 + abv.value().inexactValue() / 0.98625);
        d = d * desiredVolumes.inexactValue();
        d = d / 5.16;
        d -= barometricPressure.inexactValue();

        return new PSI(d).toBar();
    }
}
