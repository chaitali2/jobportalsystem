package com.jobportal.jobportalsystem.rest.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.security.spec.InvalidKeySpecException;

@Path("/jobportal")
public class LoginRestService {

    @Autowired
    private LoginService loginService;

    @POST
    @Produces("application/json")
    @Path("login")
    public ResponseEntity authenticateUser(LoginDetailDTO loginDetailDTO) throws AuthenticationException, InvalidKeySpecException
    {
        UserProfileDTO userProfile = loginService.authenticate(loginDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userProfile);
    }
}


