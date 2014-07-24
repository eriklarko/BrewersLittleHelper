package org.blh.recipe.uncategorized;

import javafx.beans.property.SimpleObjectProperty;
import org.blh.core.recipe.GristPart;

/**
 *
 * @author eriklark
 */
public class ObservableGristPart extends SimpleObjectProperty<GristPart>{

    public ObservableGristPart() {
    }

    public ObservableGristPart(GristPart initialValue) {
        super(initialValue);
    }
}
