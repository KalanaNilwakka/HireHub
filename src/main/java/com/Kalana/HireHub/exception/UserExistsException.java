package com.Kalana.HireHub.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String email) {
        super("A user for " + email + "already exists");
    }
}
