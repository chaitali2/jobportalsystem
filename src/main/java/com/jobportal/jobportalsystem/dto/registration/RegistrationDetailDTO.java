package com.jobportal.jobportalsystem.dto.registration;

import javax.validation.constraints.*;


public class RegistrationDetailDTO {
    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String firstName;


    @NotNull(message = "Last name is compulsory")
    @NotBlank(message = "Last name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String lastName;

    @NotNull(message = "Please enter DOB")
    @NotBlank(message = "Please enter DOB")
    private String dateOfBirth;

    @Email
    private String emailId;

    @NotNull(message = "Mobile no  is compulsory")
    @NotBlank(message = "Mobile no is compulsory")
    @Pattern(regexp = "((0/91)?[7-9][0-9]{9})", message = "Please enter valid mobile no.")
    @Size(min = 10, message = "Please enter valid mobile no.")
    private String mobileNo;

    @NotNull(message = "Please select User Type")
    @NotBlank(message = "Please select User Type")
    public String userType;

    @NotNull(message = "Please enter password")
    @NotBlank(message = "Please enter password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)", message = "Please enter valid password")
    private String password;

    @NotNull(message = "Please enter confirm password")
    @NotBlank(message = "Please enter confirm password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)", message = "Please enter valid confirm password")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "RegistrationDetailDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", userType='" + userType + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
