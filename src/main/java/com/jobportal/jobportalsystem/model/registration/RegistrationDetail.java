package com.jobportal.jobportalsystem.model.registration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_detail")
@SecondaryTable(name = "user_credential", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id"))
public class RegistrationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false)
    private String dateOfBirth;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false, unique = true)
    private String emailId;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String mobileNo;

    @Column(table = "user_credential")
    private String salt;

    @NotBlank
    @Size(max = 2)
    @Column(table = "user_credential",nullable = false)
    private String userType;

    @NotBlank
    @Size(max = 25)
    @Column(table = "user_credential",
            unique = true,
            nullable = false)
    private String username;

    @NotBlank
    @Column(table = "user_credential",
            nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationDetail{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", salt='" + salt + '\'' +
                ", userType='" + userType + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
