package com.jobportal.jobportalsystem.dao.login;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginDetailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<RegistrationDetail> getUserProfile(String username) {
        TypedQuery<RegistrationDetail> query = entityManager.createQuery("Select registrationDetail " +
                                                                        "from RegistrationDetail registrationDetail " +
                                                                        "where registrationDetail.username=:username",
                                                                         RegistrationDetail.class);
        query.setParameter("username",username);
        List<RegistrationDetail> resultList = query.getResultList();
        if (resultList.isEmpty())
            return Optional.empty();
        return Optional.of(resultList.get(0));
    }

}
