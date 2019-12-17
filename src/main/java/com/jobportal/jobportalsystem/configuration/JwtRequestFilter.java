package com.jobportal.jobportalsystem.configuration;

import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class JwtRequestFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        LoginDetailDTO loginDetailDTO = new LoginDetailDTO();
        try {
            if (!"OPTIONS".equals(req.getMethod())) {
                if (!req.getRequestURI().contains("/login") && !req.getRequestURI().contains("/signup")) {
                    String token = req.getHeader("token");
                    String username = req.getHeader("username");

                    if (!StringUtils.isEmpty(token)) {
                        LOGGER.info("token exist");
                        loginDetailDTO.setUsername(username);
                        if (jwtTokenUtil.validateToken(token, loginDetailDTO)) {
                            chain.doFilter(request, response);
                        } else {
                            LOGGER.error("error generated token is not valid");
                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
                        }
                    } else {
                        LOGGER.error("No Auth token found");
                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
                    }
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("exception generated token is not valid" + e);
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }

    }
}
