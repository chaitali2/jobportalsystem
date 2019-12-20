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
        Query query = entityManager.createQuery("select registrationDetail.password,registrationDetail.salt" +
                " from RegistrationDetail registrationDetail where registrationDetail.username=:username");
        query.setParameter("username", username);
        List<Object[]> passwordDetail = query.getResultList();
        return passwordDetail;
    }

    @Transactional
    public void updatePassword(RegistrationDetail registrationDetail) {
        TypedQuery<RegistrationDetail> query = entityManager.createQuery("select registrationDetail " +
                "from RegistrationDetail registrationDetail where registrationDetail.username=:username",
                RegistrationDetail.class);
        query.setParameter("username", registrationDetail.getUsername());
        RegistrationDetail userdetail = query.getSingleResult();
        userdetail.setPassword(registrationDetail.getPassword());
        userdetail.setSalt(registrationDetail.getSalt());
        entityManager.merge(userdetail);
    }
}
