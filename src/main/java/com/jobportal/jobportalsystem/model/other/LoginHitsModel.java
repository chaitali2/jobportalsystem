package com.jobportal.jobportalsystem.model.other;

import javax.persistence.*;

@Entity
@Table(name = "loginHits")
public class LoginHitsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String loginTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginHitsModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
