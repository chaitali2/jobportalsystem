package com.jobportal.jobportalsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "user_detail")
@SecondaryTable(name = "user_login_detail",pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id") )
public class RegistrationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String dob;
    private String city;
    private String state;
    private String emailid;
    private String mobno;

    @Column(table = "user_login_detail")
    private String typeOfUser;

    @Column(table = "user_login_detail")
    private String username;

    @Column(table = "user_login_detail")
    private String password;

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

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    @Override
    public String toString() {
        return "RegistrationDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dob='" + dob + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", emailid='" + emailid + '\'' +
                ", mobno='" + mobno + '\'' +
                ", typeOfUser='" + typeOfUser + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
