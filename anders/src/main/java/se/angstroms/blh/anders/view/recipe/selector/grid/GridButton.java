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
import javax.inject.Inject;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.uncategorized.value.UnitStringFormatter;

/**
 *
 * @author eriklark
 */
public class GridButton extends FlowPane {

	private final FullContext recipe;

    @Inject
    private UnitStringFormatter unitStringFormatter;

	public GridButton(FullContext recipe) {
		this.recipe = recipe;
	}

    public void render() {
        addImage();
        addRecieName();
        addRandomFacts();

        this.setPrefWidth(200);
    }

	private void addImage() {
		Canvas image = new Canvas(100, 150);
		image.getGraphicsContext2D().setFill(Color.CYAN);
		image.getGraphicsContext2D().fillRect(0, 0, image.getWidth(), image.getHeight());

		this.getChildren().add(image);
	}

	private void addRecieName() {
		Label name = new Label(recipe.nameProperty().get());
		name.setFont(Font.font(name.getFont().getFamily(), FontWeight.BOLD, name.getFont().getSize()));
		this.getChildren().add(name);
	}

	private void addRandomFacts() {
		//this.getChildren().add(new Label("· OG: " + unitStringFormatter.format(recipe.getOriginalGravity())));
		//this.getChildren().add(new Label("· ABV: " + unitStringFormatter.format(recipe.getAlcoholContent())));
		//this.getChildren().add(new Label("· IBU: " + unitStringFormatter.format(recipe.getBitterness())));
		this.getChildren().add(new Label("· Last brewed: Yesterday"));
	}

	public FullContext getRecipe() {
		return recipe;
	}
}
