package com.jobportal.jobportalsystem.dao.registration;

import com.jobportal.jobportalsystem.JobportalsystemApplication;
import com.jobportal.jobportalsystem.config.H2Configuration;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationDAOTest {

    @Autowired
    RegistrationDAO registrationDAO;


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
        registrationDetail.setEmailid("chaitali@gmail.com");
        registrationDetail.setMobno("8866049741");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setUsertype("R");
        registrationDetail.setUsername("chaitali@gmail.com");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
    }

    @Test
    @Order(2)
    public void testExistByEmailID() {
        boolean isExist = registrationDAO.existByEmailID(registrationDetail.getEmailid());
        Assert.assertTrue(isExist);
    }

    @Test
    @Order(1)
    public void testSaveRegistrationDetail() {
        registrationDAO.saveRegistrationDetail(registrationDetail);
    }
}