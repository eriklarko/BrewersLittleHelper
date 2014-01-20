package org.blh.beerxml.writer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.blh.beerxml.ClassToRecordNameMapper;
import org.blh.beerxml.ClassToRecordNameMapperImpl;
import org.blh.beerxml.ParseException;
import org.blh.beerxml.parser.BeerXMLParser;
import org.blh.beerxml.parser.dom.DOMParser;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Erik Larkö
 */
public class BasicWriterTest {

    @Test
    public void testAllTemplates() {
        File templatesDirectory = new File("src/test/resources/org/blh/beerxml/templates/");
        File[] templateFiles = templatesDirectory.listFiles();
        for(File templateFile : templateFiles) {
            try {
                test(templateFile);
            } catch (Exception ex) {
                throw new RuntimeException("Failed on " + templateFile.getAbsolutePath(), ex);
            }
        }
    }

    private void test(File templateFile) throws ParseException, IOException, ClassToRecordNameMapper.NoRecordNameException {
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
