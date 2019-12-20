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

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginHitsAspects.class);

    @After("execution(* com.jobportal.jobportalsystem.service.login..*(..)) && args(loginDetailDTO,..)")
    public void afterAdvice(LoginDetailDTO loginDetailDTO) {
//
//        LoginHitsModel loginHitsModel = new LoginHitsModel();
//        loginHitsModel.setUsername(loginDetailDTO.getUsername());
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date logintime = new Date();
//        loginHitsModel.setLoginTime(df.format(logintime));
//        LOGGER.info("USER NAME=>"+loginHitsModel.getUsername());
//        LOGGER.info("LOGIN TIME=>"+loginHitsModel.getLoginTime());
    }


}
