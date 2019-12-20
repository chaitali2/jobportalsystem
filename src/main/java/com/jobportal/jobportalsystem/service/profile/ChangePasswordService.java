package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dao.profile.ChangePasswordDAO;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class ChangePasswordService {

    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private ChangePasswordDAO changePasswordDAO;

    public void changePassword(PasswordDTO passwordDTO) throws InvalidKeySpecException, PasswordDoesNotExistException, AuthenticationException {
        Optional<String> isMatch = checkOldPassword(passwordDTO);
        if (isMatch.isPresent()) {
            throw new PasswordDoesNotExistException(isMatch.get());
        } else {
            validateConfirmPassword(passwordDTO.getNewPassword(), passwordDTO.getConfirmPassword());
            RegistrationDetail registrationDetail = new RegistrationDetail();
            registrationDetail.setUsername(passwordDTO.getUsername());
            String salt = authenticationUtil.generateSalt(30);
            String newPassword = authenticationUtil.generateSecurePassword(passwordDTO.getNewPassword(), salt);
            registrationDetail.setPassword(newPassword);
            registrationDetail.setSalt(salt);
            changePasswordDAO.updatePassword(registrationDetail);
        }
    }

    private void validateConfirmPassword(String password, String confirmPassword) throws AuthenticationException {
        if (!password.equals(confirmPassword)) {
            throw new AuthenticationException("New Password and Confirm Password must be equal");
        }
    }

    Optional<String> checkOldPassword(PasswordDTO passwordDTO) throws InvalidKeySpecException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(passwordDTO.getUsername());
        List<Object[]> passwordDetail = changePasswordDAO.fetchPasswordFromUser(passwordDTO.getUsername());
        String salt = passwordDetail.get(0)[1].toString();
        String oldPassword = authenticationUtil.generateSecurePassword(passwordDTO.getOldPassword(), salt);
        String existPassword = passwordDetail.get(0)[0].toString();
        if (existPassword.equals(oldPassword)) {
            return Optional.empty();
        }
        return Optional.of("Old Password doesn't exist");
    }
}
