package com.Kalana.HireHub.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with " +  userId + " not found");
    }
}
