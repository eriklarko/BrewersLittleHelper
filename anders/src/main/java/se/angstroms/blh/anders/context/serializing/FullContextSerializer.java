package se.angstroms.blh.anders.context.serializing;

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
import javax.inject.Inject;
import se.angstroms.blh.anders.context.FullContext;
import se.angstroms.blh.anders.context.value.CalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedOrCalculatedValue;
import se.angstroms.blh.anders.context.value.InputtedValue;
import se.angstroms.blh.anders.context.value.parsing.UnitStringParserFactory;

/**
 *
 * @author eriklark
 */
public class FullContextSerializer {

    @Inject
    UnitStringParserFactory unitStringParserFactory;

    private Gson gson;
    private InputtedOrCalculatedValueJsonSerializer inputtedOrCalculatedValueJsonSerializer;
    private FullContextJsonDeserializer fullContextJsonDeserializer;

    private void setupGsonObject() {
        if (gson != null) {
            return;
        }

        inputtedOrCalculatedValueJsonSerializer = new InputtedOrCalculatedValueJsonSerializer(unitStringParserFactory);
        fullContextJsonDeserializer = new FullContextJsonDeserializer();

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

    public FullContext parseFromFile(File f) throws FileNotFoundException, IOException {
        setupGsonObject();
        try (Reader reader = new InputStreamReader(new FileInputStream(f), Charset.forName("UTF-8"))) {
            FullContext context = new FullContext();
            fullContextJsonDeserializer.setFullContext(context);
            inputtedOrCalculatedValueJsonSerializer.setFullContext(context);
            return gson.fromJson(reader, FullContext.class);
        }
    }
}
