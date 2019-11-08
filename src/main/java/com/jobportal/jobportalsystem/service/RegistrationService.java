package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.dto.RegistrationDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    RegistrationDTO registrationDTO;

    public String registerUserDetail(RegistrationDetail registrationDetail) throws Exception {
        return registrationDTO.registerUserDetail(registrationDetail);
    }
}
