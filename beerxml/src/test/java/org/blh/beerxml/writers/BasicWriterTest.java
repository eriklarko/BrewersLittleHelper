package org.blh.beerxml.writers;

import org.blh.beerxml.BeerXMLParser;
import org.blh.beerxml.BeerXMLWriter;
import org.blh.beerxml.ClassToRecordNameMapperImpl;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.parsers.dom.DOMParser;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;
import org.blh.beerxml.writers.BasicWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Erik Larkö
 */
public class BasicWriterTest {
    
    @Test
    public void testAllTemplates() throws ParseException, IOException {
        File templatesDirectory = new File("/home/thinner/Code/BLHCore/tests/test/blh/core/beerxml/templates/");
        File[] templateFiles = templatesDirectory.listFiles();
        for(File templateFile : templateFiles) {
            test(templateFile);
        }
    }
    
    //@Test
    public void testSingleTemple() throws ParseException, IOException {
        test(new File("/home/thinner/Code/BLHCore/tests/test/blh/core/beerxml/templates/Recipe.xml"));
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
