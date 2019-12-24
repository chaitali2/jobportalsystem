package com.jobportal.jobportalsystem.configuration;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {
        System.out.println("==================respomnse get=====================");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization,token,username,token1");
        responseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
