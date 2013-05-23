/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml;

import blh.core.beerxml.types.BeerXMLRecord;
import blh.core.beerxml.types.BeerXMLRecordSet;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author thinner
 */
public interface BeerXMLWriter<T extends BeerXMLRecord> {

    public void write(File file, List<BeerXMLRecordSet<T>> recordSets) throws IOException, UnknownRecordSetException;
}
