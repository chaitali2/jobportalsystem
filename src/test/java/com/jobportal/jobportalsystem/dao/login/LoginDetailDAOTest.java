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
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginDetailDAOTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private LoginDetailDAO loginDetailDAO;
    private RegistrationDetail registrationDetail;

    @Before
    public void setUp() {
        registerPrepareData();
    }


    private void registerPrepareData() {
        registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName("chaitali");
        registrationDetail.setLastName("Khachane");
        registrationDetail.setDateOfBirth(new Date());
        registrationDetail.setEmailId("xyz@gmail.com");
        registrationDetail.setMobileNo("8866049741");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setUserType(RegistrationDetail.user.R);
        registrationDetail.setUsername("xyz@gmail.com");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
        entityManager.persist(registrationDetail);
    }

    @Test
    public void testGetUserProfile() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile(registrationDetail.getUsername());
        assertTrue(userprofile.isPresent());
        assertEquals("xyz@gmail.com", userprofile.get().getUsername());
        assertEquals("chaitali", userprofile.get().getFirstName());
        assertEquals( "Khachane", userprofile.get().getLastName());
        assertEquals("xyz@gmail.com", userprofile.get().getEmailId());
        assertEquals( "8866049741", userprofile.get().getMobileNo());
        assertEquals( "R", userprofile.get().getUserType().toString());
        assertEquals( "Lpm28h5myQheIFNflzA7oaB1bFGSCn", userprofile.get().getSalt());
        assertEquals( "+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=", userprofile.get().getPassword());
    }

    @Test
    public void testGetUserProfileNotFound() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile("abc@gmail.com");
        assertFalse(userprofile.isPresent());
    }
}