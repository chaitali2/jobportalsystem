package com.jobportal.jobportalsystem.rest;

import com.jobportal.jobportalsystem.dto.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class LoginRestService {

    @Autowired
    LoginService loginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);

    @POST
    @Produces("application/json")
    @Path("login")
    public Response authenticateUser(LoginDetailDTO loginDetailDTO) {
        try {
            LOGGER.info("username=="+loginDetailDTO);
            // Authenticate the user using the credentials provided
            UserProfileDTO userProfile=loginService.authenticate(loginDetailDTO);
            loginService.resetSecurityCridentials(loginDetailDTO.getPassword(),userProfile);
            String secureUserToken = loginService.issueSecureToken(userProfile);
            LOGGER.info("SECURETOKEN=="+secureUserToken);
            return Response.ok().build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}
