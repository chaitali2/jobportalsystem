package com.jobportal.jobportalsystem;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.SERIALIZABLE)
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void saveuserorderdetail() {
//        throw new RuntimeException("Rollback this transaction!");

        User user = new User("chaitali", "developer");
//        try {
            entityManager.persist(user);
        int a=10/0;

//        }catch (Exception e){
//            System.out.println("error in user==="+e.getMessage());
//        }
    }
}
