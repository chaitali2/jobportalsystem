package com.jobportal.jobportalsystem.dto.profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PasswordDTO {

    private String username;

    @NotNull(message = "Please enter old password")
    @NotBlank(message = "Please enter old password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid old password")
    private String oldPassword;

    @NotNull(message = "Please enter new password")
    @NotBlank(message = "Please enter new password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid new password")
    private String newPassword;

    @NotNull(message = "Please enter confirm password")
    @NotBlank(message = "Please enter confirm password")
    @Pattern(regexp = "(^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$)",message = "Please enter valid confirm password")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "username='" + username + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
