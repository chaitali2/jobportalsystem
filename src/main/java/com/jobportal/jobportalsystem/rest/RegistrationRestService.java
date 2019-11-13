package com.jobportal.jobportalsystem.rest;

import com.jobportal.jobportalsystem.dto.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import com.jobportal.jobportalsystem.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/registration")
public class RegistrationRestService {
    @Autowired
    RegistrationService registrationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRestService.class);

    @POST
    @Path("registerUserDetail")
    @Produces("application/json")
    public String registerUserDetail(RegistrationDetail registrationDetail) throws Exception {
        try {
            return registrationService.registerUserDetail(registrationDetail);
        } catch (Exception e) {
            LOGGER.error("registration failed==" + e);
//            return Response.status(404).entity(e.getMessage())
//                    .type("text/plain").build();
            return "error";
        }
    }

    @GET
    @Path("message1")
    @Produces("application/json")
    public String getAllUsers() {

        System.out.println("getall users..............");
        return "Hello Chaitali";
    }
}
