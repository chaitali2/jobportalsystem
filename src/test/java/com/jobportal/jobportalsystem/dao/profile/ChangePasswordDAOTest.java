package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChangePasswordDAOTest {

    @Autowired
    ChangePasswordDAO changePasswordDAO;
    @PersistenceContext
    EntityManager entityManager;

    RegistrationDetail registrationDetail;

    @Before
    public void setUp() {
        prepareData();
    }

    void registerData() {
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

    public void prepareData() {
        registerData();
        registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername("xyz@gmail.com");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
    }

    @Test
    public void testFetchPasswordFromUser() {
        Object[] objects = {"+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=", "Lpm28h5myQheIFNflzA7oaB1bFGSCn"};
        List<Object[]> existing = new ArrayList<>();
        existing.add(objects);
        List<Object[]> passwordDetail = changePasswordDAO.fetchPasswordFromUser(registrationDetail.getUsername());
        assertEquals(existing.size(), passwordDetail.size());
    }

    @Test
    public void testUpdatePassword() {
        changePasswordDAO.updatePassword(registrationDetail);
    }


}