package com.jobportal.jobportalsystem.dto.registration;

import com.jobportal.jobportalsystem.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.*;
import java.text.ParseException;


public class RegistrationDetailDTO {
    @Autowired
    Utility utility;
    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String firstname;


    @NotNull(message = "Last name is compulsory")
    @NotBlank(message = "Last name is compulsory")
    @Size(min = 1, max = 25, message = "The length of lastName should be between 1 to 25")
    @Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
    private String lastname;

    @NotNull(message = "Please enter DOB")
    @NotBlank(message = "Please enter DOB")
    private String dob;

    @Email
    private String emailid;

    @NotNull(message = "Mobile no  is compulsory")
    @NotBlank(message = "Mobile no is compulsory")
    @Pattern(regexp = "((0/91)?[7-9][0-9]{9})", message = "Please enter valid mobile no.")
    @Size(min = 10, message = "Please enter valid mobile no.")
    private String mobno;

    @NotNull(message = "Please select User Type")
    @NotBlank(message = "Please select User Type")
    private String usertype;

    @NotNull(message = "Please enter password")
    @NotBlank(message = "Please enter password")
    private String password;

    @NotNull(message = "Please enter confirm password")
    @NotBlank(message = "Please enter confirm password")
    private String confpassword;

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

    public void setDob(String dob) throws ParseException {
        this.dob = utility.changedateformatter(dob,"dd-MM-yyyy");
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }

    @Override
    public String toString() {
        return "RegistrationDetailDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dob='" + dob + '\'' +
                ", emailid='" + emailid + '\'' +
                ", mobno='" + mobno + '\'' +
                ", usertype='" + usertype + '\'' +
                ", password='" + password + '\'' +
                ", confpassword='" + confpassword + '\'' +
                '}';
    }
}
