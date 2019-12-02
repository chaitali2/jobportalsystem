package com.jobportal.jobportalsystem.customizedException;

public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }
}
