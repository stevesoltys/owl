package com.stevesoltys.owl.exception;

/**
 * @author Steve Soltys
 */
public class OwlAgentException extends OwlException {

    public OwlAgentException(String message) {
        super("Error while updating agent. " + message);
    }

}
