package se.angstroms.blh.anders.uncategorized.context.serializing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import se.angstroms.blh.anders.uncategorized.context.FullContext;
import se.angstroms.blh.anders.uncategorized.context.InitializerException;
import se.angstroms.blh.anders.uncategorized.value.CalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.uncategorized.value.InputtedValue;
import se.angstroms.blh.anders.uncategorized.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class FullContextSerializer {

    @Inject
    UnitStringParserFactory unitStringParserFactory;

    private Gson gson;
    private final InputtedOrCalculatedValueJsonSerializer inputtedOrCalculatedValueJsonSerializer;
    private final FullContextJsonDeserializer fullContextJsonDeserializer;

    public FullContextSerializer() {
        inputtedOrCalculatedValueJsonSerializer = new InputtedOrCalculatedValueJsonSerializer(unitStringParserFactory);
        fullContextJsonDeserializer = new FullContextJsonDeserializer();
    }

    private void setupGsonObject() {
        if (gson != null) {
            return;
        }

        gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(FullContext.class,               fullContextJsonDeserializer)
                .registerTypeAdapter(InputtedOrCalculatedValue.class, inputtedOrCalculatedValueJsonSerializer)
                .registerTypeAdapter(InputtedValue.class,             new InputtedValueJsonSerializer(unitStringParserFactory))
                .registerTypeAdapter(CalculatedValue.class,           new CalculatedValueJsonSerializer())
                .create();
    }

    public void saveToDisk(FullContext context, File destination) throws IOException {
        if (!destination.exists() || !destination.canWrite()) {
            throw new IOException(destination.getAbsolutePath() + " either doesn't exist or isn't writable");
        }

        setupGsonObject();
        try (Writer fw = new OutputStreamWriter(new FileOutputStream(destination), Charset.forName("UTF-8"))) {
            gson.toJson(context, fw);
        }

    }

    public FullContext parseFromFile(File f) throws FileNotFoundException, IOException, InitializerException {
        setupGsonObject();
        try (Reader reader = new InputStreamReader(new FileInputStream(f), Charset.forName("UTF-8"))) {
            FullContext context = new FullContext();
            fullContextJsonDeserializer.setFullContext(context);
            inputtedOrCalculatedValueJsonSerializer.setFullContext(context);
            return gson.fromJson(reader, FullContext.class);
        }
    }
}
