package com.jobportal.jobportalsystem.service.login;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.dao.login.LoginDetailDAO;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.dto.UserProfileDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.rest.login.LoginRestService;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    AuthenticationUtil authenticationUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    LoginDetailDAO loginDetailDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);

    public UserProfileDTO authenticate(LoginDetailDTO loginDetailDTO) throws AuthenticationException, InvalidKeySpecException {
        RegistrationDetail registrationDetailModel = convertDTOtoModel(loginDetailDTO);
        Optional<RegistrationDetail> userProfile = loginDetailDAO.getUserProfile(registrationDetailModel.getUsername());
        boolean authenticated = false;

        if (!userProfile.isPresent())
            throw new AuthenticationException("User does not exist.");

        String  secureUserPassword = authenticationUtil.generateSecurePassword(loginDetailDTO.getPassword(), userProfile.get().getSalt());

        if (secureUserPassword != null && secureUserPassword.equalsIgnoreCase(userProfile.get().getPassword())) {
            if (loginDetailDTO.getUsername() != null && loginDetailDTO.getUsername().equalsIgnoreCase(userProfile.get().getUsername())) {
                authenticated = true;
            }
        }

        if (!authenticated) {
            throw new AuthenticationException("Invalid username or password");
        }

        RegistrationDetail registrationDetail = userProfile.get();
        UserProfileDTO userProfileDTO = convertModeltoDTO(registrationDetail);

        final String token = jwtTokenUtil.generateToken(loginDetailDTO);

        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("Invalid username or password");
        }
        userProfileDTO.setToken(token);
        return userProfileDTO;
    }

    RegistrationDetail convertDTOtoModel(LoginDetailDTO loginDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername(loginDetailDTO.getUsername());
        registrationDetail.setPassword(loginDetailDTO.getPassword());
        return registrationDetail;
    }

    UserProfileDTO convertModeltoDTO(RegistrationDetail registrationDetail) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(registrationDetail.getId().toString());
        userProfileDTO.setUsername(registrationDetail.getUsername());
        userProfileDTO.setUsertype(registrationDetail.getUsertype());
        userProfileDTO.setFirstname(registrationDetail.getFirstname());
        userProfileDTO.setLastname(registrationDetail.getLastname());
        return userProfileDTO;
    }
}
