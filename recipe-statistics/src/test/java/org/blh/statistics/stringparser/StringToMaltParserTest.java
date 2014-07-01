package org.blh.statistics.stringparser;

import java.util.Collection;
import junit.framework.Assert;
import org.blh.core.ingredient.Malt;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eriklark
 */
public class StringToMaltParserTest {

    @Test
    public void testFindPossibleMatches() {
        Collection<Malt> possibleMatches = new StringToMaltParser().findPossibleMatches("Dry Extract");
        System.out.println("asd");
    }

}
