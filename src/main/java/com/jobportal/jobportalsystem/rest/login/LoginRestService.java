package com.jobportal.jobportalsystem.rest.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.service.login.LoginService;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
@Path("/jobportal")
public class LoginRestService {

    @Autowired
    LoginService loginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    @POST
    @Produces("application/json")
    @Path("login")
    public ResponseEntity authenticateUser(LoginDetailDTO loginDetailDTO) throws AuthenticationException {

        LOGGER.info("username==" + loginDetailDTO);
        //AUTHENTICATE THE USER
        UserProfileDTO userProfile = loginService.authenticate(loginDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userProfile);


    }

    public String checkIncome(int income) {
        if (income < 1000) {
            return "less";
        }
        return "high";
    }


}


