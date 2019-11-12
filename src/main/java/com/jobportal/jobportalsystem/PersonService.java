package com.jobportal.jobportalsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
public class PersonService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PersonRepo personRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Transactional
    public void callTransactionalMethodsWithTrasaction() {
        LOGGER.info("================ callTransactionalMethodsWithTrasaction() ========================");
        try {
            Person person1 = new Person();
            person1.setFirstName("Person1");
            person1.setLastName("Person1");
            person1.setEmail("email1");
            entityManager.persist(person1);
        } catch (Exception e) {
            LOGGER.error("Error in callTransactionalMethodsWithTrasaction() : {}", e.getClass());
        }
//        personRepo.required();
//        personRepo.requiresNew();
//        personRepo.nested();
//        personRepo.never();
//        personRepo.supports();
//        personRepo.notSupported();
//        personRepo.nested();
        personRepo.mandatory();
    }



}
