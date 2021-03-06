package com.jobportal.jobportalsystem.rest.profile;

import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.service.profile.ProfileService;
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

    @Autowired
    ProfileService profileService;

    @POST
    @Produces("application/json")
    @Path("userdetails")
    public ResponseEntity fetchUserDetails(Map<String,Long> keyValue) {
        ProfileDTO profileDTO = profileService.fetchUserDetails(keyValue.get("user_id"));
        return ResponseEntity.status(HttpStatus.OK).body(profileDTO);
    }

    @POST
    @Produces("application/json")
    @Path("saveProfileDetail")
    public ResponseEntity saveProfileDetail(@Valid ProfileDTO profileDTO) {
        profileService.saveProfileDetail(profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
