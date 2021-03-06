package com.jobportal.jobportalsystem.configuration;

import com.jobportal.jobportalsystem.customizedException.GenericExceptionMapper;
import com.jobportal.jobportalsystem.customizedException.ValidationExceptionMapper;
import com.jobportal.jobportalsystem.rest.login.LoginRestService;
import com.jobportal.jobportalsystem.rest.profile.ChangePasswordRestService;
import com.jobportal.jobportalsystem.rest.profile.ProfileRestService;
import com.jobportal.jobportalsystem.rest.recruiterjobseeker.RecruiterJobSeekerRestService;
import com.jobportal.jobportalsystem.rest.registration.RegistrationRestService;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CorsFilter.class);
        register(RegistrationRestService.class);
        register(LoginRestService.class);
        register(RecruiterJobSeekerRestService.class);
        register(MultiPartFeature.class);
        register(GenericExceptionMapper.class);
        register(ValidationExceptionMapper.class);
        register(ProfileRestService.class);
        register(ChangePasswordRestService.class);
    }

}
