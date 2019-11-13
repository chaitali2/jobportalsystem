package com.jobportal.jobportalsystem.service;

import com.jobportal.jobportalsystem.CustomizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.RegistrationDao;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;


@Service
public class RegistrationService {

    @Autowired
    RegistrationDao registrationDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public String registerUserDetail(RegistrationDetail registrationDetail) throws Exception {
//        RegistrationDetail registrationDetail=new RegistrationDetail();
//        LOGGER.info("registration dto=="+registrationDetailDTO);
//        BeanUtils.copyProperties(registrationDetailDTO,registrationDetail);
        LOGGER.info("registration model=="+registrationDetail);

            List<RegistrationDetail> user = registrationDao.fetchEmailId(registrationDetail);
            int size = user.size();

            if (size < 1) {
                byte[] salt = getNewSalt();
                String encryptedPassword = getEncryptedPassword(registrationDetail.getPassword(), salt);
                LOGGER.info("encryptedPassword:--"+encryptedPassword+"==="+registrationDetail.getPassword());
                registrationDetail.setPassword(encryptedPassword);
                registrationDetail.setUsername(registrationDetail.getEmailid());
                registrationDao.saveRegistrationDetail(registrationDetail);
                return "not_exist";
            }

//            throw new UserExistException("User exist");
//        Response.status(200,"User exist");
        return "exist";
    }


    public byte[] getNewSalt() throws Exception {
        // Don't use Random!
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // NIST recommends minimum 4 bytes. We use 8.
        String SALT="my-salt-text";
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return SALT.getBytes();
//        return Base64.getEncoder().encodeToString(salt);
    }


    // Get a encrypted password using PBKDF2 hash algorithm
    public String getEncryptedPassword(String password, byte[] salt) throws Exception {
//        String algorithm = "PBKDF2WithHmacSHA1";
//        int derivedKeyLength = 160; // for SHA1
//        int iterations = 20000; // NIST specifies 10000
//
//        byte[] saltBytes = Base64.getDecoder().decode(salt);
//        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
//        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
//
//        byte[] encBytes = f.generateSecret(spec).getEncoded();
//        return Base64.getEncoder().encodeToString(encBytes);

        String generatedPassword = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1"); // 160 bit hash algorithm
        md.update(salt);
        byte[] bytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }
}
