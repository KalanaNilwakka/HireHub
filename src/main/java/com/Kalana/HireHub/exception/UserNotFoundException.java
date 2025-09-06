package com.Kalana.HireHub.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with " +  userId + " not found");
    }

    public UserNotFoundException(String email) {
        super("User with " +  email + " not found");
    }
}
