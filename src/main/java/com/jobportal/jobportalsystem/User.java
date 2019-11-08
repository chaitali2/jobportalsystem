package com.jobportal.jobportalsystem;

import javax.persistence.*;

@Entity
@Table(name="user_order_detail")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String profession;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User(String username, String profession) {
        this.username = username;
        this.profession = profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
