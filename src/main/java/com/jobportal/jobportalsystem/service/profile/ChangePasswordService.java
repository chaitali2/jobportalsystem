package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.PasswordDoesNotExistException;
import com.jobportal.jobportalsystem.dao.profile.ChangePasswordDAO;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.service.registration.RegistrationService;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class ChangePasswordService {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    ChangePasswordDAO changePasswordDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordService.class);

    public void changePassword(PasswordDTO passwordDTO) throws InvalidKeySpecException, PasswordDoesNotExistException, AuthenticationException {
        Optional<String> isMatch = checkOldPassword(passwordDTO);

        if (isMatch.isPresent()) {
            LOGGER.info("old not exist");
            throw new PasswordDoesNotExistException(isMatch.get());
        } else {
            LOGGER.info("old exist");
            Optional<String> validationMessage = validateConfirmPassword(passwordDTO.getNew_password(), passwordDTO.getConfirm_password());
            if (validationMessage.isPresent()) {
                throw new AuthenticationException(validationMessage.get());
            } else {
                RegistrationDetail registrationDetail = new RegistrationDetail();
                registrationDetail.setUsername(passwordDTO.getUsername());
                String salt = authenticationUtil.generateSalt(30);
                String newPassword = authenticationUtil.generateSecurePassword(passwordDTO.getNew_password(), salt);
                LOGGER.info("newPassword=="+newPassword);
                registrationDetail.setPassword(newPassword);
                registrationDetail.setSalt(salt);
                changePasswordDAO.updatePassword(registrationDetail);
            }
        }
    }

    public Optional<String> validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            return Optional.of("New Password and Confirm Password must be equal");
        return Optional.empty();
    }

    Optional<String> checkOldPassword(PasswordDTO passwordDTO) throws InvalidKeySpecException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(passwordDTO.getUsername());
        List<Object[]> passwordDetail = changePasswordDAO.fetchPasswordFromUser(passwordDTO.getUsername());
        String salt = passwordDetail.get(0)[1].toString();
        String oldPassword = authenticationUtil.generateSecurePassword(passwordDTO.getOld_password(), salt);
        LOGGER.info("oldPassword=="+oldPassword);
        String existPassword = passwordDetail.get(0)[0].toString();
        if (existPassword.equals(oldPassword)) {
            return Optional.empty();
        }
        return Optional.of("Old Password doesn't exist");

    }
}
