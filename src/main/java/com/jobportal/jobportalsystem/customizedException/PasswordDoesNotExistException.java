package com.jobportal.jobportalsystem.customizedException;

public class PasswordDoesNotExistException extends BusinessException {
    public PasswordDoesNotExistException(String message) {
        super(message);
    }

}
