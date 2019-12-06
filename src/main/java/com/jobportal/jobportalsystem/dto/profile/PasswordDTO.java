package com.jobportal.jobportalsystem.dto.profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PasswordDTO {

    private String username;

    @NotNull(message = "Please enter old password")
    @NotBlank(message = "Please enter old password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid old password")
    private String old_password;

    @NotNull(message = "Please enter new password")
    @NotBlank(message = "Please enter new password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid new password")
    private String new_password;

    @NotNull(message = "Please enter confirm password")
    @NotBlank(message = "Please enter confirm password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid confirm password")
    private String confirm_password;

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "username='" + username + '\'' +
                ", old_password='" + old_password + '\'' +
                ", new_password='" + new_password + '\'' +
                ", confirm_password='" + confirm_password + '\'' +
                '}';
    }
}
