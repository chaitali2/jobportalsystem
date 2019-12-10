package com.jobportal.jobportalsystem.service.registration;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.Utility;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RegistrationServiceTest {

    RegistrationDetailDTO registrationDetailDTO;

    @Mock
    RegistrationDAO registrationDao;

    @Mock
    AuthenticationUtil authenticationUtil;

    @Mock
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
    public void testRegisterUserDetail() throws ParseException, InvalidKeySpecException, UserExistException, AuthenticationException {

        RegistrationDetail registrationDetail = registrationService.convertDTOtoModel(registrationDetailDTO);
//        Mockito.when(registrationDao.existByEmailID(registrationDetail)).thenReturn(false);
        Mockito.when(authenticationUtil.generateSecurePassword("Chai@1234", "1234")).thenReturn("AAAAA");
//        Mockito.when(registrationDao.saveRegistrationDetail(registrationDetail))
        registrationService.registerUserDetail(registrationDetailDTO);

    }


}