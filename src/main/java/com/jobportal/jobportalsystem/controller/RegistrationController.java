package com.jobportal.jobportalsystem.controller;

import com.jobportal.jobportalsystem.model.RegistrationDetail;
import com.jobportal.jobportalsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/registration")
public class RegistrationController {
//

    @Autowired
    RegistrationService registrationService;

    @POST
    @Path("registerUserDetail")
    @Produces("application/json")
    public String registerUserDetail(RegistrationDetail registrationDetail) throws Exception {
        System.out.println("getall users.............."+registrationDetail);
        return registrationService.registerUserDetail(registrationDetail);
    }

//    @GET
//    @Path("message1")
//    @Produces("application/json")
//    public String getAllUsers() {
//
//        System.out.println("getall users..............");
//        return "Hello Chaitali";
//    }

}


