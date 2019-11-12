package com.jobportal.jobportalsystem;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

//    @Transactional(propagation = Propagation.MANDATORY)
    public void saveuserorderdetail() {
//        throw new RuntimeException("Rollback this transaction!");

        User user = new User("chaitali", "developer");
//        try {
            entityManager.persist(user);

//        User user=  entityManager.find(User.class,44l);
//        System.out.println("user detail=="+user);
//        int a=10/0;

//        }catch (Exception e){
//            System.out.println("error in user==="+e.getMessage());
//        }
    }
}
