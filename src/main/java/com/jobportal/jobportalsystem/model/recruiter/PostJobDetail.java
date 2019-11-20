package com.jobportal.jobportalsystem.model.recruiter;

import com.jobportal.jobportalsystem.model.JobLocation;
import com.jobportal.jobportalsystem.model.RegistrationDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_post")
public class PostJobDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posted_by_id")
    private RegistrationDetail registrationDetail;

    private String company;
    private String category;
    private String job_type;
    private String experience;
    private String salary_offer;
    private String job_opening_date;
    private String description;

    @OneToOne
    @JoinColumn(name = "job_location_id")
    private JobLocation jobLocation;

    @ElementCollection
    @JoinTable(
            name="job_post_skill")
    private List<String> skills = new ArrayList<String>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationDetail getRegistrationDetail() {
        return registrationDetail;
    }

    public void setRegistrationDetail(RegistrationDetail registrationDetail) {
        this.registrationDetail = registrationDetail;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public JobLocation getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(JobLocation jobLocation) {
        this.jobLocation = jobLocation;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "PostJobDetail{" +
                "id=" + id +
                ", registrationDetail=" + registrationDetail +
                ", company='" + company + '\'' +
                ", category='" + category + '\'' +
                ", job_type='" + job_type + '\'' +
                ", experience='" + experience + '\'' +
                ", salary_offer='" + salary_offer + '\'' +
                ", job_opening_date='" + job_opening_date + '\'' +
                ", description='" + description + '\'' +
                ", jobLocation=" + jobLocation +
                ", skills=" + skills +
                '}';
    }
}
