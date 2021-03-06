package com.jobportal.jobportalsystem.customizedException;

public class ErrorDetails {
    private String status;
    private String errorMessage;

    public ErrorDetails(){}

    public ErrorDetails(String statusFromOutside, String errorMessageFromOutside)
    {
        this.status = statusFromOutside;
        this.errorMessage = errorMessageFromOutside;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
