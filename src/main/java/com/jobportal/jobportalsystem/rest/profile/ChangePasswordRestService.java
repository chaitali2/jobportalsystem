package com.jobportal.jobportalsystem.rest.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.service.profile.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.security.spec.InvalidKeySpecException;

@Path("/jobportal")
public class ChangePasswordRestService {
    @Autowired
    private ChangePasswordService changePasswordService;

    @POST
    @Produces("application/json")
    @Path("changePassword")
    public ResponseEntity changePassword(@Valid PasswordDTO passwordDTO) throws InvalidKeySpecException, PasswordDoesNotExistException, AuthenticationException {
        changePasswordService.changePassword(passwordDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
