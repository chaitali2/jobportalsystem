package com.jobportal.jobportalsystem.dto;

public class UserProfileDTO {
    private Long id;
    private String username;
    private String password;
    private String typeOfUser;
    private String salt;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeOfUser='" + typeOfUser + '\'' +
                ", salt='" + salt + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}