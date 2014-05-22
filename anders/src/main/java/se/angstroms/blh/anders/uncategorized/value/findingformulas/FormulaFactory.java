package se.angstroms.blh.anders.uncategorized.value.findingformulas;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 *
 * @author eriklark
 */
public class FormulaFactory {

	private final Multimap<ValueId, ObservableFormula<?>> map;

	public FormulaFactory() {
		map = HashMultimap.create();
	}

	public void register(ValueId type, ObservableFormula<?> formula) {
		map.put(type, formula);
	}

    public Collection<ObservableFormula<?>> getFormulasFor(ValueId type) {
        return map.get(type);
    }

	public <T extends Unit<?>> ObservableFormula<T> getDefaultFormula(ValueId type) throws NoDefaultFormulaException {
        Collection<ObservableFormula<?>> formulas = getFormulasFor(type);
		if (formulas.isEmpty()) {
			throw new NoDefaultFormulaException("No default formula for " + type.name() + " found.");
		}
		return (ObservableFormula<T>) formulas.iterator().next();
	}
}
