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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceTest {

    private RegistrationDetailDTO registrationDetailDTO;
    @Captor
    private ArgumentCaptor<RegistrationDetail> registrationDetailArgumentCaptor;
    @Mock
    private RegistrationDAO registrationDao;
    @Mock
    private AuthenticationUtil authenticationUtil;
    @InjectMocks
    RegistrationService registrationService;
    @Mock
    private Utility utility;
    @Before
    public void setUp() {
        prepareData();
    }

    private void prepareData() {
        registrationDetailDTO = new RegistrationDetailDTO();
        registrationDetailDTO.setFirstName("chaitali");
        registrationDetailDTO.setLastName("Khachane");
        registrationDetailDTO.setDateOfBirth("11-01-1195");
        registrationDetailDTO.setEmailId("chaitali@gmail.com");
        registrationDetailDTO.setMobileNo("8866049741");
        registrationDetailDTO.setPassword("Chai@1234");
        registrationDetailDTO.setConfirmPassword("Chai@1234");
        registrationDetailDTO.setUserType("R");
    }

    @Test
    public void testRegisterUserDetail() throws ParseException, AuthenticationException, UserExistException {

        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(false);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), "abcd")).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());

        registrationService.registerUserDetail(registrationDetailDTO);
        RegistrationDetail registrationDetail = registrationDetailArgumentCaptor.getValue();
        assertNotNull(registrationDetail);
        assertEquals(registrationDetailDTO.getEmailId(), registrationDetail.getUsername());
        assertEquals("securepasword", registrationDetail.getPassword());
    }

    @Test(expected = UserExistException.class)
    public void testRegisterUserDetailForUserExist() throws ParseException, AuthenticationException, UserExistException {
        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(true);
        Mockito.when(authenticationUtil.generateSecurePassword("Chai@1234", "Lpm28h5myQheIFNflzA7oaB1bFGSCn")).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());
        registrationService.registerUserDetail(registrationDetailDTO);
    }


    @Test(expected = AuthenticationException.class)
    public void testRegisterUserDetailForUserValidatePassword() throws ParseException, AuthenticationException, UserExistException {
        registrationDetailDTO.setConfirmPassword("Chai2@1234");
        Mockito.when(registrationDao.existByEmailID("chaitali@gmail.com")).thenReturn(false);
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq("Chai@1234"), Mockito.eq("Lpm28h5myQheIFNflzA7oaB1bFGSCn"))).thenReturn("securepasword");
        Mockito.doNothing().when(registrationDao).saveRegistrationDetail(registrationDetailArgumentCaptor.capture());
        registrationService.registerUserDetail(registrationDetailDTO);
    }


}