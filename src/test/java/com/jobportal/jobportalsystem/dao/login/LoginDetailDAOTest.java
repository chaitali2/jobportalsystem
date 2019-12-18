package com.jobportal.jobportalsystem.dao.login;

import com.jobportal.jobportalsystem.dao.registration.RegistrationDAO;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginDetailDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDetailDAOTest.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    LoginDetailDAO loginDetailDAO;
    RegistrationDetail registrationDetail;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstname("chaitali");
        registrationDetail.setLastname("Khachane");
        registrationDetail.setDob("11-01-1195");
        registrationDetail.setEmailid("xyz@gmail.com");
        registrationDetail.setMobno("8866049741");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setUsertype("R");
        registrationDetail.setUsername("xyz@gmail.com");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
        entityManager.persist(registrationDetail);
    }

    @Test
    public void testGetUserProfile() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile(registrationDetail.getUsername());
        assertEquals("checking find email id", "xyz@gmail.com", userprofile.get().getUsername());
    }

    @Test
    public void testGetUserProfileNotFound() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile("abc@gmail.com");
        assertFalse(userprofile.isPresent());
    }
}