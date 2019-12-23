package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.dao.profile.ProfileDAO;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTest {

    @Captor
    private ArgumentCaptor<Profile> profileArgumentCaptor;
    @Mock
    private ProfileDAO profileDAO;
    @InjectMocks
    private ProfileService profileService;
    private ProfileDTO profileDTO;

    @Before
    public void setUp() {
        prepareData();
    }

    private void prepareData() {
        profileDTO = new ProfileDTO();
        profileDTO.setFirstName("chaitali");
        profileDTO.setLastName("Khachane");
        profileDTO.setMobileNo("8866049741");
        profileDTO.setCity("Surat");
        profileDTO.setState("Gujarat");
        profileDTO.setStreet("710- udhyog nagar");
        profileDTO.setExpectedSalary("5.5");
        profileDTO.setExperience("2");
        profileDTO.setHighestDegree("BE");
        profileDTO.setPassingYear("2016");
        profileDTO.setUserID("1");
    }

    @Test
    public void testFetchUserDetails() {
        Long user_id = 1L;
        Profile profileDetail = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName("chaitali");
        registrationDetail.setLastName("Khachane");
        registrationDetail.setMobileNo("8866049741");
        profileDetail.setRegistrationDetail(registrationDetail);
        Address address = new Address();
        address.setCity("Surat");
        address.setState("Gujarat");
        address.setStreet("710- udhyog nagar");
        profileDetail.setAddress(address);
        Mockito.when(profileDAO.fetchUserDetails(user_id)).thenReturn(profileDetail);
        ProfileDTO profileDTO = profileService.fetchUserDetails(1L);
        assertNotNull(profileDTO);
        assertEquals(profileDetail.getRegistrationDetail().getFirstName(), profileDTO.getFirstName());
        assertEquals(profileDetail.getRegistrationDetail().getLastName(), profileDTO.getLastName());
        assertEquals(profileDetail.getRegistrationDetail().getMobileNo(), profileDTO.getMobileNo());
        assertEquals(profileDetail.getAddress().getCity(), profileDTO.getCity());
        assertEquals(profileDetail.getAddress().getState(), profileDTO.getState());
        assertEquals(profileDetail.getAddress().getStreet(), profileDTO.getStreet());
    }

    @Test
    public void testSaveProfileDetail() {
        Mockito.doNothing().when(profileDAO).saveProfileDetail(profileArgumentCaptor.capture());
        profileService.saveProfileDetail(profileDTO);
        Profile profileDetail = profileArgumentCaptor.getValue();
        assertNotNull(profileDetail);
        assertEquals(profileDTO.getFirstName(), profileDetail.getRegistrationDetail().getFirstName());
        assertEquals(profileDTO.getLastName(), profileDetail.getRegistrationDetail().getLastName());
        assertEquals(profileDTO.getMobileNo(), profileDetail.getRegistrationDetail().getMobileNo());
        assertEquals(profileDTO.getCity(), profileDetail.getAddress().getCity());
        assertEquals(profileDTO.getState(), profileDetail.getAddress().getState());
        assertEquals(profileDTO.getStreet(), profileDetail.getAddress().getStreet());
    }
}