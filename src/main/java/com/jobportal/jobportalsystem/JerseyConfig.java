package com.jobportal.jobportalsystem;

//import demo.ItemService;
//import demo.OrderController;
//import demo.UserResource;
import com.jobportal.jobportalsystem.rest.login.LoginRestService;
import com.jobportal.jobportalsystem.rest.other.OtherRestService;
import com.jobportal.jobportalsystem.rest.registration.RegistrationRestService;
import com.jobportal.jobportalsystem.rest.recruiter.RecruiterRestService;
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
    }

}
