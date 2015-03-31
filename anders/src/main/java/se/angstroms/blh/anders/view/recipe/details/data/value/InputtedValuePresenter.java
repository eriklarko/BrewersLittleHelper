package se.angstroms.blh.anders.view.recipe.details.data.value;



import org.blh.core.unit.Unit;

import se.angstroms.blh.anders.context.value.InputtedValue;
import se.angstroms.blh.anders.context.value.parsing.ParseException;
import se.angstroms.blh.anders.view.util.CustomControl;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

/**
 *
 * @author Thinner
 */
public class InputtedValuePresenter<T extends Unit<?>> extends HBox {

    @FXML
    private TextField input;

	private final ObjectProperty<InputtedValue<T>> valueProperty = new SimpleObjectProperty<>();

    public InputtedValuePresenter() {
	    CustomControl.setup(this);

		bindRawInputAndValue();
        fireCommitsOnBlur();
		fireCommitsOnKeyRelease();
    }

	private void bindRawInputAndValue() {
		input.textProperty().bindBidirectional(valueProperty, new StringConverter<InputtedValue<T>>() {

			@Override
			public String toString(InputtedValue<T> object) {
				if (valueProperty.get() == null) {
					return null;
				}

				return valueProperty.get().asString();
			}

			@Override
			public InputtedValue<T> fromString(String string) {
				if (valueProperty.get() == null) {
					return null;
				}

				String lastLegal = "";
				try {
					lastLegal = string;
					valueProperty.get().fromString(string);
					return valueProperty.get();
				} catch (ParseException ex) {
					input.setText(lastLegal);
					return null;
				}
			}
		});
	}

	private void fireCommitsOnBlur() {
		input.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean hasFocus) {
				if (!hasFocus) {
					fireCommitEvent();
				}
			}
		});
	}

	private void fireCommitsOnKeyRelease() {
		input.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent t) {
				fireCommitEvent();
			}
		});
	}

	private void fireCommitEvent() {
		fireEvent(new CommitEvent(input.getText()));
	}

	public String getRawValue() {
		return input.getText();
	}

	public void setRawValue(String text) {
		input.setText(text);
	}

	public StringProperty rawValueProperty() {
		return input.textProperty();
	}

	public ObjectProperty<InputtedValue<T>> valueProperty() {
		return valueProperty;
	}

	public void setValue(InputtedValue<T> dependency) {
		valueProperty.set(dependency);
	}

	public void addOnTextChangedListener(EventHandler<CommitEvent> handler) {
		this.addEventHandler(CommitEvent.COMMIT, handler);
	}

	public static final class CommitEvent extends Event {

		public static final EventType<CommitEvent> COMMIT = new EventType<CommitEvent>("LOL");

		private final String text;

		public CommitEvent(String text) {
			super(COMMIT);
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
}
