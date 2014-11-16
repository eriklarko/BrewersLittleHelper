package se.angstroms.blh.anders.context.value;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;

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
        PRE_MASH_VOLUME, PRE_BOIL_VOLUME, POST_BOIL_VOLUME, PRE_FERMENTATION_VOLUME;

    }

    T get();
}
