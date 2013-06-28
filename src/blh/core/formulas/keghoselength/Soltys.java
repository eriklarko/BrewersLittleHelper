package blh.core.formulas.keghoselength;

import blh.core.formulas.Formula;
import blh.core.uncategorized.FullContext;
import blh.core.units.CO2Volumes;
import blh.core.units.distance.Feet;
import blh.core.units.distance.Inch;
import blh.core.units.distance.Meters;
import blh.core.units.gravity.SpecificGravity;
import blh.core.units.time.Seconds;

/**
 *
 * From: http://www.mikesoltys.com/2012/09/17/determining-proper-hose-length-for-your-kegerator/
 *
 * Created by Erik Larkö at 6/23/13 4:27 PM
 */
public class Soltys implements Formula<Meters> {

    public static final double HOSE_ROUGHNESS = 0.000016;
    public static final double VISCOSITY = 0.000016008023434;

    @Override
    public Meters calc(FullContext context) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Feet calc(SpecificGravity gravity, CO2Volumes carbonation, Inch hoseDiameter, Feet tapHeight, Seconds pintFillTime) {
        double deltaP = carbonation.value() * 144;
        double specificWeight = gravity.value() * 62.4;
        double a = deltaP / specificWeight - tapHeight.value();

        double D = hoseDiameter.value() / 12;
        double A = Math.pow(D, 2) * Math.PI / 4;
        double Q = 1 / 8 / 7.4805 / pintFillTime.value();
        double V = Q / A;
        double f = swameeJain(D, A, Q, V, HOSE_ROUGHNESS, VISCOSITY);
        double b = D / f;

        double c = 2 * 32.2 / Math.pow(V,2);
        return new Feet(a * b * c);
    }

    private double swameeJain(double D, double A, double Q, double V, double hoseRoughness, double viscosity) {
        double eOverD = hoseRoughness / D;
        double N_R = V * D / viscosity;

        double denom = Math.log10(1/3.7 * eOverD + 5.74 * Math.pow(N_R, 0.9));
        return 0.25 / Math.pow(denom, 2);
    }
}
