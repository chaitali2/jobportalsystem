package com.jobportal.jobportalsystem.service.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;

import com.jobportal.jobportalsystem.dao.login.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.login.UserProfileDTO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Mock
    private AuthenticationUtil authenticationUtil;
    @Mock
    private LoginDetailDAO loginDetailDAO;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    private LoginDetailDTO loginDetailDTO;
    @InjectMocks
    private LoginService loginService;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        loginDetailDTO = new LoginDetailDTO();
        loginDetailDTO.setUsername("chaitali@gmail.com");
        loginDetailDTO.setPassword("securepassword");
    }

    @Test
    public void testAuthenticate() throws AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword("securepassword");
        registrationDetail.setId(1L);
        registrationDetail.setUserType(RegistrationDetail.user.R);
        registrationDetail.setFirstName("chaitali");
        registrationDetail.setFirstName("khachane");
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.anyString(), Mockito.any())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("token");
        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
        assertNotNull(userProfileDTO);
        assertEquals(registrationDetail.getUsername(), userProfileDTO.getUsername());
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticateUserProfileNotPresent() throws AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), Optional.of(registrationDetail).get().getSalt())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("token");
        loginService.authenticate(loginDetailDTO);
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticateTokenEmpty() throws  AuthenticationException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), Optional.of(registrationDetail).get().getSalt())).thenReturn("securepassword");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("");
        loginService.authenticate(loginDetailDTO);
    }

}