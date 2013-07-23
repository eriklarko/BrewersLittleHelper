/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
