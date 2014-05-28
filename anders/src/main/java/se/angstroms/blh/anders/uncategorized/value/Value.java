package se.angstroms.blh.anders.uncategorized.value;

import org.blh.core.unit.Unit;

/**
 * Defines all things about a beer recipe that has a value.
 *
 * @author eriklark
 */
public interface Value<T extends Unit<?>> {

    T get();
}
