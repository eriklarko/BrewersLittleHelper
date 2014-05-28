package se.angstroms.blh.anders.uncategorized.context.serializing;

import se.angstroms.blh.anders.uncategorized.context.serializing.FullContextSerializer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.blh.core.unit.Factor;
import org.blh.core.unit.gravity.SpecificGravity;
import org.junit.Assert;
import org.junit.Test;
import se.angstroms.blh.anders.formulas.NopFormula;
import se.angstroms.blh.anders.uncategorized.context.FullContext;

/**
 *
 * @author eriklark
 */
public class FullContextSerializerTest {

    private final String pathToValidFile = "validFullContext.json";

    @Test
    public void testWriteToValidFile() throws IOException, URISyntaxException {
        FullContext context = new FullContext();
        context.getOriginalGravity().setFormula(new NopFormula<>(new SpecificGravity(1), context));
        context.getExtractionEfficiency().set(new Factor(70));
        context.getCoolingLoss().set(new Factor(40));

        File tempFile = File.createTempFile(UUID.randomUUID().toString(), null);
        new FullContextSerializer().saveToDisk(context, tempFile);

        assertSameFileContents(new File(this.getClass().getResource(pathToValidFile).toURI()), tempFile);
    }

    private void assertSameFileContents(File reference, File generated) throws IOException {
        List<String> expectedLines = Files.readAllLines(reference.toPath());
        List<String> actualLines = Files.readAllLines(generated.toPath());

        Collections.sort(expectedLines);
        Collections.sort(actualLines);

        Assert.assertArrayEquals(expectedLines.toArray(), actualLines.toArray());
    }
}
