package com.jobportal.jobportalsystem;

import com.jobportal.jobportalsystem.controller.RegistrationController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        packages("com.login.logindemo");
        register(UserResource.class);
        register(CorsFilter.class);
        register(RegistrationController.class);
        register(OrderController.class);

//        register(SecurityFilter.class);
    }

}
