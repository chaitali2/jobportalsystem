package com.jobportal.jobportalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sun.security.krb5.internal.PAForUserEnc;

import javax.persistence.EntityManager;

@SpringBootApplication
public class JobportalsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobportalsystemApplication.class, args);
    }


//    @Bean
//   public EntityManager getEntityManager(){
//        EntityManager entityManager=
//    }
}
