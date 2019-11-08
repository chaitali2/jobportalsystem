package com.jobportal.jobportalsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @GET
    @Path("orderdetail")
    @Produces("application/json")
    public String saveorderdetail(){
        System.out.println("save order detail");
        orderRepository.saveorderdetail();
//        int a=10/0;
//        userRepository.saveuserorderdetail();
        return"success";
    }



}
