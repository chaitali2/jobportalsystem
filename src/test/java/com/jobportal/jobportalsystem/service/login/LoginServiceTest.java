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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

class LoginServiceTest {

    @MockBean
    LoginDetailDAO loginDetailDAO;

    @MockBean
    AuthenticationUtil authenticationUtil;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    LoginDetailDTO loginDetailDTO;

    @Autowired
    LoginService loginService;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        loginDetailDTO = new LoginDetailDTO();
        loginDetailDTO.setUsername("chaitali@gmail.com");
        loginDetailDTO.setPassword("Chai@1234");
    }

    @Test
    public void TestAuthenticate() throws AuthenticationException {
        RegistrationDetail registrationDetail;
        registrationDetail = loginService.convertDTOtoModel(loginDetailDTO);

        Mockito.when(loginDetailDAO.getUserProfile(registrationDetail)).thenReturn(Optional.of(registrationDetail));
//        Mockito.when(authenticationUtil.generateSecurePassword(registrationDetail)).thenReturn(Optional.of(registrationDetail));

        UserProfileDTO userProfileDTO = loginService.authenticate(loginDetailDTO);
        assertEquals(userProfileDTO.getUsername(), loginDetailDTO.getUsername());
    }
}