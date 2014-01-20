package org.blh.beerxml;

import org.blh.beerxml.type.BeerXMLRecord;
import org.blh.beerxml.type.BeerXMLRecordSet;

/**
 * Defines a lookup class that can convert a BeerXMLRecord to the corresponding
 * record or record set name.
 *
 * @author thinner
 */
public interface ClassToRecordNameMapper {

    String getRecordName(BeerXMLRecord record) throws NoRecordNameException;

    String getRecordSetName(BeerXMLRecordSet recordSet) throws NoRecordNameException;

    /**
     * Thrown if no record name is related to the specified type.
     */
    public class NoRecordNameException extends Exception {

        public NoRecordNameException(String message) {
            super(message);
        }

        public NoRecordNameException(String message, Throwable innerException) {
            super(message, innerException);
        }
    }
}
