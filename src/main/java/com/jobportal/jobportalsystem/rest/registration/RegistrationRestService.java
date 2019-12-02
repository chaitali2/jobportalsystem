package com.jobportal.jobportalsystem.rest.registration;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.registration.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/jobportal")
public class RegistrationRestService {
    @Autowired
    RegistrationService registrationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRestService.class);

    @POST
    @Path("signup")
    @Produces("application/json")
    public ResponseEntity registerUserDetail(@Valid RegistrationDetailDTO registrationDetailDTO) throws UserExistException {
        try {
            LOGGER.info("registrationDetailDTO=" + registrationDetailDTO);
            registrationService.registerUserDetail(registrationDetailDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Success fully registered!");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
