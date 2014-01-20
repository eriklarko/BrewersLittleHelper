package org.blh.core.formula.keghoselength;

import org.blh.core.unit.distance.Feet;
import org.blh.core.unit.distance.Inch;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.pressure.PSI;
import org.blh.core.unit.time.Seconds;

/**
 * From: http://www.mikesoltys.com/2012/09/17/determining-proper-hose-length-for-your-kegerator/
 *
 * Created by Erik Larkö at 6/23/13 4:27 PM
 */
public class Soltys  {

    public static final double HOSE_ROUGHNESS = 0.000016;
    public static final double VISCOSITY = 0.000016008023434;

    public Feet calc(SpecificGravity gravity, PSI kegPressure, Inch hoseDiameter, Feet tapHeight, Seconds pintFillTime) {
        double deltaP = kegPressure.value() * 144;
        double specificWeight = gravity.value() * 62.4;
        double a = deltaP / specificWeight - tapHeight.value();

        double d = hoseDiameter.value() / 12;
        double largeA = Math.pow(d, 2) * Math.PI / 4;
        double q = ((1d / 8) / 7.4805) / pintFillTime.value();
        double v = q / largeA;
        double f = swameeJain(d, v, HOSE_ROUGHNESS, VISCOSITY);
        double b = d / f * 2;

        double c = 32.2 / Math.pow(v, 2);
        return new Feet(a * b * c);
    }

    private double swameeJain(double d, double v, double hoseRoughness, double viscosity) {
        double eOverD = hoseRoughness / d;
        double nr = v * d / viscosity;

        double a = 1 / 3.7 * eOverD;
        double b = 5.74 / Math.pow(nr, 0.9);

        double denom = Math.log10(a  + b);
        return 0.25 / Math.pow(denom, 2);
    }
}
