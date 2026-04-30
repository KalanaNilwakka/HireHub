package com.Kalana.HireHub.exception;

public class ApplicationAlreadyApprovedException extends RuntimeException {
    public ApplicationAlreadyApprovedException(Long applicationId) {
        super("Application with the application ID " + applicationId + " is already approved");
    }
}
