package com.jobportal.jobportalsystem.rest.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.service.profile.ChangePasswordService;
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
public class ChangePasswordRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordRestService.class);

    @Autowired
    ChangePasswordService changePasswordService;

    @POST
    @Produces("application/json")
    @Path("changePassword")
    public ResponseEntity changePassword(PasswordDTO passwordDTO) throws InvalidKeySpecException, PasswordDoesNotExistException, AuthenticationException {

        LOGGER.info("passwordDTO==" + passwordDTO);
        changePasswordService.changePassword(passwordDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
