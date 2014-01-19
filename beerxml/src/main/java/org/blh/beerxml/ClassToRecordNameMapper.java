package org.blh.beerxml;

import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;

/**
 *
 * @author thinner
 */
public interface ClassToRecordNameMapper {

    public String getRecordName(BeerXMLRecord record) throws UnknownRecordSetException;

    public String getRecordSetName(BeerXMLRecordSet recordSet) throws UnknownRecordSetException;
}
