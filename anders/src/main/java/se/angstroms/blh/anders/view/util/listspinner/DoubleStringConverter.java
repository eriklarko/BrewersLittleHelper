package se.angstroms.blh.anders.view.util.listspinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jfxtras.scene.control.ListSpinner;

/**
 *
 * @author eriklark
 */
public class DoubleStringConverter extends WidthAndValueKeepingStringConverter<Double> {

    private final NumberFormat nf;

    public DoubleStringConverter(ListSpinner<Double> spinner) {
        this(spinner, new DecimalFormat("0.00"));
    }

    public DoubleStringConverter(ListSpinner<Double> spinner, NumberFormat nf) {
        super(spinner);
        this.nf = nf;
    }

    @Override
    protected String asString(Double d) {
        return nf.format(d);
    }

    @Override
    protected Double parse(String s) {
        return Double.parseDouble(s);
    }

}
