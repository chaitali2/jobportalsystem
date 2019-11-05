package com.jobportal.jobportalsystem.dto;

import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;

@Repository
@Transactional
public class RegistrationDTO {

    @PersistenceContext
    EntityManager entityManager;


    public String registerRecruiterOrJobSeekerDetail(RegistrationDetail registrationDetail) throws Exception {
        registrationDetail.setUsername(registrationDetail.getEmailid());

        Query query = entityManager.createQuery("Select d.emailid from RegistrationDetail d where d.emailid=:emailid");
        query.setParameter("emailid", registrationDetail.getEmailid());

        List<RegistrationDetail> user = query.getResultList();
        System.out.println("====>" + user);

        int size = user.size();
        if (size < 1) {
            byte[] salt = getNewSalt();
            String encryptedPassword = getEncryptedPassword(registrationDetail.getPassword(), salt);
            registrationDetail.setPassword(encryptedPassword);
            entityManager.persist(registrationDetail);
            return "not_exist";
        }
        return "exist";
    }

    public byte[] getNewSalt() throws Exception {
        // Don't use Random!
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // NIST recommends minimum 4 bytes. We use 8.
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
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
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }
}
