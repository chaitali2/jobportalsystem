package com.jobportal.jobportalsystem.dto;

import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RegistrationDTO {

    @PersistenceContext
    EntityManager entityManager;


    public String registerRecruiterOrJobSeekerDetail(RegistrationDetail registrationDetail) {
        registrationDetail.setUsername(registrationDetail.getEmailid());

        Query query = entityManager.createQuery("Select d.emailid from RegistrationDetail d where d.emailid=:emailid");
        query.setParameter("emailid", registrationDetail.getEmailid());

        List<RegistrationDetail> user = query.getResultList();
        System.out.println("====>" + user);

        int size = user.size();
        if (size < 1) {
            entityManager.persist(registrationDetail);
            return "not_exist";
        }
        return "exist";
    }
}
