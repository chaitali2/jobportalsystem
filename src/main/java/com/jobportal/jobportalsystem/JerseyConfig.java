package com.jobportal.jobportalsystem;

//import demo.ItemService;
//import demo.OrderController;
//import demo.UserResource;
import com.jobportal.jobportalsystem.rest.RegistrationRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        packages("com.login.logindemo");
//        register(UserResource.class);
        register(CorsFilter.class);
        register(RegistrationRestService.class);
//        register(OrderController.class);
//        register(ItemService.class);
//
//        register(SecurityFilter.class);
    }

}
