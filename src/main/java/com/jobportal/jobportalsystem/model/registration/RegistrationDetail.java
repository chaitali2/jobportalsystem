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
    private String firstname;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false)
    private String lastname;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false)
    private String dob;

    @NotBlank
    @Size(max = 25)
    @Column(nullable = false, unique = true)
    private String emailid;

    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String mobno;

    @Column(table = "user_credential")
    private String salt;

    @NotBlank
    @Size(max = 2)
    @Column(table = "user_credential",nullable = false)
    private String usertype;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "RegistrationDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dob='" + dob + '\'' +
                ", emailid='" + emailid + '\'' +
                ", mobno='" + mobno + '\'' +
                ", salt='" + salt + '\'' +
                ", usertype='" + usertype + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
