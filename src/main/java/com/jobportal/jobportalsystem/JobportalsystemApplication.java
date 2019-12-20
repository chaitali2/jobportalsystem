package com.jobportal.jobportalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JobportalsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobportalsystemApplication.class, args);
    }

}
