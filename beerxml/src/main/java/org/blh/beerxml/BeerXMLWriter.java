package org.blh.beerxml;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;

/**
 *
 * @author thinner
 */
public interface BeerXMLWriter<T extends BeerXMLRecord> {

    public void write(File file, List<BeerXMLRecordSet<T>> recordSets) throws IOException, UnknownRecordSetException;
}
