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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChangePasswordDAOTest {

    @Autowired
    private ChangePasswordDAO changePasswordDAO;
    @PersistenceContext
    private EntityManager entityManager;
    private RegistrationDetail registrationDetail;
    @Before
    public void setUp() {
        prepareData();
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

    public void prepareData() {
        registerPrepareData();
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
        assertNotNull(passwordDetail);
        assertEquals("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=",passwordDetail.get(0)[0]);
        assertEquals("Lpm28h5myQheIFNflzA7oaB1bFGSCn",passwordDetail.get(0)[1]);

    }

    @Test
    public void testUpdatePassword() {
        changePasswordDAO.updatePassword(registrationDetail);
        List<Object[]> passwordDetail = changePasswordDAO.fetchPasswordFromUser(registrationDetail.getUsername());
        assertNotNull(passwordDetail);
        assertEquals("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=",passwordDetail.get(0)[0]);
        assertEquals("Lpm28h5myQheIFNflzA7oaB1bFGSCn",passwordDetail.get(0)[1]);
    }


}