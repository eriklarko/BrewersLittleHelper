package org.blh.core.unit;

/**
 * Describes generic something per something else values. E.g. Liters per hour.
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 * @since Jul 17, 2013 10:19:18 PM
 */
public class Fraction<Nominator, Denominator> extends Unit<Nominator> {

    public Fraction(Nominator value) {
        super(value);
    }
}
