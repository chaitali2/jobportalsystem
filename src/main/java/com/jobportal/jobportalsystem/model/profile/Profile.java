package com.jobportal.jobportalsystem.model.profile;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id")
    RegistrationDetail registrationDetail;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

    private String experience;
    private String highest_degree;
    private String percentage;
    private String passing_year;
    private String expected_salary;

    public RegistrationDetail getRegistrationDetail() {
        return registrationDetail;
    }

    public void setRegistrationDetail(RegistrationDetail registrationDetail) {
        this.registrationDetail = registrationDetail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    @Override
    public String toString() {
        return "Profile{" +
                "registrationDetail=" + registrationDetail +
                ", address=" + address +
                ", experience='" + experience + '\'' +
                ", highest_degree='" + highest_degree + '\'' +
                ", percentage='" + percentage + '\'' +
                ", passing_year='" + passing_year + '\'' +
                ", expected_salary='" + expected_salary + '\'' +
                '}';
    }
}
