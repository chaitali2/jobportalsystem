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
import java.util.Optional;

@Repository
@Transactional
public class LoginDetailDAO {
    @PersistenceContext
    EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDetailDAO.class);

    public Optional<RegistrationDetail> getUserProfile(String username) {
        TypedQuery<RegistrationDetail> query = entityManager.createQuery("Select rd from RegistrationDetail rd " +
                                                                            "where rd.username=:username",RegistrationDetail.class);

        query.setParameter("username",username);

        List<RegistrationDetail> resultList = query.getResultList();
        if (resultList.size() == 0)
            return Optional.empty();
        return Optional.of(resultList.get(0));
    }

}
