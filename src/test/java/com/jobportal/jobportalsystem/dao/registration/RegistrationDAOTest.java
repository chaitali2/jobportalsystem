package com.jobportal.jobportalsystem.dao.registration;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RegistrationDAOTest {

    @Autowired
    private RegistrationDAO registrationDAO;
    private RegistrationDetail registrationDetail;

    @Before
    public void setUp() {
        prepareData();
    }

    private void prepareData() {
        registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName("chaitali");
        registrationDetail.setLastName("Khachane");
        registrationDetail.setDateOfBirth(new Date());
        registrationDetail.setEmailId("xyzyy@gmail.com");
        registrationDetail.setMobileNo("8866049741");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setUserType(RegistrationDetail.user.R);
        registrationDetail.setUsername("xyzyy@gmail.com");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
    }

    @Test
    public void testExistByEmailID() {
        testSaveRegistrationDetail();
        boolean isExist = registrationDAO.existByEmailID(registrationDetail.getEmailId());
        Assert.assertTrue(isExist);
    }

    @Test
    public void testSaveRegistrationDetail() {
        registrationDAO.saveRegistrationDetail(registrationDetail);
    }
}