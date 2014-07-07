package se.angstroms.blh.anders.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.blh.core.ingredient.Malt;
import org.blh.core.unit.ExtractPotential;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.color.SRM;
import org.blh.core.unit.gravity.GravityPoints;
import org.blh.core.unit.weight.Lbs;

/**
 *
 * @author eriklark
 */
public class MaltStore implements Store<Malt>{

    @Override
    public ObservableList<Malt> getAll() {
        return FXCollections.observableArrayList(
                new Malt("Münich", new Lovibond(new SRM(9)), new ExtractPotential(new GravityPoints(37 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),
                new Malt("Maris Otter", new Lovibond(new SRM(3)), new ExtractPotential(new GravityPoints(38 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN)
        );
    }
}
