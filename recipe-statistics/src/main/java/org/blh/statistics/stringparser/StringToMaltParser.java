package org.blh.statistics.stringparser;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.util.Pair;
import org.blh.core.ingredient.Malt;
import org.blh.core.unit.ExtractPotential;
import org.blh.core.unit.color.Lovibond;
import org.blh.core.unit.gravity.GravityPoints;
import org.simmetrics.metrics.JaroWinkler;

/**
 *
 * @author eriklark
 */
public class StringToMaltParser {

    private final JaroWinkler algo = new JaroWinkler();
    private final float threshold = 0.8f;
    private final IngredientStore<Malt> lol = new IngredientStore<Malt>() {

        @Override
        public Collection<Malt> getAll() {
            return Arrays.asList(
                new Malt("Light Dry Extract", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.EXTRACT),
                new Malt("Dark Dry Extract", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN),
                new Malt("Crystal Malt - 80L", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN),
                new Malt("Pale Malt (2 Row) UK", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN),
                new Malt("Chocolate Malt", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN),
                new Malt("White Wheat Malt", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN),
                new Malt("Roasted Barley", new Lovibond(1), new ExtractPotential(new GravityPoints(1)), Malt.TYPE.GRAIN)
            );
        }
    };

    public Collection<Malt> findPossibleMatches(String maltName) {
        /*return lol.getAll().stream().max((Malt thiz, Malt that) ->
                Float.compare(algo.getSimilarity(thiz.getName(), maltName), algo.getSimilarity(that.getName(), maltName)));*/

        return lol.getAll().stream()
                .map(malt -> new Pair<>(malt, algo.compare(malt.getName(), maltName)))
                .filter(pair -> pair.getValue() > threshold)
                .sorted((thiz, that) -> thiz.getValue().compareTo(that.getValue()))
                .map(pair -> pair.getKey())
                .collect(Collectors.toList());
    }

    public static interface IngredientStore<T> {

        Collection<T> getAll();
    }
}
