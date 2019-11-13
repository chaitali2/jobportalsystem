package com.jobportal.jobportalsystem.CustomizedException;

public class UserExistException extends Exception {

    public UserExistException(String message) {
        super(message);
    }
}
