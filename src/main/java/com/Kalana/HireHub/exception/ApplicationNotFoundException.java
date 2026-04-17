package com.Kalana.HireHub.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long applicationId) {
        super("Application with the application ID " + applicationId + " does not exist");
    }
}
