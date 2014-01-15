package org.blh.recipe.uncategorized;

/**
 * Created by Erik Larkö at 6/28/13 1:31 PM
 */
public class NoFormulaFoundException extends Exception {

    public NoFormulaFoundException() {
    }

    public NoFormulaFoundException(String message) {
        super(message);
    }

    public NoFormulaFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
