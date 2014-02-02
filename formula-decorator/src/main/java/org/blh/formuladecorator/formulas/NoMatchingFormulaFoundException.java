package org.blh.formuladecorator.formulas;

/**
 * Created by Erik Larkö at 6/28/13 1:30 PM
 */
public class NoMatchingFormulaFoundException extends Exception {

    public NoMatchingFormulaFoundException() {
    }

    public NoMatchingFormulaFoundException(String message) {
        super(message);
    }

    public NoMatchingFormulaFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
