package org.blh.beerxml.writer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.blh.beerxml.ClassToRecordNameMapper.NoRecordNameException;
import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;

/**
 * Represents a class that is capable of writing BeerXML version 1 files.
 *
 * @author thinner
 */
public interface BeerXMLWriter<T extends BeerXMLRecord> {

    void write(File file, List<BeerXMLRecordSet<T>> recordSets) throws IOException, NoRecordNameException;
}
