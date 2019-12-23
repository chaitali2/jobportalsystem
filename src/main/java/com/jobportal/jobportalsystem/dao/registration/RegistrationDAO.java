package com.jobportal.jobportalsystem.dao.registration;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RegistrationDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Boolean existByEmailID(String emailID) {
        Query query = entityManager.createQuery("Select registrationDetail.emailId " +
                                                    "from RegistrationDetail registrationDetail " +
                                                    "where registrationDetail.emailId=:emailId");

        query.setParameter("emailId", emailID);
        return query.getResultList().size() == 1;
    }

    @Transactional
    public void saveRegistrationDetail(RegistrationDetail registrationDetail) {
        entityManager.persist(registrationDetail);
    }
}
