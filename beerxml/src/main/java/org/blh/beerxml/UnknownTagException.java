package org.blh.beerxml;

/**
 *
 * @author Erik Larkö
 */
public class UnknownTagException extends ParseException {

    public UnknownTagException(String message) {
        super(message);
    }
}
