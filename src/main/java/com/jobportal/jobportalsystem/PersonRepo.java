package com.jobportal.jobportalsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class PersonRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepo.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() {
        try {
            Person person2 = new Person();
            person2.setFirstName("Person2");
            person2.setLastName("Person2");
            person2.setEmail("email2");
            entityManager.persist(person2);
            LOGGER.info("This is from requiresNew()");
        } catch (Exception e) {
            LOGGER.error("Error in requiresNew() : {}", e.getClass());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
        try {
            Person person3 = new Person();
            person3.setFirstName("Person3");
            person3.setLastName("Person3");
            person3.setEmail("email3");
            entityManager.persist(person3);
            LOGGER.info("This is from required()");
        } catch (Exception e) {
            LOGGER.error("Error in required() : {}", e.getClass());
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() {
        try {
            Person person4 = new Person();
            person4.setFirstName("Person4");
            person4.setLastName("Person4");
            person4.setEmail("email4");
            entityManager.persist(person4);
            userRepository.saveuserorderdetail();
            LOGGER.info("This is from mandatory()");
        } catch (Exception e) {
            LOGGER.error("Error in mandatory() : {}", e.getClass());
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
        try {
            Person person5 = new Person();
            person5.setFirstName("Person5");
            person5.setLastName("Person5");
            person5.setEmail("email5");
            entityManager.persist(person5);
            LOGGER.info("This is from nested()");
        } catch (Exception e) {
            LOGGER.error("Error in nested() : {}", e.getClass());
        }
    }

    @Transactional(propagation = Propagation.NEVER)
    public void never() {
        Person person=entityManager.find(Person.class,57l);
        System.out.println("person detail=="+person);

        Person person6 = new Person();
        person6.setFirstName("Person5");
        person6.setLastName("Person5");
        person6.setEmail("email5");
        entityManager.persist(person6);
        LOGGER.info("This is from never()");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
        Person person=entityManager.find(Person.class,57l);
        System.out.println("person detail=="+person);


        Person person7 = new Person();
        person7.setFirstName("Person8");
        person7.setLastName("Person8");
        person7.setEmail("email8");
        entityManager.persist(person7);
        LOGGER.info("This is from notSupported()");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() {
//        try {
            LOGGER.info("This is from supports()1");

            Person person7 = new Person();
            person7.setFirstName("Person8");
            person7.setLastName("Person8");
            person7.setEmail("email8");
            entityManager.persist(person7);
            LOGGER.info("This is from supports()2");

            int a=10/0;
            LOGGER.info("This is from supports()");
//        } catch (Exception e) {
//            LOGGER.error("Error in supports() : {}", e.getClass());
//        }
    }

}
