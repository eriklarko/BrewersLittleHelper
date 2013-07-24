package org.blh.beerxml.writers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.blh.beerxml.BeerXMLParser;
import org.blh.beerxml.BeerXMLWriter;
import org.blh.beerxml.ClassToRecordNameMapperImpl;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.parsers.dom.DOMParser;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Erik Larkö
 */
public class BasicWriterTest {
    
    @Test
    public void testAllTemplates() throws ParseException, IOException {
        File templatesDirectory = new File("src/test/resources/org/blh/beerxml/templates/");
        File[] templateFiles = templatesDirectory.listFiles();
        for(File templateFile : templateFiles) {
            test(templateFile);
        }
    }
    
    private void test(File templateFile) throws ParseException, IOException {
        BeerXMLParser parser = DOMParser.defaultDOMParser();
        List<BeerXMLRecordSet<BeerXMLRecord>> expected = parser.parse(templateFile);

        File actualFile = File.createTempFile("actual", "xml");
        BeerXMLWriter<BeerXMLRecord> writer = new BasicWriter(new ClassToRecordNameMapperImpl());
        writer.write(actualFile, expected);

        List<BeerXMLRecordSet<BeerXMLRecord>> actual = parser.parse(actualFile);
        actualFile.delete();
        
        Assert.assertEquals(templateFile.getAbsolutePath(), actual, expected);        
    }
}
