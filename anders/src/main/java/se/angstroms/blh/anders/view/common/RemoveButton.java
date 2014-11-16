package se.angstroms.blh.anders.view.common;

import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import jfxtras.scene.control.ImageViewButton;

/**
 *
 * @author eriklark
 */
public class RemoveButton<T> extends HBox {

    private final ImageViewButton btn = new ImageViewButton();

    public RemoveButton() {
        this.setAlignment(Pos.BASELINE_CENTER);
    }

    public RemoveButton(GridListView.GridRow<T> row, Consumer<T> onDelete) {
        String url = getClass().getResource("/images/remove.png").toExternalForm();
        Image img = new Image(url, 20, 20, true, true, true);
        btn.setImage(img);

        btn.setOnMouseClicked((e) -> {
            System.out.println("Sending " + row.getModel().getValue() + " to removeListener");
            onDelete.accept(row.getModel().getValue());
        });

        this.getChildren().add(btn);
    }
}
