package com.stevesoltys.owl.exception;

/**
 * @author Steve Soltys
 */
public class OwlConfigurationException extends OwlException {

    public OwlConfigurationException(String message) {
        super("Error while loading configuration. " + message);
    }

}
