package com.jobportal.jobportalsystem.service;

import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;

@Service
public class LoginService {

    public Response authenticate(String username,String password){
        return Response.ok().build();

    }
}
