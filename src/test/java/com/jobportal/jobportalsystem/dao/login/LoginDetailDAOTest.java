package com.jobportal.jobportalsystem.dao.login;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDetailDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDetailDAOTest.class);

    @Autowired
    LoginDetailDAO loginDetailDAO;

    @Test
    public void testGetUserProfile() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile("chaitali@gmail.com");
        assertEquals("checking find email id", "chaitali@gmail.com", userprofile.get().getUsername());
    }

    @Test
    public void testGetUserProfileNotFound() {
        Optional<RegistrationDetail> userprofile = loginDetailDAO.getUserProfile("chaitali1@gmail.com");
        assertFalse(userprofile.isPresent());
    }
}