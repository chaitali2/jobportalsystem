package com.jobportal.jobportalsystem.rest;

import com.jobportal.jobportalsystem.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class LoginRestService {

    @Autowired
    LoginService loginService;

    @POST
    @Produces("application/json")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {
        try {

            // Authenticate the user using the credentials provided
//            authenticate(username, password);

            // Issue a token for the user
//            String token = issueToken(username);

            // Return the token on the response
//            return Response.ok(token).build();
            return Response.ok().build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}
