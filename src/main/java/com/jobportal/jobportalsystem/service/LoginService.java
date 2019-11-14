package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.CustomizedException.AuthenticationException;
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
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Autowired
    LoginDetailDAO loginDetailDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    public UserProfileDTO authenticate(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
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


    public String issueSecureToken(UserProfileDTO userProfile) throws AuthenticationException {
        String returnValue = null;
        // Get salt but only part of it
        String newSaltAsPostfix = userProfile.getSalt();
        String accessTokenMaterial = userProfile.getId() + newSaltAsPostfix;
        byte[] encryptedAccessToken = null;
        try {
            encryptedAccessToken = authenticationUtil.encrypt(userProfile.getPassword(), accessTokenMaterial);
        } catch (InvalidKeySpecException ex) {
//            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AuthenticationException("Failed to issue secure access token");
        }
        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
        // Split token into equal parts
        int tokenLength = encryptedAccessTokenBase64Encoded.length();
        String tokenToSaveToDatabase = encryptedAccessTokenBase64Encoded.substring(0, tokenLength / 2);
        returnValue = encryptedAccessTokenBase64Encoded.substring(tokenLength / 2, tokenLength);
        userProfile.setToken(tokenToSaveToDatabase);
        storeAccessToken(userProfile);
        return returnValue;
    }

    private void storeAccessToken(UserProfileDTO userProfile) {
        RegistrationDetail userEntity = new RegistrationDetail();
        BeanUtils.copyProperties(userProfile, userEntity);
        // Connect to database
        try {
            LOGGER.info("userEntity==" + userEntity);
            LOGGER.info("userProfile==" + userProfile);
//            this.database.openConnection();
            loginDetailDAO.updateUserProfile(userEntity);
        } finally {
//            this.database.closeConnection();
        }
    }


    @Transactional
    public UserProfileDTO resetSecurityCridentials(String password,
                                                   UserProfileDTO userProfile) {

        LOGGER.info("re isssue userProfile 1==" + userProfile);

        // Generate salt
        String salt = authenticationUtil.generateSalt(30);
        // Generate secure user password
        String secureUserPassword = null;
        try {
            secureUserPassword = authenticationUtil.
                    generateSecurePassword(password, salt);
        } catch (InvalidKeySpecException ex) {
//            Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            throw new UserServiceException(ex.getLocalizedMessage());
        }
        userProfile.setSalt(salt);
        userProfile.setPassword(secureUserPassword);
        RegistrationDetail userEntity = new RegistrationDetail();
        BeanUtils.copyProperties(userProfile, userEntity);
        // Connect to database
        try {
            LOGGER.info("re issue userEntity==" + userEntity);
            LOGGER.info("re isssue userProfile==" + userProfile);

//            this.database.openConnection();
            loginDetailDAO.updateUserProfile(userEntity);
        } finally {
//            this.database.closeConnection();
        }
        return userProfile;
    }

    //    public LoginDetailDTO resetSecurityCridentials(String password,
//                                                   LoginDetail userProfile) {
//        // Generate salt
//        String salt = authenticationUtil.generateSalt(30);
//        // Generate secure user password
//        String secureUserPassword = null;
//        try {
//            secureUserPassword = authenticationUtil.
//                    generateSecurePassword(password, salt);
//        } catch (InvalidKeySpecException ex) {
////            Logger.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
////            throw new UserServiceException(ex.getLocalizedMessage());
//        }
//        userProfile.setSalt(salt);
//        userProfile.setPassword(secureUserPassword);
//        UserProfileEntity userEntity = new UserProfileEntity();
//        BeanUtils.copyProperties(userProfile, userEntity);
//        // Connect to database
//        try {
//            this.database.openConnection();
//            this.database.updateUserProfile(userEntity);
//        } finally {
//            this.database.closeConnection();
//        }
//        return userProfile;
//    }
    RegistrationDetail convertDTOtoModel(LoginDetailDTO loginDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword(loginDetailDTO.getPassword());
        return registrationDetail;
    }
}
