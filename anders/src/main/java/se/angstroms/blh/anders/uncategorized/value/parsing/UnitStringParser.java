package se.angstroms.blh.anders.uncategorized.value.parsing;

import org.blh.core.unit.Unit;

/**
 *
 * @author eriklark
 */
public interface UnitStringParser<T extends Unit<?>> {

    public T parse(String raw) throws ParseException;
}
