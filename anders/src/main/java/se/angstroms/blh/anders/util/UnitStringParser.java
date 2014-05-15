package se.angstroms.blh.anders.util;

import org.blh.core.unit.Unit;

/**
 *
 * @author eriklark
 */
public interface UnitStringParser<T extends Unit<?>> {

    public T parse(String raw) throws ParseException;
}
