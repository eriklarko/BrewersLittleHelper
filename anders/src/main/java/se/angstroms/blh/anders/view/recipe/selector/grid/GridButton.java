/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.angstroms.blh.anders.view.recipe.selector.grid;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.blh.recipe.attempts.composite.Recipe;

/**
 *
 * @author eriklark
 */
public class GridButton extends FlowPane {

	private final Recipe recipe;

	public GridButton(Recipe recipe) {
		this.recipe = recipe;

		addImage();
		addRecieName();
		addRandomFacts();

		this.setPrefWidth(40);
	}

	private void addImage() {
		Canvas image = new Canvas(20, 20);
		image.getGraphicsContext2D().setFill(Color.CYAN);
		image.getGraphicsContext2D().fillRect(0, 0, 20, 20);

		this.getChildren().add(image);
	}

	private void addRecieName() {
		Label name = new Label(recipe.getName());
		name.setFont(Font.font(name.getFont().getFamily(), FontWeight.BOLD, name.getFont().getSize()));
		this.getChildren().add(name);
	}

	private void addRandomFacts() {
		this.getChildren().add(new Label("· OG: 1.050"));
		this.getChildren().add(new Label("· ABV: 5%"));
		this.getChildren().add(new Label("· IBU: 60"));
		this.getChildren().add(new Label("· Last brewed: Yesterday"));
	}

	public Recipe getRecipe() {
		return recipe;
	}
}
