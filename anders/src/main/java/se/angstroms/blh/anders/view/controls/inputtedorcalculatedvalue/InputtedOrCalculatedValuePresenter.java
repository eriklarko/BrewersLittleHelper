package se.angstroms.blh.anders.view.controls;

import java.util.Arrays;
import java.util.Collection;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javax.inject.Inject;
import org.blh.FullContext;
import org.blh.core.unit.Unit;
import org.blh.formulas.Formula;
import org.blh.formulas.decorated.bitterness.DecoratedTinseth;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * FXML Controller class
 *
 * @author nichlassa
 */
public class ValueComponentPresenter extends VBox implements ChangeListener<Class<? extends Unit<?>>> {

    @FXML
    private Label title;
    @FXML
    private Label value;

    @Inject
    private FullContext fullContext;

    private final ObjectProperty<Class<? extends Unit<?>>> unitProperty;
    private Formula<? extends Unit<?>> selectedFormula;
    private Collection<Formula<? extends Unit<?>>> availableFormulas;

    public ValueComponentPresenter() {
        CustomControl.setup(this);

        unitProperty = new SimpleObjectProperty<>();
        unitProperty.addListener(this);
    }

    public String getTitle() {
        return this.title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public StringProperty titleProperty() {
        return this.title.textProperty();
    }

    public Class<? extends Unit<?>> getUnit() {
        return unitProperty.get();
    }

    public void setUnit(Class<? extends Unit<?>> clazz) {
        unitProperty.set(clazz);
    }

    public ObjectProperty<Class<? extends Unit<?>>> unitProperty() {
        return unitProperty;
    }

    public void recalculateValue() {
        Unit<?> calculatedValue = selectedFormula.calc(fullContext);
        this.value.setText(calculatedValue.toString());
    }

    @Override
    public void changed(ObservableValue<? extends Class<? extends Unit<?>>> ov,
            Class<? extends Unit<?>> oldValue,
            Class<? extends Unit<?>> newValue) {

        // TODO: Find real formulas for the unit
        availableFormulas = Arrays.asList(new DecoratedTinseth());
        selectedFormula = availableFormulas.iterator().next();
    }
}
