package se.angstroms.blh.anders.view.formulaexplorer;


import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.Value;
import se.angstroms.blh.anders.formulas.ObservableFormula;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
 *		 The y-axis should be the formula value, the x-axis the free variable
 *
 * @author eriklark
 */
public class FormulaExplorer extends HBox {

    private final ObservableFormula<?> formulaToExplore;

    public FormulaExplorer(ObservableFormula<?> formulaToExplore) {
        this.formulaToExplore = formulaToExplore;

        buildDependenciesInputs();
        setupGraph();
    }

    private Node buildDependenciesInputs() {
		VBox root = new VBox();
        for (Value<?> dependency : formulaToExplore.getDependencies()) {
			root.getChildren().add(buildDependencyInput(dependency));
		}

		return root;
    }

	private Node buildDependencyInput(Value<?> dependency) {
		dependency.getValueType(); // Original gravity, IBU, Liters, malt type..
		return new Label("Hej");
	}

    private void setupGraph() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void populateDependenciesUsing(FullContext context) {

    }
}
