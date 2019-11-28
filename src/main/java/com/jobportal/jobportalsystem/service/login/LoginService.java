package com.jobportal.jobportalsystem.service.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dao.login.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.rest.login.LoginRestService;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Autowired
    LoginDetailDAO loginDetailDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);

    public UserProfileDTO authenticate(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        LOGGER.info("loginDetailDTO==="+loginDetailDTO);
        Optional<RegistrationDetail> userProfile = loginDetailDAO.getUserProfile(convertDTOtoModel(loginDetailDTO));

        if (!userProfile.isPresent())
            throw new AuthenticationException("User does not exist.");


        String secureUserPassword = null;
        try {
            secureUserPassword = authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), userProfile.get().getSalt());
        } catch (InvalidKeySpecException ex) {

        }

        boolean authenticated = false;
        if (secureUserPassword != null && secureUserPassword.equalsIgnoreCase(userProfile.get().getPassword())) {
            if (loginDetailDTO.getUsername() != null && loginDetailDTO.getUsername().equalsIgnoreCase(userProfile.get().getUsername())) {
                authenticated = true;
            }
        }

        LOGGER.info("authenticated===" + authenticated);

        if (!authenticated) {
            throw new AuthenticationException("Authentication failed");
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        RegistrationDetail registrationDetail=userProfile.get();
        userProfileDTO=convertDTOtoModel(registrationDetail);
        LOGGER.info("userEntity====>>" + registrationDetail);
        LOGGER.info("userProfile====>>" + userProfile);
        return userProfileDTO;
    }

    RegistrationDetail convertDTOtoModel(LoginDetailDTO loginDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword(loginDetailDTO.getPassword());
        return registrationDetail;
    }


    UserProfileDTO convertDTOtoModel(RegistrationDetail registrationDetail) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(registrationDetail.getId().toString());
        userProfileDTO.setUsername(registrationDetail.getUsername());
        userProfileDTO.setTypeOfUser(registrationDetail.getTypeOfUser());
        return userProfileDTO;
    }
}
