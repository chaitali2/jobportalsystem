package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
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
import javax.persistence.Query;

import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProfileDAOTest {

    @Autowired
    private ProfileDAO profileDAO;
    private Profile profile;
    private RegistrationDetail registrationDetail;
    @PersistenceContext
    private EntityManager entityManager;

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
        profile = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName("xyz");
        registrationDetail.setLastName("abc");
        registrationDetail.setMobileNo("8877654321");

        Query query = entityManager.createQuery("Select d.id " +
                "from RegistrationDetail d " +
                "where d.emailId=:emailid");

        query.setParameter("emailid", "xyz@gmail.com");
        Long userId = Long.parseLong(query.getSingleResult().toString());

        registrationDetail.setId(userId);
        Address address = new Address();
        address.setState("Gujarat");
        address.setCity("Surat");
        address.setStreet("710 Sundarm Apartment,Athwagate");
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setExpectedSalary("4");
        educationExperience.setExperience("2");
        educationExperience.setHighestDegree("BE");
        educationExperience.setPassingYear("2017");
        educationExperience.setPercentage("76");

        profile.setRegistrationDetail(registrationDetail);
        profile.setAddress(address);
        profile.setEducationExperience(educationExperience);
    }

    @Test
    public void testSaveProfileDetail() {
        profileDAO.saveProfileDetail(profile);
        Profile profileDetails = profileDAO.fetchUserDetails(profile.getRegistrationDetail().getId());
        assertEquals("xyz@gmail.com", profileDetails.getRegistrationDetail().getUsername());
        assertEquals("xyz", profileDetails.getRegistrationDetail().getFirstName());
        assertEquals("abc", profileDetails.getRegistrationDetail().getLastName());
        assertEquals("710 Sundarm Apartment,Athwagate", profileDetails.getAddress().getStreet());
        assertEquals("Surat", profileDetails.getAddress().getCity());
        assertEquals("Gujarat", profileDetails.getAddress().getState());
        assertEquals("4", profileDetails.getEducationExperience().getExpectedSalary());
        assertEquals("2", profileDetails.getEducationExperience().getExperience());
        assertEquals("BE", profileDetails.getEducationExperience().getHighestDegree());
        assertEquals("2017", profileDetails.getEducationExperience().getPassingYear());
        assertEquals("76", profileDetails.getEducationExperience().getPercentage());
    }

    @Test
    public void testFetchUserDetails() {
        testSaveProfileDetail();
        Profile profileDetails = profileDAO.fetchUserDetails(profile.getRegistrationDetail().getId());
        assertEquals("xyz@gmail.com", profileDetails.getRegistrationDetail().getUsername());
        assertEquals("xyz", profileDetails.getRegistrationDetail().getFirstName());
        assertEquals("abc", profileDetails.getRegistrationDetail().getLastName());
        assertEquals("710 Sundarm Apartment,Athwagate", profileDetails.getAddress().getStreet());
        assertEquals("Surat", profileDetails.getAddress().getCity());
        assertEquals("Gujarat", profileDetails.getAddress().getState());
        assertEquals("4", profileDetails.getEducationExperience().getExpectedSalary());
        assertEquals("2", profileDetails.getEducationExperience().getExperience());
        assertEquals("BE", profileDetails.getEducationExperience().getHighestDegree());
        assertEquals("2017", profileDetails.getEducationExperience().getPassingYear());
        assertEquals("76", profileDetails.getEducationExperience().getPercentage());
    }
}