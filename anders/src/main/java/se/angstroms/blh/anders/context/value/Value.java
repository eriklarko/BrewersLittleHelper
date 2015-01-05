package se.angstroms.blh.anders.context.value;


import javafx.beans.Observable;

/**
 * Defines all things about a beer recipe that has a value.
 *
 * @author eriklark
 */
public interface Value<T> extends Observable {

    public static enum Id {

        NOTHING,
        BITTERNESS,
        OG, FG,
        EXTRACTION_EFFICIENCY,
        BOIL_TIME,
        ALCOHOL_CONTENT,
        ELEVATION,
        COOLING_LOSS, YEAST_ATTENUATION,
        PRE_MASH_VOLUME, PRE_BOIL_VOLUME, POST_BOIL_VOLUME, PRE_FERMENTATION_VOLUME,

		HOP_ADDITIONS, FERMENTABLES, YEAST_ADDITIONS;

    }

	Id getValueType();

    T get();
}
