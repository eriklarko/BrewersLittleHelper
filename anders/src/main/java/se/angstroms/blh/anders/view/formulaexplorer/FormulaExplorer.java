package se.angstroms.blh.anders.view.formulaexplorer;

import java.util.Collection;
import javafx.beans.Observable;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.formulas.ObservableFormula;

/**
 *
 * TODO: List the formula's dependencies
 *
 * TODO: Allow all dependencies to be set to some values
 *
 * TODO: Allow one dependency to be "free". This dependency's value will be
 * changed iteratively.
 *
 * TODO: Graph the values of formula
 *
 * @author eriklark
 */
public class FormulaExplorer {

    private final ObservableFormula<?> formulaToExplore;

    public FormulaExplorer(ObservableFormula<?> formulaToExplore) {
        this.formulaToExplore = formulaToExplore;

        setupFreeDepencySelectBox();
        setupBoundDependenciesInputs();
        setupGraph();
    }

    private void setupFreeDepencySelectBox() {
        Collection<Observable> dependencies = formulaToExplore.getDependenies();
        for (Observable dependency : dependencies) {
            // Ska ObservableFormula ta in Value.Id som dependencies istället för Observables? Kanske?
        }
    }

    private void setupBoundDependenciesInputs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setupGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void populateDependenciesUsing(FullContext context) {

    }
}
