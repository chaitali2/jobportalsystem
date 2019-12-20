package com.jobportal.jobportalsystem.dto.profile;

import javax.validation.constraints.*;

public class ProfileDTO {

    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String firstName;

    private String userID;
    @NotNull(message = "Last name is compulsory")
    @NotBlank(message = "Last name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String lastName;

    @NotNull(message = "Mobile no  is compulsory")
    @NotBlank(message = "Mobile no is compulsory")
    @Pattern(regexp = "((0/91)?[7-9][0-9]{9})", message = "Please enter valid mobile no.")
    @Size(min = 10, message = "Please enter valid mobile no.")
    private String mobileNo;

    private String street;
    private String city;
    private String state;
    private String experience;
    private String highestDegree;
    private String percentage;
    private String passingYear;
    private String expectedSalary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "firstName='" + firstName + '\'' +
                ", userID='" + userID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", experience='" + experience + '\'' +
                ", highestDegree='" + highestDegree + '\'' +
                ", percentage='" + percentage + '\'' +
                ", passingYear='" + passingYear + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                '}';
    }
}
