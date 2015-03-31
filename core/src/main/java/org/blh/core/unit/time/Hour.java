package org.blh.core.unit.time;

import org.blh.core.unit.DoubleUnit;

/**
 * The value holds a number of hours.
 * 
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:21:26 PM
 */
public class Hour extends DoubleUnit {

    public static final int CONVERSION_FACTOR = 60;

    public Hour(double value) {
        super(value);
    }

    public Hour(Minutes minutes) {
        super(minutes.value() / CONVERSION_FACTOR);
    }

	@Override
	public Hour deriveNew(double d) {
		return new Hour(d);
	}
}
