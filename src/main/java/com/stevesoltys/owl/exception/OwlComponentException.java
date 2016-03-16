package com.stevesoltys.owl.exception;

/**
 * @author Steve Soltys
 */
public class OwlComponentException extends OwlException {

    public OwlComponentException(String message) {
        super("Error while loading components. " + message);
    }

}
