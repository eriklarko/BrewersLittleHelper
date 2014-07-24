package se.angstroms.blh.anders.view.util.listspinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jfxtras.scene.control.ListSpinner;

public class IntStringConverter extends WidthAndValueKeepingStringConverter<Integer> {

    private final NumberFormat nf;

    public IntStringConverter(ListSpinner<Integer> spinner) {
         this(spinner, new DecimalFormat());
    }

    public IntStringConverter(ListSpinner<Integer> spinner, NumberFormat nf) {
        super(spinner);
        this.nf = nf;
    }

    @Override
    protected String asString(Integer i) {
        return nf.format(i);
    }

    @Override
    protected Integer parse(String s) {
        return Integer.parseInt(s);
    }
}
