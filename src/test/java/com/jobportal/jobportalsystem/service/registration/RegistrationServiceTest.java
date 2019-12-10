package com.jobportal.jobportalsystem.service.registration;


import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RegistrationServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceTest.class);

    RegistrationDetailDTO registrationDetailDTO;

    @Captor
    ArgumentCaptor<RegistrationDetail> registrationDetailArgumentCaptor;

    @Mock
    RegistrationDAO registrationDao;

    @Mock
    AuthenticationUtil authenticationUtil;

    @Mock
    Utility utility;

    @InjectMocks
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
    public void testRegisterUserDetail() throws InvalidKeySpecException, ParseException, AuthenticationException, UserExistException {

        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(false);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), "abcd")).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());

        registrationService.registerUserDetail(registrationDetailDTO);
        RegistrationDetail registrationDetail = registrationDetailArgumentCaptor.getValue();
        assertNotNull(registrationDetail);
        assertEquals(registrationDetailDTO.getEmailid(), registrationDetail.getUsername());
        assertEquals("securepasword", registrationDetail.getPassword());
    }

    @Test(expected = UserExistException.class)
    public void testRegisterUserDetailForUserExist() throws InvalidKeySpecException, ParseException, AuthenticationException, UserExistException {
        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(true);
        Mockito.when(authenticationUtil.generateSecurePassword("Chai@1234", "Lpm28h5myQheIFNflzA7oaB1bFGSCn")).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());
        registrationService.registerUserDetail(registrationDetailDTO);
    }


    @Test(expected = AuthenticationException.class)
    public void testRegisterUserDetailForUserValidatePassword() throws InvalidKeySpecException, ParseException, AuthenticationException, UserExistException {
        registrationDetailDTO.setConfpassword("Chai2@1234");
        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(false);
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq("Chai@1234"), Mockito.eq("Lpm28h5myQheIFNflzA7oaB1bFGSCn"))).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());
        registrationService.registerUserDetail(registrationDetailDTO);
    }


}