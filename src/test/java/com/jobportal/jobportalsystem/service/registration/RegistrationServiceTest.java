package com.jobportal.jobportalsystem.service.registration;

import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.Utility;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    RegistrationDetailDTO registrationDetailDTO;


    @MockBean
    RegistrationDAO registrationDao;

    @MockBean
    AuthenticationUtil authenticationUtil;

    @MockBean
    Utility utility;

    @Autowired
    RegistrationService registrationService;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        registrationDetailDTO = new RegistrationDetailDTO();
        registrationDetailDTO.setFirstname("chaitali");
        registrationDetailDTO.setLastname("Khachane");
        registrationDetailDTO.setDob("11-01-1195");
        registrationDetailDTO.setEmailid("chaitali@gmail.com");
        registrationDetailDTO.setMobno("8866049741");
        registrationDetailDTO.setPassword("Chai@1234");
        registrationDetailDTO.setConfpassword("Chai@1234");
        registrationDetailDTO.setUsertype("R");
    }

    @Test
    void registerUserDetail() {
    }


}