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

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.PrintWriter;
import java.io.StringWriter;

@Path("/authentication")
public class LoginRestService {

    @Autowired
    LoginService loginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @POST
    @Produces("application/json")
    @Path("login")
    public ResponseEntity authenticateUser(LoginDetailDTO loginDetailDTO){
        try {
            LOGGER.info("username==" + loginDetailDTO);
            //AUTHENTICATE THE USER
            UserProfileDTO userProfile = loginService.authenticate(loginDetailDTO);
            // FOR AUTHORISED GENERATE TOKEN
            final String token = jwtTokenUtil.generateToken(loginDetailDTO);
            userProfile.setToken(token);
            LOGGER.info("SECURETOKEN==" + token);
            return ResponseEntity.status(HttpStatus.OK).body(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }

    }


}


