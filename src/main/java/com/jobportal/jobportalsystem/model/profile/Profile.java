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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "edu_exp_id")
    EducationExperience educationExperience;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EducationExperience getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(EducationExperience educationExperience) {
        this.educationExperience = educationExperience;
    }

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

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", registrationDetail=" + registrationDetail +
                ", address=" + address +
                ", educationExperience=" + educationExperience +
                '}';
    }
}
