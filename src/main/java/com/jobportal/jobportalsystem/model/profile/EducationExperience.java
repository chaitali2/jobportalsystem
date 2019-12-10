package com.jobportal.jobportalsystem.model.profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private String highest_degree;

    @Size(max = 10)
    private String percentage;

    @Size(max = 5)
    private String passing_year;

    @Size(max = 10)
    private String expected_salary;


    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EducationExperience{" +
                "id=" + id +
                ", experience='" + experience + '\'' +
                ", highest_degree='" + highest_degree + '\'' +
                ", percentage='" + percentage + '\'' +
                ", passing_year='" + passing_year + '\'' +
                ", expected_salary=" + expected_salary +
                '}';
    }
}
