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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class LoginServiceTest {

    @Mock
    LoginDetailDAO loginDetailDAO;

    @Mock
    AuthenticationUtil authenticationUtil;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    LoginDetailDTO loginDetailDTO;

    @Autowired
    LoginService loginService;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        System.out.println("prepare data");
        loginDetailDTO = new LoginDetailDTO();
        loginDetailDTO.setUsername("chaitali@gmail.com");
        loginDetailDTO.setPassword("Chai@1234");
    }

    @Test(expected = AuthenticationException.class)
    public void testAuthenticate() throws InvalidKeySpecException, AuthenticationException {
        RegistrationDetail registrationDetail=new RegistrationDetail();
        System.out.println("registrationDetail=="+registrationDetail);
        Optional<RegistrationDetail> userprofile=Optional.of(registrationDetail);
        System.out.println("====>"+userprofile.isPresent());

        Mockito.when(loginDetailDAO.getUserProfile("chaitali@gmail.com")).thenReturn(Optional.of(registrationDetail));
        Mockito.when(authenticationUtil.generateSecurePassword("Chai@1234","1234")).thenReturn("AAAAA");
        Mockito.when(jwtTokenUtil.generateToken(loginDetailDTO)).thenReturn("token");

        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
        assertEquals(registrationDetail.getUsername(),userProfileDTO.getUsername());



    }

}