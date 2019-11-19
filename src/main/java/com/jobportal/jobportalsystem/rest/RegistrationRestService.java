package com.jobportal.jobportalsystem.rest;

import com.jobportal.jobportalsystem.dto.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import com.jobportal.jobportalsystem.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity registerUserDetail(RegistrationDetailDTO registrationDetailDTO) throws Exception {
        try {
            String status=registrationService.registerUserDetail(registrationDetailDTO);
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("error");
        }
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
