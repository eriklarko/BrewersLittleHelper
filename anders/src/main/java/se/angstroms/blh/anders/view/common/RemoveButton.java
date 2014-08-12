package se.angstroms.blh.anders.view.common;

import java.util.function.Consumer;
import javafx.beans.property.Property;
import javafx.scene.image.Image;
import jfxtras.scene.control.ImageViewButton;

/**
 *
 * @author eriklark
 */
public class RemoveButton<T extends Property<?>> extends ImageViewButton {

    public RemoveButton(GridListView.GridRow<T> row, Consumer<T> onDelete) {
        String url = getClass().getResource("/images/remove.png").toExternalForm();
        Image img = new Image(url, 20, 20, true, true, true);
        this.setImage(img);

        this.setOnMouseClicked((e) -> {
            onDelete.accept(row.getModel());
        });
    }
}
