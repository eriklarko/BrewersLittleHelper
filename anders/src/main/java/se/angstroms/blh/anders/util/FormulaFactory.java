/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.angstroms.blh.anders.util;

import java.util.HashMap;
import java.util.Map;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.formulas.ObservableFormula;
import se.angstroms.blh.anders.uncategorized.ValueId;

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
