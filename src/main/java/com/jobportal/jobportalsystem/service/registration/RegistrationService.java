package com.jobportal.jobportalsystem.service.registration;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.AuthenticationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.util.List;


@Service
public class RegistrationService {

    @Autowired
    RegistrationDAO registrationDao;

    @Autowired
    AuthenticationUtil authenticationUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    public void registerUserDetail(RegistrationDetailDTO registrationDetailDTO) throws Exception {

        LOGGER.info("registration model==" + registrationDetailDTO);
        // FETCH DATA ON EMAIL ID
        if (registrationDao.existByEmailID(convertDTOtoModel(registrationDetailDTO))) {
            throw new UserExistException("Email ID is already exist");
        }

        String salt = authenticationUtil.generateSalt(30);
        String secureUserPassword = null;
        try {
            //GENERATE SECURE PASSWORD
            secureUserPassword = authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), salt);
        } catch (InvalidKeySpecException ex) {
//                    LOGGER.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                    throw new UserServiceException(ex.getLocalizedMessage());
        }
        registrationDetailDTO.setPassword(secureUserPassword);
        // SAVE THE USER DETAIL
        RegistrationDetail registrationDetail=convertDTOtoModel(registrationDetailDTO);
        registrationDetail.setUsername(registrationDetail.getEmailid());
        registrationDetail.setSalt(salt);
        registrationDao.saveRegistrationDetail(registrationDetail);
    }


    public RegistrationDetail convertDTOtoModel(RegistrationDetailDTO registrationDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstname(registrationDetailDTO.getFirstname());
        registrationDetail.setLastname(registrationDetailDTO.getLastname());
        registrationDetail.setEmailid(registrationDetailDTO.getEmailid());
        registrationDetail.setDob(registrationDetailDTO.getDob());
        registrationDetail.setPassword(registrationDetailDTO.getPassword());
        registrationDetail.setMobno(registrationDetailDTO.getMobno());
        registrationDetail.setTypeOfUser(registrationDetailDTO.getTypeOfUser());
        return registrationDetail;
    }

}
