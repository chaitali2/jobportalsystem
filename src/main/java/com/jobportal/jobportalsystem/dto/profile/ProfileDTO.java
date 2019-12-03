package com.jobportal.jobportalsystem.dto.profile;

import javax.validation.constraints.*;

public class ProfileDTO {

    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String firstname;

    private String userID;

    @NotNull(message = "Last name is compulsory")
    @NotBlank(message = "Last name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String lastname;

    @NotNull(message = "Mobile no  is compulsory")
    @NotBlank(message = "Mobile no is compulsory")
    @Pattern(regexp = "((0/91)?[7-9][0-9]{9})", message = "Please enter valid mobile no.")
    @Size(min = 10, message = "Please enter valid mobile no.")
    private String mobno;

    private String street_add;

    private String city;

    private String state;

    private String experience;
    private String highest_degree;
    private String percentage;
    private String passing_year;
    private String expected_salary;

    public String getHighest_degree() {
        return highest_degree;
    }

    public void setHighest_degree(String highest_degree) {
        this.highest_degree = highest_degree;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getExpected_salary() {
        return expected_salary;
    }

    public void setExpected_salary(String expected_salary) {
        this.expected_salary = expected_salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getStreet_add() {
        return street_add;
    }

    public void setStreet_add(String street_add) {
        this.street_add = street_add;
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

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobno='" + mobno + '\'' +
                ", street_add='" + street_add + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
