package se.angstroms.blh.anders.context.serializing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import org.blh.core.unit.Factor;
import org.blh.core.unit.Unit;
import org.blh.core.unit.gravity.SpecificGravity;
import org.junit.Assert;
import org.junit.Test;
import se.angstroms.blh.anders.formulas.NopFormula;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue.STATE;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class FullContextDeserializerTest {

    private final String pathToValidFile = "validFullContext2.json";

    @Test
    public void testReadFromValidFile() throws IOException, URISyntaxException, FileNotFoundException {
        FullContextSerializer fullContextSerializer = new FullContextSerializer();
        fullContextSerializer.unitStringParserFactory = new UnitStringParserFactory();
        FullContext actual = fullContextSerializer.parseFromFile(new File(this.getClass().getResource(pathToValidFile).toURI()));

        assertValue("Original gravity",      STATE.CALCULATED, null,           NopFormula.class, actual.getOriginalGravity());
        assertValue("Final gravity",         STATE.CALCULATED, null,           NopFormula.class, actual.getFinalGravity());
        assertValue("Extraction efficiency", STATE.INPUTTED,   new Factor(70), null,             actual.getExtractionEfficiency());
        Assert.assertEquals("Cooling loss value differs", new Factor(40), actual.getCoolingLoss().get());
    }

    private void assertValue(String name, STATE state, Unit<?> value, Class<?> formula, InputtedOrCalculatedValue<? extends Unit<?>> iocv) {
        Assert.assertEquals(name + " state differs", state, iocv.stateProperty().get());
        Assert.assertEquals(name + " value differs", value, iocv.get());

        if (iocv.formulaProperty().get() == null) {
            if (formula != null) {
                Assert.fail(name + " formula was null");
            }
        } else {
            Assert.assertEquals(name + " formula differs", formula, iocv.formulaProperty().get().getClass());
        }
    }
}
