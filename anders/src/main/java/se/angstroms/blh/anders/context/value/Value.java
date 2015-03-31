package se.angstroms.blh.anders.context.value;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.Unit;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.distance.Meters;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Liters;

import se.angstroms.blh.anders.context.value.parsing.ParseException;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParser;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParserFactory;

import javafx.beans.Observable;


/**
 * Defines all things about a beer recipe that has a value.
 *
 * @author eriklark
 */
public abstract class Value<T> implements Observable {

	public static enum Id {

		NOTHING("", null),
		BITTERNESS("Bitterness", IBU.class),
		OG("Original gravity", SpecificGravity.class), FG("Final Gravity", SpecificGravity.class),
		EXTRACTION_EFFICIENCY("Extraction efficiency", Factor.class),
		BOIL_TIME("", Minutes.class),
		ALCOHOL_CONTENT("Alcohol content", ABV.class),
		ELEVATION("", Meters.class),
		COOLING_LOSS("", Percentage.class), YEAST_ATTENUATION("", Percentage.class),
		PRE_MASH_VOLUME("Pre mash volume", Liters.class), PRE_BOIL_VOLUME("", Liters.class), POST_BOIL_VOLUME("Post boil volume", Liters.class), PRE_FERMENTATION_VOLUME("", Liters.class),
		HOP_ADDITIONS("Hops", null), FERMENTABLES("Fermentables", null), YEAST_ADDITIONS("Yeast", null);

		private static final UnitStringParserFactory stringParserFactory = new UnitStringParserFactory();

		private final String humanReadable;
		private final Class<? extends Unit<?>> unit;

		private Id(String humanReadable, Class<? extends Unit<?>> unit) {
			this.humanReadable = humanReadable;
			this.unit = unit;
		}

		public String getHumanReadable() {
			return humanReadable;
		}

		public Class<? extends Unit<?>> getUnit() {
			return unit;
		}

		public <T extends Unit<?>> UnitStringParser<T> getParser() {
			UnitStringParser<Unit<?>> parser = stringParserFactory.getParserFor(unit);
			return (String raw) -> {
				try {
					return (T) parser.parse(raw);
				} catch (Exception ex) {
					throw new ParseException("Unable to parse the given string into " + this.name(), ex);
				}
			};
		}
	}

	private static final UnitStringFormatter unitStringFormatter = new UnitStringFormatter();

	public String asString() {
		if (get() == null) {
			return "n/a";
		} else if (get() instanceof Unit) {
			Unit unit = (Unit) get();
			return unitStringFormatter.format(unit);
		} else {
			throw new IllegalStateException("Tried to get the string representation of a value that is not a unit");
		}
	}

	public abstract Id getValueType();

	public abstract T get();
}
