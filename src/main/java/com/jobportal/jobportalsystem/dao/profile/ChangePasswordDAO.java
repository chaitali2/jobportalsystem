package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.service.profile.ChangePasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ChangePasswordDAO {

    @PersistenceContext
    EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangePasswordDAO.class);

    public List<Object[]> fetchPasswordFromUser(String username) {
        Query query = entityManager.createQuery("select rd.password,rd.salt from RegistrationDetail rd where rd.username=:username");
        query.setParameter("username", username);
        List<Object[]> passwordDetail = query.getResultList();
        return passwordDetail;
    }

    @Transactional
    public void updatePassword(RegistrationDetail registrationDetail) {
        TypedQuery<RegistrationDetail> query = entityManager.createQuery("select rd from RegistrationDetail rd where rd.username=:username", RegistrationDetail.class);
        query.setParameter("username", registrationDetail.getUsername());
        RegistrationDetail userdetail = query.getSingleResult();
        LOGGER.info("ChangePasswordDAO==" + userdetail);
        userdetail.setPassword(registrationDetail.getPassword());
        userdetail.setSalt(registrationDetail.getSalt());
        LOGGER.info("ChangePasswordDAO==" + userdetail);
        entityManager.merge(userdetail);
    }
}
