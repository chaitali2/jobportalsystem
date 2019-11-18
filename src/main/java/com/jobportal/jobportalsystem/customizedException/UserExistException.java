package com.jobportal.jobportalsystem.customizedException;

public class UserExistException extends Exception {

    public UserExistException(String message) {
        super(message);
    }
}
