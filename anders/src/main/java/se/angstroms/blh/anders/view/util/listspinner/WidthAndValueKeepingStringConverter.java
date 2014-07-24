package se.angstroms.blh.anders.view.util.listspinner;

import javafx.scene.text.Text;
import javafx.util.StringConverter;
import jfxtras.scene.control.ListSpinner;

/**
 *
 * @author eriklark
 */
public abstract class WidthAndValueKeepingStringConverter<T> extends StringConverter<T> {

    private final ListSpinner<T> spinner;
    private double previousWidth = 0;
    private T lastGoodValue = null;

    public WidthAndValueKeepingStringConverter(ListSpinner<T> spinner) {
        this.spinner = spinner;
    }

    @Override
    public final String toString(T t) {
        String s = asString(t);
        keepWidthSweet(s);
        return s;
    }

    protected abstract String asString(T t);

    private void keepWidthSweet(String s) {
        Text t = new Text(s);
        double newWidth = t.getLayoutBounds().getWidth() + 75;
        newWidth = Math.max(newWidth, previousWidth);
        spinner.setMaxWidth(newWidth);
        previousWidth = newWidth;
    }

    @Override
    public final T fromString(String string) {
        try {
            T toReturn = parse(string);
            lastGoodValue = toReturn;
            return toReturn;
        } catch (Exception ex) {
            return lastGoodValue;
        }
    }

    protected abstract T parse(String s);
}
