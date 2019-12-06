package com.jobportal.jobportalsystem.dto.recruiter;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.utility.Utility;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PostJobDetailDTO {

    @Autowired
    Utility utility;

    private Long id;
    @NotNull(message = "Please enter company name")
    @NotBlank(message = "Please enter company name")
    private String company;

    @NotNull(message = "Please select category")
//    @NotBlank(message = "Please select category")
//    @Range(min = 1)
    private Long category_id;


    private String category_name;

    //    @NotNull(message = "Please select skill")
//    @NotBlank(message = "Please select skill")
    private Set<String> skills;

    @NotNull(message = "Please select type of job")
    @NotBlank(message = "Please select type of job")
    private String job_type;

    //    @NotNull(message = "Please enter experience")
//    @NotBlank(message = "Please enter experience")
    private double experience;

    //    @NotNull(message = "Please enter offered salary")
//    @NotBlank(message = "Please enter offered salary")
    private int salary_offer;

    @NotNull(message = "Please enter address")
    @NotBlank(message = "Please enter address")
    private String street_add;

    @NotNull(message = "Please enter city")
    @NotBlank(message = "Please enter city")
    private String city;

    @NotNull(message = "Please enter state")
    @NotBlank(message = "Please enter state")
    private String state;

    //    @NotNull(message = "Please enter pincode")
//    @NotBlank(message = "Please enter pincode")
    @Size(min = 6, message = "Please enter 6 digit pincode")
    @Pattern(regexp = "[0-9]+", message = "Wrong zip!")
    private int pincode;

    @NotNull(message = "Please select job opening date")
    @NotBlank(message = "Please select job opening date")
    private String job_opening_date;

    @NotNull(message = "Please enter description")
    @NotBlank(message = "Please enter description")
    private String description;

    private Long posted_by_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPosted_by_id() {
        return posted_by_id;
    }

    public void setPosted_by_id(Long posted_by_id) {
        this.posted_by_id = posted_by_id;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getSalary_offer() {
        return salary_offer;
    }

    public void setSalary_offer(int salary_offer) {
        this.salary_offer = salary_offer;
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

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getJob_opening_date() {
        return job_opening_date;
    }

    public void setJob_opening_date(String job_opening_date) {

        this.job_opening_date = job_opening_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "PostJobDetailDTO{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", skills=" + skills +
                ", job_type='" + job_type + '\'' +
                ", experience='" + experience + '\'' +
                ", salary_offer='" + salary_offer + '\'' +
                ", street_add='" + street_add + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", job_opening_date='" + job_opening_date + '\'' +
                ", description='" + description + '\'' +
                ", posted_by_id=" + posted_by_id +
                '}';
    }
}
