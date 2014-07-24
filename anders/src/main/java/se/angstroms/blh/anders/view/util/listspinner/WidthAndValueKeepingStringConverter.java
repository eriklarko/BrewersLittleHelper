package se.angstroms.blh.anders.view.util.listspinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import jfxtras.scene.control.ListSpinner;

/**
 *
 * @author eriklark
 */
public class WidthAndValueKeepingStringConverter extends StringConverter<Double> {

    private final ListSpinner<Double> spinner;
    private final NumberFormat nf;
    private double previousWidth = 0;
    private Double lastGoodNumber = null;

    public WidthAndValueKeepingStringConverter(ListSpinner<Double> spinner) {
        this(spinner, new DecimalFormat("#.##"));
    }

    public WidthAndValueKeepingStringConverter(ListSpinner<Double> spinner, NumberFormat nf) {
        this.spinner = spinner;
        this.nf = nf;
    }

    @Override
    public String toString(Double d) {
        String s = nf.format(d);
        keepWidthSweet(s);
        return s;
    }

    private void keepWidthSweet(String s) {
        Text t = new Text(s);
        double newWidth = t.getLayoutBounds().getWidth() + 75;
        newWidth = Math.max(newWidth, previousWidth);
        spinner.setMaxWidth(newWidth);
        previousWidth = newWidth;
    }

    @Override
    public Double fromString(String string) {
        try {
            Double toReturn = Double.valueOf(string);
            lastGoodNumber = toReturn;
            return toReturn;
        } catch (NumberFormatException ex) {
            return lastGoodNumber;
        }
    }
}
