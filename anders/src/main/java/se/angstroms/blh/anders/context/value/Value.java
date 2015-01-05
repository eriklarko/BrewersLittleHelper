package se.angstroms.blh.anders.context.value;

import javafx.beans.Observable;

/**
 * Defines all things about a beer recipe that has a value.
 *
 * @author eriklark
 */
public interface Value<T> extends Observable {

	public static enum Id {

		NOTHING(""),
		BITTERNESS("Bitterness"),
		OG("Original gravity"), FG("Final Gravity"),
		EXTRACTION_EFFICIENCY("Extraction efficiency"),
		BOIL_TIME(""),
		ALCOHOL_CONTENT("Alcohol content"),
		ELEVATION(""),
		COOLING_LOSS(""), YEAST_ATTENUATION(""),
		PRE_MASH_VOLUME("Pre mash volume"), PRE_BOIL_VOLUME(""), POST_BOIL_VOLUME("Post boil volume"), PRE_FERMENTATION_VOLUME(""),
		HOP_ADDITIONS(""), FERMENTABLES(""), YEAST_ADDITIONS("");

		private final String humanReadable;

		private Id(String humanReadable) {
			this.humanReadable = humanReadable;
		}

		public String getHumanReadable() {
			return humanReadable;
		}
	}

	Id getValueType();

	T get();
}
