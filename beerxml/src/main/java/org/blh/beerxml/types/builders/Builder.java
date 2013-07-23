/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml.types.builders;

import org.blh.beerxml.ParseException;
import org.blh.beerxml.UnknownTagException;
import org.blh.beerxml.types.BeerXMLRecord;

/**
 *
 * @author thinner
 */
public interface Builder<T extends BeerXMLRecord> {

    public Builder<T> set(String tagName, String value) throws ParseException, UnknownTagException;

    public T create();
}
