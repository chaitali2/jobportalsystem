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

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileDAOTest {

    @Autowired
    ProfileDAO profileDAO;
    Profile profile;

    @Before
    public void setUp() {
        prepareData();
    }


    public void prepareData() {
        profile = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setUsername("chaitali@gmail.com");
        registrationDetail.setFirstname("chaitali");
        registrationDetail.setLastname("Khachane");
        registrationDetail.setMobno("8877654321");
        registrationDetail.setId(1l);
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