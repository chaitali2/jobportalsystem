package com.jobportal.jobportalsystem.aop;

import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;

@Aspect
@Component
public class LoginHitsAspects {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginHitsAspects.class);

    @After("execution(* com.jobportal.jobportalsystem.service.login.LoginService.authenticate(..)) && args(loginDetailDTO,..)")
    public void afterAdvice(LoginDetailDTO loginDetailDTO) {
        Date logintime = new Date();
        LOGGER.info("USER NAME"+loginDetailDTO.getUsername());
        LOGGER.info("LOGIN TIME"+logintime);
    }
}
