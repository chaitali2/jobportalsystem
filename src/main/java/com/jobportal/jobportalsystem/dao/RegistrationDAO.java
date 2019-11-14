package com.jobportal.jobportalsystem.dao;

import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;

@Repository
public class RegistrationDAO {
    @PersistenceContext
    EntityManager entityManager;

    public List<RegistrationDetail> fetchEmailId(RegistrationDetail registrationDetail) {
        Query query = entityManager.createQuery("Select d.emailid from RegistrationDetail d where d.emailid=:emailid");
        query.setParameter("emailid", registrationDetail.getEmailid());
        List<RegistrationDetail> user = query.getResultList();
        System.out.println("====>" + user);
        return user;
    }

    public void saveRegistrationDetail(RegistrationDetail registrationDetail) {
        entityManager.persist(registrationDetail);
    }


}
