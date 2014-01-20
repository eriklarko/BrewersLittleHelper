package org.blh.beerxml;

/**
 * Thrown if anything goes wrong with the parsing of a BeerXML file.
 *
 * @author thinner
 */
public class ParseException extends Exception {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable innerException) {
        super(innerException);
    }

    public ParseException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
