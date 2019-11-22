package com.jobportal.jobportalsystem.dao.login;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class LoginDetailDAO {
    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDetailDAO.class);

    public List<RegistrationDetail> getUserProfile(RegistrationDetail registrationDetail) {
        TypedQuery<RegistrationDetail> query = entityManager.createQuery("Select rd from RegistrationDetail rd where rd.username=:username",RegistrationDetail.class);
        query.setParameter("username",registrationDetail.getUsername());
        List<RegistrationDetail> userProfileEntity = query.getResultList();
        LOGGER.info("detail of user="+userProfileEntity);
        return userProfileEntity;
    }

    public void updateUserProfile(RegistrationDetail registrationDetail) {
      RegistrationDetail registrationDetail1=  entityManager.merge(registrationDetail);
      LOGGER.info("registrationDetail1=="+registrationDetail1);
    }
}
