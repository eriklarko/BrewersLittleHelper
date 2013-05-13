/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml;

import blh.core.beerxml.types.BeerXMLRecord;

/**
 *
 * @author thinner
 */
public interface ClassToRecordNameMapper {
    public String getRecordName(BeerXMLRecord record) throws UnknownRecordException;
}
