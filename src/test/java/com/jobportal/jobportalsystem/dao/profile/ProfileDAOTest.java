package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
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
import javax.persistence.Query;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProfileDAOTest {

    @Autowired
    ProfileDAO profileDAO;
    Profile profile;
    @PersistenceContext
    EntityManager entityManager;

    @Before
    public void setUp() {
        prepareData();
    }

    void registerData() {
        RegistrationDetail registrationDetail = new RegistrationDetail();
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
        profile = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstname("chaitali");
        registrationDetail.setLastname("Khachane");
        registrationDetail.setMobno("8877654321");

        Query query = entityManager.createQuery("Select d.id " +
                "from RegistrationDetail d " +
                "where d.emailid=:emailid");

        query.setParameter("emailid", "xyz@gmail.com");
        Long userId = Long.parseLong(query.getSingleResult().toString());

        registrationDetail.setId(userId);
        Address address = new Address();
        address.setState("Gujarat");
        address.setCity("Surat");
        address.setStreet_add("710 Sundarm Apartment,Athwagate");
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setExpected_salary("4");
        educationExperience.setExperience("2");
        educationExperience.setHighest_degree("BE");
        educationExperience.setPassing_year("2017");
        educationExperience.setPercentage("76");

        profile.setRegistrationDetail(registrationDetail);
        profile.setAddress(address);
        profile.setEducationExperience(educationExperience);
    }

    @Test
    public void testSaveProfileDetail() {
        profileDAO.saveProfileDetail(profile);
    }


    @Test
    public void testSaveProfileDetailExistInProfile() {
        profile = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername("chaitali@gmail.com");
        registrationDetail.setFirstname("chaitali");
        registrationDetail.setLastname("Khachane");
        registrationDetail.setMobno("8877654321");
        registrationDetail.setUsertype("J");
        registrationDetail.setId(16l);
        Address address = new Address();
        address.setState("Gujarat");
        address.setCity("Surat");
        address.setStreet_add("710 Sundarm Apartment,Athwagate");
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setExpected_salary("4");
        educationExperience.setExperience("2");
        educationExperience.setHighest_degree("BE");
        educationExperience.setPassing_year("2017");
        educationExperience.setPercentage("76");

        profile.setRegistrationDetail(registrationDetail);
        profile.setAddress(address);
        profile.setEducationExperience(educationExperience);
        profileDAO.saveProfileDetail(profile);
    }

    @Test
    public void testFetchUserDetails() {
        Profile profileDetails = profileDAO.fetchUserDetails(1l);
        assertEquals("chaitali@gmail.com", profileDetails.getRegistrationDetail().getUsername());
    }

    @Test
    public void testFetchUserDetailsExistOtherDetail() {
        Profile profileDetails = profileDAO.fetchUserDetails(16l);
        assertEquals("mona@gmail.com", profileDetails.getRegistrationDetail().getUsername());
    }
}