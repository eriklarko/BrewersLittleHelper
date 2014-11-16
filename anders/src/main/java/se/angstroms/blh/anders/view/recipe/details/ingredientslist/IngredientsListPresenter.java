package se.angstroms.blh.anders.view.recipe.details.ingredientslist;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.inject.Inject;

import javafx.scene.layout.RowConstraints;
import org.blh.core.ingredient.Hop;
import org.blh.core.ingredient.Malt;
import org.blh.core.ingredient.Yeast;
import org.blh.core.recipe.GristPart;
import org.blh.core.recipe.HopAddition;
import org.blh.core.recipe.YeastAddition;
import org.blh.core.unit.ExtractPotential;
import org.blh.core.unit.Percentage;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.time.Minutes;
import org.blh.core.unit.volume.Milliliters;
import org.blh.core.unit.weight.Grams;
import org.blh.core.unit.weight.Kilograms;
import org.blh.recipe.uncategorized.IngredientsList;
import se.angstroms.blh.anders.data.HopStore;
import se.angstroms.blh.anders.data.MaltStore;
import se.angstroms.blh.anders.data.YeastStore;
import se.angstroms.blh.anders.view.common.GridListView;
import se.angstroms.blh.anders.view.util.CustomControl;

/**
 * Lists the specified ingredients
 *
 * @author Erik Larkö <erik.larko@purplescout.se>
 */
public class IngredientsListPresenter extends GridPane {

    @Inject private MaltStore maltStore;
    @Inject private HopStore hopStore;
    @Inject private YeastStore yeastStore;

    @FXML private GridListView<GristPart> fermentablesTable;
    @FXML private GridListView<HopAddition> hopsTable;
    @FXML private GridListView<YeastAddition<?>> yeastsTable;

	private final ObjectProperty<IngredientsList> ingredientsListProperty;

    public IngredientsListPresenter() {
        CustomControl.setup(this);

		ingredientsListProperty = new SimpleObjectProperty<>();
		ingredientsListProperty.addListener(new ChangeListener<IngredientsList>() {

			@Override
			public void changed(ObservableValue<? extends IngredientsList> ov, IngredientsList t, IngredientsList newValue) {
				doSetIngredientsList(newValue);
			}
		});

        this.getColumnConstraints().addAll(
                columnConstraintsWithPercentageWidth(34),
                columnConstraintsWithPercentageWidth(33),
                columnConstraintsWithPercentageWidth(33)
        );

        RowConstraints fillRow = new RowConstraints();
        fillRow.setPercentHeight(100);
        fillRow.setValignment(VPos.CENTER);
        this.getRowConstraints().addAll(fillRow);
    }

    private ColumnConstraints columnConstraintsWithPercentageWidth(double percentage) {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(percentage);
        return columnConstraints;
    }

    public void setIngredientsList(IngredientsList ingredientsList) {
        ingredientsListProperty.set(ingredientsList);
    }

    private void doSetIngredientsList(IngredientsList ingredientsList) {
        fermentablesTable.setData(ingredientsList.getFermentables(), (model) -> new MaltListItem(model, maltStore, (gp) -> {
            ingredientsList.getFermentables().remove(gp);
        }));
        hopsTable.setData(ingredientsList.getHopAdditions(), (model) -> new HopListItem(model, hopStore, (ha) -> ingredientsList.getHopAdditions().remove(ha)));
        yeastsTable.setData(ingredientsList.getYeastAdditions(), (model) -> new YeastListItem(model, yeastStore, (ya) -> ingredientsList.getYeastAdditions().remove(ya)));
    }

    @FXML
    public void addMalt(ActionEvent e) {
        ingredientsListProperty.get().getFermentables().add(new GristPart(new Malt("TROLL", new Lovibond(1), new ExtractPotential(new GravityPoints(66)), Malt.TYPE.GRAIN), new Kilograms(2)));
    }

    @FXML
    public void addHop(ActionEvent e) {
        ingredientsListProperty.get().getHopAdditions().add(new HopAddition(new Hop("Hej", new Percentage(10)), new Minutes(60), new Grams(10)));
    }

    @FXML
    public void addYeast(ActionEvent e) {
        ingredientsListProperty.get().getYeastAdditions().add(new YeastAddition<>(new Yeast("asdf", "asdffff", new Percentage(80)), new Milliliters(10)));
    }
}
