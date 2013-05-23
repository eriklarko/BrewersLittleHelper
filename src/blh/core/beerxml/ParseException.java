package blh.core.beerxml;

/**
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
