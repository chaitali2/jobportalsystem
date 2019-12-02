package com.jobportal.jobportalsystem.dao.registration;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RegistrationDAO {

    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationDAO.class);

    public Boolean existByEmailID(RegistrationDetail registrationDetail) {
        Query query = entityManager.createQuery("Select d.emailid " +
                                                    "from RegistrationDetail d " +
                                                    "where d.emailid=:emailid");

        query.setParameter("emailid", registrationDetail.getEmailid());

        return query.getResultList().size() == 1;
    }


    @Transactional
    public void saveRegistrationDetail(RegistrationDetail registrationDetail) {
        entityManager.persist(registrationDetail);
    }


}
