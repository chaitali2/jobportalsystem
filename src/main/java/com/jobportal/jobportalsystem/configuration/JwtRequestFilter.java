package com.jobportal.jobportalsystem.configuration;

import com.jobportal.jobportalsystem.dto.LoginDetailDTO;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.Addressing;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

//@Configuration
public class JwtRequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info(
                "Starting a transaction for req : {}",
                req.getRequestURI());

        LoginDetailDTO loginDetailDTO = new LoginDetailDTO();
        try {
            if (!req.getRequestURI().contains("/login")) {
                String username = request.getParameter("name");
//            String token = request.getParameter("token");
                String token = "dfdgfdhgfhfgjjjjjjjjjjjjjjjjjjjdrfgdfg";
                loginDetailDTO.setUsername(username);
                if (jwtTokenUtil.validateToken(token, loginDetailDTO)) {
                    chain.doFilter(request, response);
                } else {
                    LOGGER.info("error generated token is not valid");
                    res.sendRedirect("/login");
                }
            } else {
                chain.doFilter(request, response);
            }
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            res.sendRedirect("/authentication/login");
            LOGGER.info("exception generated token is not valid"+exceptionAsString);

            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

    }
}
