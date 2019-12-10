package com.jobportal.jobportalsystem.model.recruiter_jobseeker;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;

import javax.persistence.*;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String job_type;
    private double experience;
    private double salary_offer;
    private String job_opening_date;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_location_id")
    private JobLocation jobLocation;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    Set<Skill> skillSet;

    public Set<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet) {
        this.skillSet = skillSet;
    }

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

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public double getSalary_offer() {
        return salary_offer;
    }

    public void setSalary_offer(double salary_offer) {
        this.salary_offer = salary_offer;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
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

    @Override
    public String toString() {
        return "PostJobDetail{" +
                "id=" + id +
                ", registrationDetail=" + registrationDetail +
                ", company='" + company + '\'' +
                ", category=" + category +
                ", job_type='" + job_type + '\'' +
                ", experience=" + experience +
                ", salary_offer=" + salary_offer +
                ", job_opening_date='" + job_opening_date + '\'' +
                ", description='" + description + '\'' +
                ", jobLocation=" + jobLocation +
                ", skillSet=" + skillSet +
                '}';
    }
}
