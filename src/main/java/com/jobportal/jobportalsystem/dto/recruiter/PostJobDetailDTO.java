package com.jobportal.jobportalsystem.dto.recruiter;

import com.jobportal.jobportalsystem.model.other.Category;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class PostJobDetailDTO {

    private Long id;
    private String company;
    private Long category;
    private String category_name;
    private List<String> skills;
    private String job_type;
    private String experience;
    private String salary_offer;
    private String street_add;
    private String city;
    private String state;
    private String pincode;
    private String job_opening_date;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSalary_offer() {
        return salary_offer;
    }

    public void setSalary_offer(String salary_offer) {
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getJob_opening_date() {
        return job_opening_date;
    }

    public void setJob_opening_date(String job_opening_date) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDateTime formatDateTime = LocalDateTime.parse(job_opening_date, formatter);
//        this.job_opening_date = formatDateTime.format(formatter);
        this.job_opening_date = job_opening_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
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
                ", category=" + category +
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
