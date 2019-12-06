package com.jobportal.jobportalsystem.rest.registration;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.registration.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.text.ParseException;

@Controller
@Path("/jobportal")
public class RegistrationRestService {

    @Autowired
    RegistrationService registrationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationRestService.class);

    @POST
    @Path("signup")
    @Produces("application/json")
    public ResponseEntity registerUserDetail(@Valid RegistrationDetailDTO registrationDetailDTO) throws UserExistException, AuthenticationException, ParseException {

            LOGGER.info("registrationDetailDTO=" + registrationDetailDTO);
            registrationService.registerUserDetail(registrationDetailDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Success fully registered!");



    }

}
