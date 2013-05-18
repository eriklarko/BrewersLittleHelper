/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blh.core.beerxml;

import blh.core.beerxml.types.BeerXMLRecordSet;
import java.io.File;
import java.util.List;

/**
 *
 * @author thinner
 */
public interface BeerXMLParser {
    List<BeerXMLRecordSet> parse(File xmlFile) throws ParseException;
}
