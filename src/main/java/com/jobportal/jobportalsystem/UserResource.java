package com.jobportal.jobportalsystem;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/secured")
public class UserResource {

    @GET
    @Path("message")
    @Produces("application/json")
    public String getAllUsers() {

        System.out.println("getall users..............");
        return "Hello Chaitali";
    }
}
