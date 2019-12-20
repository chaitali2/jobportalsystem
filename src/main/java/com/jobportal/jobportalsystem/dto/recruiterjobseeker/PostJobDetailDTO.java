package com.jobportal.jobportalsystem.dto.recruiterjobseeker;

import javax.validation.constraints.*;
import java.util.Set;

public class PostJobDetailDTO {

    private Long jobId;

    @NotNull(message = "Please enter company name")
    @NotBlank(message = "Please enter company name")
    private String company;

    private Long categoryId;
    private String categoryName;

    private Set<String> skills;

    @NotNull(message = "Please select type of job")
    @NotBlank(message = "Please select type of job")
    private String jobType;
    @Pattern(regexp = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?", message = "please enter in digit experience")
    private String experience;

    @Pattern(regexp = "[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?", message = "please enter in digit Salary")
    private String salaryOffer;

    @NotNull(message = "Please enter address")
    @NotBlank(message = "Please enter address")
    private String street;

    @NotNull(message = "Please enter city")
    @NotBlank(message = "Please enter city")
    private String city;

    @NotNull(message = "Please enter state")
    @NotBlank(message = "Please enter state")
    private String state;

    @Size(min = 6, message = "Please enter 6 digit pincode")
    @Pattern(regexp = "[0-9]+", message = "Wrong zip!")
    private String pincode;

    @NotNull(message = "Please select job opening date")
    @NotBlank(message = "Please select job opening date")
    private String jobOpeningDate;

    @NotNull(message = "Please enter description")
    @NotBlank(message = "Please enter description")
    private String description;

    private Long postedById;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSalaryOffer() {
        return salaryOffer;
    }

    public void setSalaryOffer(String salaryOffer) {
        this.salaryOffer = salaryOffer;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getJobOpeningDate() {
        return jobOpeningDate;
    }

    public void setJobOpeningDate(String jobOpeningDate) {
        this.jobOpeningDate = jobOpeningDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPostedById() {
        return postedById;
    }

    public void setPostedById(Long postedById) {
        this.postedById = postedById;
    }

    @Override
    public String toString() {
        return "PostJobDetailDTO{" +
                "jobId=" + jobId +
                ", company='" + company + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", skills=" + skills +
                ", jobType='" + jobType + '\'' +
                ", experience='" + experience + '\'' +
                ", salaryOffer='" + salaryOffer + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode='" + pincode + '\'' +
                ", jobOpeningDate='" + jobOpeningDate + '\'' +
                ", description='" + description + '\'' +
                ", postedById=" + postedById +
                '}';
    }
}

