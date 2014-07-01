package se.angstroms.blh.anders.context.value.parsing;

import org.blh.core.unit.Unit;

/**
 *
 * @author eriklark
 */
public interface UnitStringParser<T extends Unit<?>> {

    public T parse(String raw) throws ParseException;
}
