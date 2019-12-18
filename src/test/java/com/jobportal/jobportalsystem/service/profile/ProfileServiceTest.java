package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.dao.profile.ProfileDAO;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProfileServiceTest {

    @Captor
    ArgumentCaptor<Profile> profileArgumentCaptor;
    @Mock
    ProfileDAO profileDAO;

    @Autowired
    ProfileService profileService;

    ProfileDTO profileDTO;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        profileDTO = new ProfileDTO();
        profileDTO.setFirstname("chaitali");
        profileDTO.setLastname("Khachane");
        profileDTO.setMobno("8866049741");
        profileDTO.setCity("Surat");
        profileDTO.setState("Gujarat");
        profileDTO.setStreet_add("710- udhyog nagar");
        profileDTO.setExpected_salary("5.5");
        profileDTO.setExperience("2");
        profileDTO.setHighest_degree("BE");
        profileDTO.setPassing_year("2016");
        profileDTO.setUserID("1");
    }

    @Test
    public void testFetchUserDetails() {
        Long user_id = 1l;
        Profile profileDetail = new Profile();
        Mockito.when(profileDAO.fetchUserDetails(user_id)).thenReturn(profileDetail);
    }

    @Test
    public void testSaveProfileDetail() {
        Mockito.doNothing().when(profileDAO).saveProfileDetail(profileArgumentCaptor.capture());
    }
}