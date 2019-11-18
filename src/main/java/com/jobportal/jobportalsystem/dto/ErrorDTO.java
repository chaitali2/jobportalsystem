package com.jobportal.jobportalsystem.dto;

public class ErrorDTO {

   private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
