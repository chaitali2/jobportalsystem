package com.jobportal.jobportalsystem.service.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;

import com.jobportal.jobportalsystem.dao.login.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LoginServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceTest.class);

    @Captor
    ArgumentCaptor<RegistrationDetail> registrationDetailArgumentCaptor;

    @Mock
    AuthenticationUtil authenticationUtil;
    @Mock
    LoginDetailDAO loginDetailDAO;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    LoginDetailDTO loginDetailDTO;

    @InjectMocks
    LoginService loginService;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        System.out.println("prepare data");
        loginDetailDTO = new LoginDetailDTO();
        loginDetailDTO.setUsername("chaitali@gmail.com");
        loginDetailDTO.setPassword("securepassword");
    }

    @Test
    public void testAuthenticate() throws InvalidKeySpecException, AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword("securepassword");
        registrationDetail.setId(1l);
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), Optional.of(registrationDetail).get().getSalt())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("token");
        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
        assertEquals(registrationDetail.getUsername(), userProfileDTO.getUsername());
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticateUserProfileNotPresent() throws InvalidKeySpecException, AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), Optional.of(registrationDetail).get().getSalt())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("token");
        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticateTokenEmpty() throws InvalidKeySpecException, AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), Optional.of(registrationDetail).get().getSalt())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("");
        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
    }

}