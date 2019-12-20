package com.jobportal.jobportalsystem.model.profile;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EducationExperince")
public class EducationExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 10)
    private String experience;

    @Size(max = 50)
    private String highestDegree;

    @Size(max = 10)
    private String percentage;

    @Size(max = 5)
    private String passingYear;

    @Size(max = 10)
    private String expectedSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "EducationExperience{" +
                "id=" + id +
                ", experience='" + experience + '\'' +
                ", highestDegree='" + highestDegree + '\'' +
                ", percentage='" + percentage + '\'' +
                ", passingYear='" + passingYear + '\'' +
                ", expectedSalary='" + expectedSalary + '\'' +
                '}';
    }
}
