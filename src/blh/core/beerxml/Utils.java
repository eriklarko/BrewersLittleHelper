package blh.core.beerxml;

import blh.core.units.Unit;

/**
 * 
 * @author thinner
 * @since May 23, 2013 10:56:04 PM
 */
public class Utils {

    public static String toStringOrNull(Unit unit) {
        if (unit == null) {
            return null;
        }
        if (unit.value() == null) {
            return null;
        }

        return String.valueOf(unit.value());
    }

    public static String toStringOrNull(String s) {
        return s;
    }

    public static String toStringOrNull(Object e) {
        if (e == null) {
            return null;
        }
        return String.valueOf(e);
    }

    public static String toStringOrNull(boolean b) {
        return String.valueOf(b);
    }

    public static String toStringOrNull(int i) {
        return String.valueOf(i);
    }
    
    public static String toStringOrNull(double d) {
        return String.valueOf(d);
    }

    private Utils() {
    }
}
