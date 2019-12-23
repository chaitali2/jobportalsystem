package com.jobportal.jobportalsystem.model.recruiterjobseeker;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "job_post")
public class PostJobDetail {

    public enum Job{
        P,C
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posted_by_id")
    private RegistrationDetail registrationDetail;

    private String company;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Job jobType;
    private double experience;
    private double salaryOffer;
    private Date jobOpeningDate;
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "job_location_id")
    private JobLocation jobLocation;

    @ManyToMany(cascade =CascadeType.MERGE,fetch = FetchType.EAGER)
    Set<Skill> skillSet;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Job getJobType() {
        return jobType;
    }

    public void setJobType(Job jobType) {
        this.jobType = jobType;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public double getSalaryOffer() {
        return salaryOffer;
    }

    public void setSalaryOffer(double salaryOffer) {
        this.salaryOffer = salaryOffer;
    }

    public Date getJobOpeningDate() {
        return jobOpeningDate;
    }

    public void setJobOpeningDate(Date jobOpeningDate) {
        this.jobOpeningDate = jobOpeningDate;
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

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

    @Override
    public String toString() {
        return "PostJobDetail{" +
                "id=" + id +
                ", registrationDetail=" + registrationDetail +
                ", company='" + company + '\'' +
                ", category=" + category +
                ", jobType='" + jobType + '\'' +
                ", experience=" + experience +
                ", salaryOffer=" + salaryOffer +
                ", jobOpeningDate='" + jobOpeningDate + '\'' +
                ", description='" + description + '\'' +
                ", jobLocation=" + jobLocation +
                ", skillSet=" + skillSet +
                '}';
    }
}
