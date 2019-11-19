package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.dao.RegistrationDAO;
import com.jobportal.jobportalsystem.dto.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public String registerUserDetail(RegistrationDetailDTO registrationDetailDTO) throws Exception {

        LOGGER.info("registration model==" + registrationDetailDTO);
        // FETCH DATA ON EMAIL ID
        List<RegistrationDetail> user = registrationDao.fetchEmailId(convertDTOtoModel(registrationDetailDTO));

        int size = user.size();

        //CHECK DATA EXIST OR NOT
        if (size < 1) {
            String salt = authenticationUtil.generateSalt(30);
            String secureUserPassword = null;
            try {
                //GENERATE SECURE PASSWORD
                secureUserPassword = authenticationUtil.generateSecurePassword(registrationDetailDTO.getPassword(), salt);
            } catch (InvalidKeySpecException ex) {
//                    LOGGER.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                    throw new UserServiceException(ex.getLocalizedMessage());
            }

            LOGGER.info("encryptedPassword:--" + secureUserPassword + "===" + registrationDetailDTO.getPassword());
            registrationDetailDTO.setPassword(secureUserPassword);
            // SAVE THE USER DETAIL
            registrationDao.saveRegistrationDetail(convertDTOtoModel(registrationDetailDTO),salt);

            return "not_exist";
        }
        return "exist";
    }


    RegistrationDetail convertDTOtoModel(RegistrationDetailDTO registrationDetailDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstname(registrationDetailDTO.getFirstname());
        registrationDetail.setLastname(registrationDetailDTO.getLastname());
        registrationDetail.setEmailid(registrationDetailDTO.getEmailid());
        registrationDetail.setDob(registrationDetailDTO.getDob());
        registrationDetail.setPassword(registrationDetailDTO.getPassword());
        registrationDetail.setCity(registrationDetailDTO.getCity());
        registrationDetail.setState(registrationDetailDTO.getState());
        registrationDetail.setMobno(registrationDetailDTO.getMobno());
        registrationDetail.setTypeOfUser(registrationDetailDTO.getTypeOfUser());
        return registrationDetail;
    }

}
