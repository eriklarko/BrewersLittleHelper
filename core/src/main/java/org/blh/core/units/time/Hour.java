package org.blh.core.units.time;

import org.blh.core.units.Unit;

/**
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:21:26 PM
 */
public class Hour extends Unit<Double> {

    public static final int CONVERSION_FACTOR = 60;

    public Hour(double value) {
        super(value);
    }

    public Hour(Minutes minutes) {
        super(minutes.value() / CONVERSION_FACTOR);
    }
}
