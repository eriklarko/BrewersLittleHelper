package se.angstroms.blh.anders.context.value.parsing;

import org.blh.core.unit.Unit;

public interface UnitStringParser<T extends Unit<?>> {

    public T parse(String raw) throws ParseException;
}
