package com.jobportal.jobportalsystem.service.registration;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Service
public class RegistrationService {

    @Autowired
    RegistrationDAO registrationDao;

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Autowired
    Utility utility;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    public void registerUserDetail(RegistrationDetailDTO registrationDetailDTO) throws UserExistException, AuthenticationException, ParseException {

        System.out.println("registration model==" + registrationDetailDTO);

        // FETCH DATA ON EMAIL ID
        RegistrationDetail registrationDetail1=convertDTOtoModel(registrationDetailDTO);
        boolean isExistEmail = registrationDao.existByEmailID(registrationDetail1.getEmailid());

        if (isExistEmail) {
            throw new UserExistException("Email ID is already exist");
        }

        Optional<String> validationMessage = validateConfirmPassword(registrationDetailDTO.getPassword(), registrationDetailDTO.getConfpassword());
        if (validationMessage.isPresent()) {
            throw new AuthenticationException(validationMessage.get());
        }
        String salt = authenticationUtil.generateSalt(30);
        String secureUserPassword = null;
        try {
            //GENERATE SECURE PASSWORD
            secureUserPassword = authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), salt);
        } catch (InvalidKeySpecException ex) {
            LOGGER.error("===========InvalidKeySpecException=============");
            ex.printStackTrace();
        }

        registrationDetailDTO.setPassword(secureUserPassword);
        // SAVE THE USER DETAIL
        RegistrationDetail registrationDetail = convertDTOtoModel(registrationDetailDTO);
        registrationDetail.setUsername(registrationDetail.getEmailid());
        registrationDetail.setSalt(salt);

        registrationDao.saveRegistrationDetail(registrationDetail);
    }

    public Optional<String> validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword))
            return Optional.of("Password and confirm password must be equal");
        return Optional.empty();
    }

    public RegistrationDetail convertDTOtoModel(RegistrationDetailDTO registrationDetailDTO) throws ParseException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstname(registrationDetailDTO.getFirstname());
        registrationDetail.setLastname(registrationDetailDTO.getLastname());
        registrationDetail.setEmailid(registrationDetailDTO.getEmailid());
        String dobDate = utility.changedateformatter(registrationDetailDTO.getDob(), "dd-MM-yyyy");
        registrationDetail.setDob(dobDate);
        registrationDetail.setPassword(registrationDetailDTO.getPassword());
        registrationDetail.setMobno(registrationDetailDTO.getMobno());
        registrationDetail.setUsertype(registrationDetailDTO.getUsertype());
        return registrationDetail;
    }

}
