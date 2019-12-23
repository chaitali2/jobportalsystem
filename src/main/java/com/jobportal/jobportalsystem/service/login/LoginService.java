package com.jobportal.jobportalsystem.service.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dao.login.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.login.UserProfileDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private LoginDetailDAO loginDetailDAO;

    public UserProfileDTO authenticate(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        Optional<RegistrationDetail> userProfile = checkUserProfileExistOrNot(loginDetailDTO);
        authenticatePassword(loginDetailDTO, userProfile);
        RegistrationDetail registrationDetail = userProfile.get();
        UserProfileDTO userProfileDTO = convertModeltoDTO(registrationDetail);
        userProfileDTO.setToken(generateToken(loginDetailDTO));
        return userProfileDTO;
    }


    private Optional<RegistrationDetail> checkUserProfileExistOrNot(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        RegistrationDetail registrationDetailModel = convertDTOtoModel(loginDetailDTO);
        Optional<RegistrationDetail> userProfile = loginDetailDAO.getUserProfile(registrationDetailModel.getUsername());
        if (!userProfile.isPresent()) {
            throw new AuthenticationException("User does not exist.");
        }
        return userProfile;
    }

    private void authenticatePassword(LoginDetailDTO loginDetailDTO, Optional<RegistrationDetail> userProfile) throws AuthenticationException {
        boolean authenticated = false;
        if (userProfile.isPresent()) {
          String secureUserPassword = authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), userProfile.get().getSalt());
            if (secureUserPassword != null && secureUserPassword.equalsIgnoreCase(userProfile.get().getPassword())) {
                if (loginDetailDTO.getUsername() != null && loginDetailDTO.getUsername().equalsIgnoreCase(userProfile.get().getUsername())) {
                    authenticated = true;
                }
            }
        }
        if (!authenticated) {
            throw new AuthenticationException("Invalid username or password");
        }
    }

    private String generateToken(LoginDetailDTO loginDetailDTO) throws AuthenticationException {
        final String token = jwtTokenUtil.generateToken(loginDetailDTO);
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("Invalid username or password");
        }
        return token;
    }


    private RegistrationDetail convertDTOtoModel(LoginDetailDTO loginDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword(loginDetailDTO.getPassword());
        return registrationDetail;
    }

    private UserProfileDTO convertModeltoDTO(RegistrationDetail registrationDetail) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(registrationDetail.getId().toString());
        userProfileDTO.setUsername(registrationDetail.getUsername());
        userProfileDTO.setUserType(registrationDetail.getUserType().toString());
        userProfileDTO.setFirstName(registrationDetail.getFirstName());
        userProfileDTO.setLastName(registrationDetail.getLastName());
        return userProfileDTO;
    }
}
