package se.angstroms.blh.anders.view.recipe.details.data.value;

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
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 *
 * @author Thinner
 */
public class InputtedValuePresenter extends HBox {

    @FXML
    private TextField input;

    public InputtedValuePresenter() {
	    CustomControl.setup(this);

        input.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean hasFocus) {
				if (hasFocus) {
					System.out.println("Blurred the liiiiiines!");
				} else {
					fireCommitEvent();
				}
			}
		});

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

	public String getValue() {
		return input.getText();
	}

	public void setValue(String text) {
		input.setText(text);
	}

	public StringProperty valueProperty() {
		return input.textProperty();
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
