package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.dao.RegistrationDAO;
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
    public String registerUserDetail(RegistrationDetail registrationDetail) throws Exception {
//        RegistrationDetail registrationDetail=new RegistrationDetail();
//        LOGGER.info("registration dto=="+registrationDetailDTO);
//        BeanUtils.copyProperties(registrationDetailDTO,registrationDetail);
        LOGGER.info("registration model==" + registrationDetail);

        List<RegistrationDetail> user = registrationDao.fetchEmailId(registrationDetail);
        int size = user.size();

        if (size < 1) {
            String salt = authenticationUtil.generateSalt(30);
            registrationDetail.setSalt(salt);
            String secureUserPassword = null;

            try {
                secureUserPassword = authenticationUtil.generateSecurePassword(registrationDetail.getPassword(), salt);
            } catch (InvalidKeySpecException ex) {
//                    LOGGER.getLogger(UsersServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//                    throw new UserServiceException(ex.getLocalizedMessage());
            }

            LOGGER.info("encryptedPassword:--" + secureUserPassword + "===" + registrationDetail.getPassword());
            registrationDetail.setPassword(secureUserPassword);
            registrationDetail.setUsername(registrationDetail.getEmailid());
            registrationDao.saveRegistrationDetail(registrationDetail);
            return "not_exist";
        }

//            throw new UserExistException("User exist");
//        Response.status(200,"User exist");
        return "exist";
    }


//
//
//        public byte[] getNewSalt() throws Exception {
//        // Don't use Random!
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        // NIST recommends minimum 4 bytes. We use 8.
//        String SALT="my-salt-text";
//        byte[] salt = new byte[8];
//        random.nextBytes(salt);
//
//        return SALT.getBytes();
////        return Base64.getEncoder().encodeToString(salt);
//    }
//
//
//    // Get a encrypted password using PBKDF2 hash algorithm
//    public String getEncryptedPassword(String password, byte[] salt) throws Exception {
////        String algorithm = "PBKDF2WithHmacSHA1";
////        int derivedKeyLength = 160; // for SHA1
////        int iterations = 20000; // NIST specifies 10000
////
////        byte[] saltBytes = Base64.getDecoder().decode(salt);
////        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
////        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
////
////        byte[] encBytes = f.generateSecret(spec).getEncoded();
////        return Base64.getEncoder().encodeToString(encBytes);
//
//        String generatedPassword = null;
//        MessageDigest md = MessageDigest.getInstance("SHA-1"); // 160 bit hash algorithm
//        md.update(salt);
//        byte[] bytes = md.digest(password.getBytes());
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < bytes.length; i++) {
//            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//        }
//        generatedPassword = sb.toString();
//        return generatedPassword;
//    }
}
