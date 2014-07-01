package se.angstroms.blh.anders.context.value;

import org.blh.core.unit.Unit;

/**
 * Defines all things about a beer recipe that has a value.
 *
 * @author eriklark
 */
public interface Value<T extends Unit<?>> {

    public static enum Id {

        NOTHING,
        BITTERNESS,
        OG, FG,
        EXTRACTION_EFFICIENCY,
        BOIL_TIME,
        ALCOHOL_CONTENT,
        ELEVATION,
        COOLING_LOSS, YEAST_ATTENUATION,
        PRE_BOIL_VOLUME, POST_BOIL_VOLUME;

    }

    T get();
}
