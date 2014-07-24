package se.angstroms.blh.anders.view.common;

import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import se.angstroms.blh.anders.view.util.FilterableComboBox;

/**
 *
 * @author eriklark
 */
public class SelectBoxLabel<T> extends Group {

    private final ObjectProperty<T> model;
    private final Label label;
    private final FilterableComboBox<T> editor;
    private final Function<T, String> toString;

    public SelectBoxLabel(T model, ObservableList<T> availableValues, Function<T, String> toString) {
        this.model = new SimpleObjectProperty<>(model);
        this.toString = toString;

        label = new Label();
        editor = new FilterableComboBox<>(availableValues, FilterableComboBox.STRING_CONTAINS(toString));
        editor.setItemToString(toString);
        editor.getEditor().setText(toString.apply(model));

        setupLabelTextBinding();
        setupOnLabelClicked();
        setupEditorOnFocus();
        setupEditorKeyListener();
        editor.getSelectionModel().selectedItemProperty().addListener((a,b,c) -> commitSelectedValue());

        showLabel();
        this.getChildren().addAll(label, editor);
    }

    private void setupLabelTextBinding() {
        StringBinding modelNameBinding = Bindings.createStringBinding(() -> toString.apply(model.get()), model);
        label.textProperty().bind(modelNameBinding);
    }

    private void setupOnLabelClicked() {
        label.setOnMouseClicked((event) -> showEditor());
    }

    private void setupEditorOnFocus() {
        editor.focusedProperty().addListener((_1, _2, newValue) -> {
            if (!newValue) {
                showLabel();
            }
        });
    }

    private void setupEditorKeyListener() {
        editor.setOnKeyReleased((event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                showLabel();
            } else if (event.getCode() == KeyCode.ENTER) {
                commitSelectedValue();
            }
        });
    }

    private void commitSelectedValue() {
        if (editor.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Tried to set select box to something that doesn't exist. Open create dialog?");
        } else {
            System.out.println("Submitting " + editor.getSelectionModel().getSelectedItem());
            model.set(editor.getSelectionModel().getSelectedItem());
        }
        showLabel();
    }

    private void showLabel() {
        label.setVisible(true);
        editor.setVisible(false);
    }

    private void showEditor() {
        label.setVisible(false);
        editor.setVisible(true);
        editor.show();
    }

    public ObjectProperty<T> modelProperty() {
        return model;
    }
}
