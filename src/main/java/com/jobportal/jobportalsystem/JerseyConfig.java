package com.jobportal.jobportalsystem;

import com.jobportal.jobportalsystem.customizedException.GenericExceptionMapper;
import com.jobportal.jobportalsystem.customizedException.ValidationExceptionMapper;
import com.jobportal.jobportalsystem.rest.login.LoginRestService;
import com.jobportal.jobportalsystem.rest.other.OtherRestService;
import com.jobportal.jobportalsystem.rest.profile.ProfileRestService;
import com.jobportal.jobportalsystem.rest.registration.RegistrationRestService;
import com.jobportal.jobportalsystem.rest.recruiter.RecruiterRestService;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CorsFilter.class);
        register(RegistrationRestService.class);
        register(LoginRestService.class);
        register(RecruiterRestService.class);
        register(OtherRestService.class);
        register(MultiPartFeature.class);
        register(GenericExceptionMapper.class);
        register(ValidationExceptionMapper.class);
        register(ProfileRestService.class);
    }

}
