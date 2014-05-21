package se.angstroms.blh.anders.uncategorized.iocv.parsing;

/**
 *
 * @author eriklark
 */
public class ParseException extends Exception {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
