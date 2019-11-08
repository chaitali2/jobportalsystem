package com.jobportal.jobportalsystem;

import org.springframework.boot.CommandLineRunner;
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

//    @Override
//    public void run(String... args) throws Exception {
////        AnnotationConfigApplicationContext context =
////                new AnnotationConfigApplicationContext(TransactionConfig.class);
//      OrderController orderController =new OrderController();
//        orderController.saveorderdetail();
//    }

//    @Bean
//   public EntityManager getEntityManager(){
//        EntityManager entityManager=
//    }
}
