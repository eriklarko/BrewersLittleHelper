package se.angstroms.blh.anders.uncategorized.value;

import org.blh.core.unit.Unit;

/**
 *
 * @author eriklark
 */
public interface Value<T extends Unit<?>> {

    T get();
}
