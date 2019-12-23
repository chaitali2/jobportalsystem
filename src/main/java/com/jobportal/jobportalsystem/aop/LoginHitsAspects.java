package com.jobportal.jobportalsystem.aop;

import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
//import com.jobportal.jobportalsystem.model.other.LoginHitsModel;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
