package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dao.profile.ChangePasswordDAO;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SCREENPeer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ChangePasswordServiceTest {
    @Mock
    AuthenticationUtil authenticationUtil;
    @Mock
    ChangePasswordDAO changePasswordDAO;

    @Captor
    ArgumentCaptor<RegistrationDetail> registrationDetailArgumentCaptor ;

    @InjectMocks
    ChangePasswordService changePasswordService;
    PasswordDTO passwordDTO;
    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        passwordDTO = new PasswordDTO();
        passwordDTO.setUsername("chaitali@gmail.com");
        passwordDTO.setNew_password("Hash@1234");
        passwordDTO.setOld_password("Hash@12345");
        passwordDTO.setConfirm_password("Hash@1234");
    }


    @Test
    public void testChangePassword() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        Object[] existing = {"securePass_old","asdf"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getNew_password()),Mockito.eq("abcd"))).thenReturn("securePass");
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.doNothing().when(changePasswordDAO).updatePassword(registrationDetailArgumentCaptor.capture());
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOld_password()),Mockito.eq("asdf"))).thenReturn("securePass_old");
        changePasswordService.changePassword(passwordDTO);
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSalt(30);
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSecurePassword(Mockito.eq(passwordDTO.getNew_password()),Mockito.eq("abcd"));
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSecurePassword(Mockito.eq(passwordDTO.getOld_password()),Mockito.eq("asdf"));
        RegistrationDetail registrationDetail = registrationDetailArgumentCaptor.getValue();
        assertNotNull(registrationDetail);
        assertEquals(passwordDTO.getUsername(),registrationDetail.getUsername());
        assertEquals("securePass",registrationDetail.getPassword());
        assertEquals("abcd",registrationDetail.getSalt());

    }

    @Test(expected =  PasswordDoesNotExistException.class)
    public void testChangePasswordWithOldPassDoesnotExists() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        Object[] existing = {"securePass_old","asdf"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getNew_password()),Mockito.eq("abcd"))).thenReturn("securePass");
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.doNothing().when(changePasswordDAO).updatePassword(registrationDetailArgumentCaptor.capture());
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOld_password()),Mockito.eq("asdf"))).thenReturn("");
        changePasswordService.changePassword(passwordDTO);

    }

    @Test(expected = AuthenticationException.class)
    public void newPasswordAndConfirmPasswordDoesnotMatch() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        passwordDTO.setConfirm_password("asdfcksdjdk");
        Object[] existing = {"securePass_old","asdf"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOld_password()),Mockito.eq("asdf"))).thenReturn("securePass_old");
        changePasswordService.changePassword(passwordDTO);
    }
}