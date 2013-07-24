/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.blh.beerxml;

import java.io.File;
import java.util.List;
import org.blh.beerxml.types.BeerXMLRecord;
import org.blh.beerxml.types.BeerXMLRecordSet;

/**
 *
 * @author thinner
 */
public interface BeerXMLParser {
    List<BeerXMLRecordSet<BeerXMLRecord>> parse(File xmlFile) throws ParseException;
}
