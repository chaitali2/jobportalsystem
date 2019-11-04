package com.jobportal.jobportalsystem.dto;

import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RegistrationDTO {

    @PersistenceContext
    EntityManager entityManager;

    public String registerRecruiterOrJobSeekerDetail(RegistrationDetail registrationDetail) {
        entityManager.persist(registrationDetail);
        return "Hello Chaitali";
    }
}
