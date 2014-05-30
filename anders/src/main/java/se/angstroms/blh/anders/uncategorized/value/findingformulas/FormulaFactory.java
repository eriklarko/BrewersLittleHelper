package se.angstroms.blh.anders.uncategorized.value.findingformulas;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.formulas.observable.ObservableFormulaBuilder;
import se.angstroms.blh.anders.uncategorized.value.ValueId;

/**
 *
 * @author eriklark
 */
public class FormulaFactory {

	private final Multimap<ValueId, ObservableFormulaBuilder> map;

	public FormulaFactory() {
		map = HashMultimap.create();
	}

	public void register(ValueId type, Class<? extends ObservableFormula> formula) {
		map.put(type, new ObservableFormulaBuilder(formula));
	}

    public Collection<ObservableFormulaBuilder> getFormulasFor(ValueId type) {
        return map.get(type);
    }

	public <T extends Unit<?>> ObservableFormulaBuilder<T> getDefaultFormula(ValueId type) throws NoDefaultFormulaException {
        Collection<ObservableFormulaBuilder> formulas = getFormulasFor(type);
		if (formulas.isEmpty()) {
			throw new NoDefaultFormulaException("No default formula for " + type.name() + " found.");
		}
		return (ObservableFormulaBuilder<T>) formulas.iterator().next();
	}
}
