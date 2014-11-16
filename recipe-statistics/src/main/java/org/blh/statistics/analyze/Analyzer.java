package org.blh.statistics.analyze;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Analyzer<T> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void analyze(File sourceFolder, File destinationFolder) {
        List<T> analyzedElements = analyzeElementsInFolder(sourceFolder);
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        //analyzedElements.stream().forEach((element) -> writeToDisk(element, destinationFolder));
    }

    private List<T> analyzeElementsInFolder(File folder) {
        analyzeElementsInFolderLoops(folder);
        analyzeElementsInFolderSequential(folder);
        return analyzeElementsInFolderParallel(folder);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////





















    private List<T> analyzeElementsInFolderLoops(File folder) {
        long start = System.currentTimeMillis();

        List<T> elements = new LinkedList<>();
        for (File file : Files.fileTreeTraverser().breadthFirstTraversal(folder)) {
            if (!file.isFile() || !file.canRead()) {
                continue;
            }

            Optional<T> analyzedFile = analyze(file);
            if (analyzedFile.isPresent()) {
                elements.add(analyzedFile.get());
            }
        }


        long end = System.currentTimeMillis();
        System.out.println("###########################################################################################");
        System.out.println("## For loop: " + (end - start) + " ms");
        System.out.println("###########################################################################################");
        return elements;
    }



























    private List<T> analyzeElementsInFolderSequential(File folder) {
        long start = System.currentTimeMillis();
        List<T> elements = Files.fileTreeTraverser()
                .breadthFirstTraversal(folder)
                .toList()
                .stream()
                .filter((f) -> f.isFile() && f.canRead())
                .map(this::analyze)
                .filter((a) -> a.isPresent())
                .map((a) -> a.get())
                .collect(Collectors.toList());


        long end = System.currentTimeMillis();
        System.out.println("###########################################################################################");
        System.out.println("## Sequential streams: " + (end - start) + " ms");
        System.out.println("###########################################################################################");
        return elements;
    }

























    private List<T> analyzeElementsInFolderParallel(File folder) {
        long start = System.currentTimeMillis();
        List<T> elements = Files.fileTreeTraverser()
                .breadthFirstTraversal(folder)
                .toList()
                .stream()
                .filter((f) -> f.isFile() && f.canRead())
                .parallel()
                .map(this::analyze)
                .filter((a) -> a.isPresent())
                .map((a) -> a.get())
                .collect(Collectors.toList());


        long end = System.currentTimeMillis();
        System.out.println("###########################################################################################");
        System.out.println("## Parallel streams: " + (end - start) + " ms");
        System.out.println("###########################################################################################");

        return elements;
    }




























    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Analyzes the contents of the file
     */
    protected abstract Optional<T> analyze(File elementFile);

    protected String readEntireFile(File elementFile) {
        try {
            return Files.toString(elementFile, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void writeToDisk(T element, File destination) {
        destination = new File(destination, getFilename(element).replace("/", "__") + ".json");
        try (Writer fw = new OutputStreamWriter(new FileOutputStream(destination), Charset.forName("UTF-8"))) {
            gson.toJson(element, fw);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected abstract String getFilename(T element);

}
