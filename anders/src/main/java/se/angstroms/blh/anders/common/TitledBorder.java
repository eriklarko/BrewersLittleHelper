package se.angstroms.blh.anders.common;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Places content in a bordered pane with a title.
 *
 * Stolen from Jewelsea
 * http://stackoverflow.com/questions/14860960/groupbox-titledborder-in-javafx-2
 */
public class TitledBorder extends StackPane {

	public TitledBorder(String titleString, Node content) {
		Label title = new Label(" " + titleString + " ");
		title.getStyleClass().add("titled-border-title");
		StackPane.setAlignment(title, Pos.TOP_CENTER);

		content.getStyleClass().add("titled-border-content");

		getStyleClass().add("titled-border-border");
		getChildren().addAll(title, content);
	}
}
