package se.angstroms.blh.anders.context.value.parsing;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;
import org.blh.core.unit.alcohol.ABV;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import org.blh.core.unit.volume.Liters;
import se.angstroms.blh.anders.context.value.Value;

/**
 * Maps Units and ValudIds with methods that take Strings and turns them into
 * said Units.
 *
 * @author eriklark
 */
public class UnitStringParserFactory {

    public <T extends Unit<?>> UnitStringParser<T> getParserFor(Value.Id type) {
        UnitStringParser<Unit<?>> parser = doGetParserFor(type);
        return (String raw) -> {
            try {
                return (T) parser.parse(raw);
            } catch (Exception ex) {
                throw new ParseException("Unable to parse the given string into " + type.name(), ex);
            }
        };
    }

    public <T extends Unit<?>> UnitStringParser<T> getParserFor(Class<? extends Unit<?>> unitClass) {
        if (unitClass == SpecificGravity.class) {
            return (String raw) -> {
                Double d = Double.parseDouble(raw);
                return (T) new SpecificGravity(d);
            };
        }

        if (unitClass == Factor.class) {
            return (String raw) -> {
                Double d = Double.parseDouble(raw);
                return (T) new Factor(d);
            };
        }

        if (unitClass == IBU.class) {
            return (String raw) -> {
                Double d = Double.parseDouble(raw);
                return (T) new IBU(d);
            };
        }

        if (unitClass == ABV.class) {
            return (String raw) -> {
                Double d = Double.parseDouble(raw);
                return (T) new ABV(d);
            };
        }

        if (unitClass == Liters.class) {
            return (String raw) -> {
                Double d = Double.parseDouble(raw);
                return (T) new Liters(d);
            };
        }

        throw new IllegalArgumentException("I don't know how to turn strings into " + unitClass.getCanonicalName() + " yet");
    }

    private <T extends Unit<?>> UnitStringParser<T> doGetParserFor(Value.Id type) {
        switch (type) {
			case OG:
            case FG:
				return getParserFor(SpecificGravity.class);
            case EXTRACTION_EFFICIENCY:
                return getParserFor(Factor.class);
            case BITTERNESS:
                return getParserFor(IBU.class);
            case ALCOHOL_CONTENT:
                return getParserFor(ABV.class);
            case PRE_MASH_VOLUME:
            case PRE_BOIL_VOLUME:
            case POST_BOIL_VOLUME:
            case PRE_FERMENTATION_VOLUME:
                return getParserFor(Liters.class);
			default:
				throw new IllegalArgumentException("I don't know how to turn strings into " + type.name() + " yet");
		}
    }
}
