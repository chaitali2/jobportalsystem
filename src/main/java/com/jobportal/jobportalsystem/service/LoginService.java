package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dao.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import com.jobportal.jobportalsystem.rest.LoginRestService;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Autowired
    LoginDetailDAO loginDetailDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    public UserProfileDTO authenticate(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        LOGGER.info("loginDetailDTO==="+loginDetailDTO);
        List<RegistrationDetail> userProfileEntity = loginDetailDAO.getUserProfile(convertDTOtoModel(loginDetailDTO));
        RegistrationDetail userEntity = userProfileEntity.get(0);
        String secureUserPassword = null;
        try {
            secureUserPassword = authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), userEntity.getSalt());
        } catch (InvalidKeySpecException ex) {

        }

        boolean authenticated = false;
        if (secureUserPassword != null && secureUserPassword.equalsIgnoreCase(userEntity.getPassword())) {
            if (loginDetailDTO.getUsername() != null && loginDetailDTO.getUsername().equalsIgnoreCase(userEntity.getUsername())) {
                authenticated = true;
            }
        }
        LOGGER.info("authenticated===" + authenticated);
        if (!authenticated) {
            throw new AuthenticationException("Authentication failed");
        }
        UserProfileDTO userProfile = new UserProfileDTO();
//userProfile.setId(userProfileEntity.);
        BeanUtils.copyProperties(userEntity, userProfile);
        LOGGER.info("userEntity====>>" + userEntity);

        LOGGER.info("userProfile====>>" + userProfile);
        return userProfile;
    }

    RegistrationDetail convertDTOtoModel(LoginDetailDTO loginDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword(loginDetailDTO.getPassword());
        return registrationDetail;
    }
}
