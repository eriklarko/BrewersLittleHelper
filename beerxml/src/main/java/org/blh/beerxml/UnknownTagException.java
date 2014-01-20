package org.blh.beerxml;

/**
 * Thrown if a parser encounters an unknown tag.
 * 
 * @author Erik Larkö
 */
public class UnknownTagException extends ParseException {

    public UnknownTagException(String message) {
        super(message);
    }
}
