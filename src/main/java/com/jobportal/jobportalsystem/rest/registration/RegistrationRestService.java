package com.jobportal.jobportalsystem.rest.registration;

import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.registration.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
            LOGGER.error("error==" + e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(errorDTO);
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
