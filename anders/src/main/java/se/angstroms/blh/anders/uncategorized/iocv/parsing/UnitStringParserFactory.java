package se.angstroms.blh.anders.uncategorized.iocv.parsing;

import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;
import org.blh.core.unit.bitterness.IBU;
import org.blh.core.unit.gravity.SpecificGravity;
import se.angstroms.blh.anders.uncategorized.iocv.ValueId;
import se.angstroms.blh.anders.uncategorized.iocv.ValueId;

/**
 *
 * @author eriklark
 */
public class UnitStringParserFactory {

    public <T extends Unit<?>> UnitStringParser<T> getParserFor(ValueId type) {
        UnitStringParser<Unit<?>> asd = doGetParserFor(type);
        return (String raw) -> {
            try {
                return (T) asd.parse(raw);
            } catch (Exception ex) {
                throw new ParseException("Unable to parse the given string into " + type.name(), ex);
            }
        };
    }

    private <T extends Unit<?>> UnitStringParser<T> doGetParserFor(ValueId type) {
        switch (type) {
			case OG:
				return (String raw) -> {
                    Double d = Double.parseDouble(raw);
                    return (T) new SpecificGravity(d);
                };
            case EXTRACTION_EFFICIENCY:
                return (String raw) -> {
                    Double d = Double.parseDouble(raw);
                    return (T) new Factor(d);
                };
            case BITTERNESS:
                return (String raw) -> {
                    Double d = Double.parseDouble(raw);
                    return (T) new IBU(d);
                };
			default:
				throw new IllegalArgumentException("I don't know how to turn strings into " + type.name() + " yet");
		}
    }
}
