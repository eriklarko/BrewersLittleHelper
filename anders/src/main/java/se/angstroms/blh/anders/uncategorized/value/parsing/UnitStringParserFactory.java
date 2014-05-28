package se.angstroms.blh.anders.uncategorized.value.parsing;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 * Maps Units and ValudIds with methods that take Strings and turns them into
 * said Units.
 *
 * @author eriklark
 */
public class UnitStringParserFactory {

    public <T extends Unit<?>> UnitStringParser<T> getParserFor(ValueId type) {
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

        throw new IllegalArgumentException("I don't know how to turn strings into " + unitClass.getCanonicalName() + " yet");
    }

    private <T extends Unit<?>> UnitStringParser<T> doGetParserFor(ValueId type) {
        switch (type) {
			case OG:
				return getParserFor(SpecificGravity.class);
            case EXTRACTION_EFFICIENCY:
                return getParserFor(Factor.class);
            case BITTERNESS:
                return getParserFor(IBU.class);
			default:
				throw new IllegalArgumentException("I don't know how to turn strings into " + type.name() + " yet");
		}
    }
}
