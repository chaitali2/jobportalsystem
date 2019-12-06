package com.jobportal.jobportalsystem.rest.profile;

import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.rest.recruiter.RecruiterRestService;
import com.jobportal.jobportalsystem.service.profile.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/jobportal")
public class ProfileRestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileRestService.class);

    @Autowired
    ProfileService profileService;

    @POST
    @Produces("application/json")
    @Path("userdetails")
    public ResponseEntity fetchUserDetails(Map<String,Long> keyValue) {
        LOGGER.info("recruiter_id==" + keyValue.get("user_id"));
        ProfileDTO profileDTO = profileService.fetchUserDetails(keyValue.get("user_id"));
        LOGGER.info("profileDTO==" + profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(profileDTO);
    }

    @POST
    @Produces("application/json")
    @Path("saveProfileDetail")
    public ResponseEntity saveProfileDetail(@Valid ProfileDTO profileDTO) {
        LOGGER.info("profileDTO==" + profileDTO);
        profileService.saveProfileDetail(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
