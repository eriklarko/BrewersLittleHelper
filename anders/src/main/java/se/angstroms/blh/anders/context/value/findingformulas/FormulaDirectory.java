package se.angstroms.blh.anders.context.value.findingformulas;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.formulas.PartialObservableFormula;

/**
 * Used to find available formulas for a given value.
 * Since all formulas depend on the recipe, or context, this class returns
 * partial formulas which must be bound before they can be used.
 *
 * @author eriklark
 */
public class FormulaDirectory {

	private final Multimap<Value.Id, PartialObservableFormula> map;

	public FormulaDirectory() {
		map = HashMultimap.create();
	}

	public void register(Value.Id type, Class<? extends ObservableFormula> formula) {
		map.put(type, new PartialObservableFormula(formula));
	}

    public Collection<PartialObservableFormula> getFormulasFor(Value.Id type) {
        return map.get(type);
    }

	public <T extends Unit<?>> PartialObservableFormula<T> getDefaultFormula(Value.Id type) throws NoDefaultFormulaException {
        Collection<PartialObservableFormula> formulas = getFormulasFor(type);
		if (formulas.isEmpty()) {
			throw new NoDefaultFormulaException("No default formula for " + type.name() + " found.");
		}
		return (PartialObservableFormula<T>) formulas.iterator().next();
	}
}
