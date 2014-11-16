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
                new Malt("Breiss Sparkling Amber DME", new Lovibond(new SRM(9)), new ExtractPotential(new GravityPoints(44 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.EXTRACT),
                new Malt("Lol", new Lovibond(new SRM(9)), new ExtractPotential(new GravityPoints(0 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.EXTRACT),
                new Malt("Münich", new Lovibond(new SRM(9)), new ExtractPotential(new GravityPoints(37 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),
                new Malt("Vienna DK", new Lovibond(4), new ExtractPotential(new GravityPoints(37 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),
                new Malt("Pilsner DK", new Lovibond(1), new ExtractPotential(new GravityPoints(38 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),
                new Malt("Maris Otter", new Lovibond(new SRM(3)), new ExtractPotential(new GravityPoints(38 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),

                new Malt("Pale Ale (US)", new Lovibond(3), new ExtractPotential(new GravityPoints(37 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN),
                new Malt("Carapils (DE)", new Lovibond(1), new ExtractPotential(new GravityPoints(35 / Lbs.CONVERSION_FACTOR)), Malt.TYPE.GRAIN)
        );
    }
}
