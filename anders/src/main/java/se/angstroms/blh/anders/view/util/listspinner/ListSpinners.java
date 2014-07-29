package se.angstroms.blh.anders.view.util.listspinner;

import jfxtras.scene.control.ListSpinner;

/**
 *
 * @author eriklark
 */
public class ListSpinners {

    public static ListSpinner<Double> generic(double initialValue) {
        return generic(0, 100000, 0.1, initialValue);
    }

    public static ListSpinner<Double> generic(double min, double max, double step, double initialValue) {
        ListSpinner<Double> spinner = new ListSpinner<>(new ListSpinnerDoubleList(min, max, step));
        spinner.setValue(initialValue);
        spinner.setEditable(true);
        spinner.setStringConverter(new DoubleStringConverter(spinner));

        return spinner;
    }

    private ListSpinners() {

    }
}
