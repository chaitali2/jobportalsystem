package com.jobportal.jobportalsystem.service.registration;

import com.jobportal.jobportalsystem.customizedException.AuthenticationException;
import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import com.jobportal.jobportalsystem.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationDAO registrationDao;
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private Utility utility;

    public void registerUserDetail(RegistrationDetailDTO registrationDetailDTO) throws UserExistException, AuthenticationException, ParseException {
        checkExistUser(registrationDetailDTO);
        validateConfirmPassword(registrationDetailDTO.getPassword(), registrationDetailDTO.getConfirmPassword());

        String salt = authenticationUtil.generateSalt(30);
        String secureUserPassword = authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), salt);
        registrationDetailDTO.setPassword(secureUserPassword);

        RegistrationDetail registrationDetail = convertDTOtoModel(registrationDetailDTO);
        registrationDetail.setUsername(registrationDetail.getEmailId());
        registrationDetail.setSalt(salt);
        registrationDetail.setPassword(secureUserPassword);
        registrationDao.saveRegistrationDetail(registrationDetail);
    }

    private void validateConfirmPassword(String password, String confirmPassword) throws AuthenticationException {
        if (!password.equals(confirmPassword)) {
            throw new AuthenticationException("Password and confirm password must be equal");
        }
    }

    private void checkExistUser(RegistrationDetailDTO registrationDetailDTO) throws ParseException, UserExistException {
        RegistrationDetail registrationDetail1 = convertDTOtoModel(registrationDetailDTO);
        boolean isExistEmail = registrationDao.existByEmailID(registrationDetail1.getEmailId());
        if (isExistEmail) {
            throw new UserExistException("Email ID is already exist");
        }
    }

    private RegistrationDetail convertDTOtoModel(RegistrationDetailDTO registrationDetailDTO) throws ParseException {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName(registrationDetailDTO.getFirstName());
        registrationDetail.setLastName(registrationDetailDTO.getLastName());
        registrationDetail.setEmailId(registrationDetailDTO.getEmailId());
        String dobDate = utility.changeDateFormatter(registrationDetailDTO.getDateOfBirth(), "dd-MM-yyyy");
        registrationDetail.setDateOfBirth(dobDate);
        registrationDetail.setPassword(registrationDetailDTO.getPassword());
        registrationDetail.setMobileNo(registrationDetailDTO.getMobileNo());
        registrationDetail.setUserType(registrationDetailDTO.getUserType());
        return registrationDetail;
    }

}
