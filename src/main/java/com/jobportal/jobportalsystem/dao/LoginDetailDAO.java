package com.jobportal.jobportalsystem.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class LoginDetailDAO {
    @PersistenceContext
    EntityManager entityManager;

    public void authenticate(String username, String password) {
//        Query query = entityManager.createQuery("Select d.emailid from RegistrationDetail d where d.emailid=:emailid");
//        List<RegistrationDetail> user = query.getResultList();
    }
}
