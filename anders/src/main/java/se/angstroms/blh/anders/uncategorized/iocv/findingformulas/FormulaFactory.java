package se.angstroms.blh.anders.uncategorized.iocv.findingformulas;

import java.util.HashMap;
import java.util.Map;
import org.blh.core.unit.Unit;
import se.angstroms.blh.anders.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.iocv.ValueId;

/**
 *
 * @author eriklark
 */
public class FormulaFactory {

	private final Map<ValueId, ObservableFormula<?>> map;

	public FormulaFactory() {
		map = new HashMap<>();
	}

	public void register(ValueId type, ObservableFormula<?> formula) {
		map.put(type, formula);
	}

	public <T extends Unit<?>> ObservableFormula<T> getDefaultFormula(ValueId type) throws NoDefaultFormulaException {
		ObservableFormula<T> defaultFormula = (ObservableFormula<T>) map.get(type);
		if (defaultFormula == null) {
			throw new NoDefaultFormulaException("No default formula for " + type.name() + " found.");
		}
		return defaultFormula;
	}
}
