package org.blh.beerxml.type.builder;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.type.BeerXMLRecord;

/**
 * Represents a class able to go from a set of strings to a valid BeerXML
 * record.
 * 
 * @author thinner
 */
public interface Builder<T extends BeerXMLRecord> {

    Builder<T> set(String tagName, String value) throws ParseException, UnknownTagException;

    T create();
}
