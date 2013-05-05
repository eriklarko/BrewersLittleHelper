/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml;

import blh.core.beerxml.types.BeerXMLData;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author thinner
 */
public interface BeerXMLParser {
    BeerXMLData parse(File xmlFile) throws ParseException;
}
