/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml.types.builders;

import blh.core.beerxml.ParseException;
import blh.core.beerxml.UnknownTagException;
import blh.core.beerxml.types.BeerXMLRecord;

/**
 *
 * @author thinner
 */
public interface Builder<T extends BeerXMLRecord> {

    public Builder<T> set(String tagName, String value) throws ParseException, UnknownTagException;

    public T create();
}
