/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.blh.formuladecorator;

import java.util.HashMap;
import java.util.Map;
import org.blh.core.unit.Unit;
import org.blh.formuladecorator.formulas.ObservableFormula;

/**
 *
 * @author eriklark
 */
public class FormulaFactory {

	private static FormulaFactory instance;

	public static final FormulaFactory getInstance() {
		if (instance == null) {
			instance = new FormulaFactory();
		}
		return instance;
	}

	private Map<Class<? extends Unit<?>>, ObservableFormula<?>> map;

	public FormulaFactory() {
		map = new HashMap<>();
	}

	public void register(Class<? extends Unit<?>> clazz, ObservableFormula<?> formula) {
		map.put(clazz, formula);
	}

	public <T extends Unit<?>> ObservableFormula<T> lol(Class<T> clazz) {
		return (ObservableFormula<T>) map.get(clazz);
	}
}
