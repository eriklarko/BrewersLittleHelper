package se.angstroms.blh.anders.examples;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class TestCustomControlController extends VBox {

	@FXML
	private TextField textField;

	public TestCustomControlController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"TestCustomControl.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String getText() {
		return textProperty().get();
	}

	public void setText(String value) {
		textProperty().set(value);
	}

	public StringProperty textProperty() {
		return textField.textProperty();
	}

	@FXML
	protected void doSomething() {
		System.out.println("The button was clicked!");
	}
}
