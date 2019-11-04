package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.dto.RegistrationDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationDTO registrationDTO;

    public String registerRecruiterOrJobSeekerDetail(RegistrationDetail registrationDetail) {
        registrationDTO.registerRecruiterOrJobSeekerDetail(registrationDetail);
        System.out.println("in service");
        return "Hello Chaitali";
    }
}
