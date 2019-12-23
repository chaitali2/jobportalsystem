package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dao.profile.ChangePasswordDAO;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangePasswordServiceTest {
    @Mock
    private AuthenticationUtil authenticationUtil;
    @Mock
    private ChangePasswordDAO changePasswordDAO;
    @Captor
    private ArgumentCaptor<RegistrationDetail> registrationDetailArgumentCaptor ;
    @InjectMocks
    private ChangePasswordService changePasswordService;
    private PasswordDTO passwordDTO;
    @Before
    public void setUp() {
        prepareData();
    }

    private void prepareData() {
        passwordDTO = new PasswordDTO();
        passwordDTO.setUsername("chaitali@gmail.com");
        passwordDTO.setNewPassword("Hash@1234");
        passwordDTO.setOldPassword("Hash@12345");
        passwordDTO.setConfirmPassword("Hash@1234");
    }


    @Test
    public void testChangePassword() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        Object[] existing = {"securePass_old","xyz"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getNewPassword()),Mockito.eq("abcd"))).thenReturn("securePass");
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.doNothing().when(changePasswordDAO).updatePassword(registrationDetailArgumentCaptor.capture());
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOldPassword()),Mockito.eq("xyz"))).thenReturn("securePass_old");
        changePasswordService.changePassword(passwordDTO);
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSalt(30);
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSecurePassword(Mockito.eq(passwordDTO.getNewPassword()),Mockito.eq("abcd"));
        Mockito.verify(authenticationUtil,Mockito.times(1)).generateSecurePassword(Mockito.eq(passwordDTO.getOldPassword()),Mockito.eq("xyz"));
        RegistrationDetail registrationDetail = registrationDetailArgumentCaptor.getValue();
        assertNotNull(registrationDetail);
        assertEquals(passwordDTO.getUsername(),registrationDetail.getUsername());
        assertEquals("securePass",registrationDetail.getPassword());
        assertEquals("abcd",registrationDetail.getSalt());

    }

    @Test(expected =  PasswordDoesNotExistException.class)
    public void testChangePasswordWithOldPassDoesnotExists() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        Object[] existing = {"securePass_old","xyz"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(authenticationUtil.generateSalt(30)).thenReturn("abcd");
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getNewPassword()),Mockito.eq("abcd"))).thenReturn("securePass");
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.doNothing().when(changePasswordDAO).updatePassword(registrationDetailArgumentCaptor.capture());
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOldPassword()),Mockito.eq("xyz"))).thenReturn("");
        changePasswordService.changePassword(passwordDTO);

    }

    @Test(expected = AuthenticationException.class)
    public void newPasswordAndConfirmPasswordDoesnotMatch() throws InvalidKeySpecException, AuthenticationException, PasswordDoesNotExistException {
        passwordDTO.setConfirmPassword("asdfcksdjdk");
        Object[] existing = {"securePass_old","xyz"};
        List<Object[]> existingList = new ArrayList<>();
        existingList.add(existing);
        Mockito.when(changePasswordDAO.fetchPasswordFromUser(Mockito.eq(passwordDTO.getUsername()))).thenReturn(existingList);
        Mockito.when(authenticationUtil.generateSecurePassword(Mockito.eq(passwordDTO.getOldPassword()),Mockito.eq("xyz"))).thenReturn("securePass_old");
        changePasswordService.changePassword(passwordDTO);
    }
}