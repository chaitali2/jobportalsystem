package com.jobportal.jobportalsystem.rest;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.service.LoginService;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    public ResponseEntity authenticateUser(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        try {
            LOGGER.info("username==" + loginDetailDTO);
            UserProfileDTO userProfile = loginService.authenticate(loginDetailDTO);
            final String token = jwtTokenUtil.generateToken(loginDetailDTO);
            userProfile.setToken(token);
            LOGGER.info("SECURETOKEN==" + token);
            return new ResponseEntity(userProfile, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("error=="+e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return new ResponseEntity(errorDTO, HttpStatus.OK);
        }

    }
}
