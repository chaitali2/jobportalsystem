package com.jobportal.jobportalsystem.rest.profile;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.service.profile.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProfileRestServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    ProfileService profileService;

    @Test
    public void testSaveProfileDetail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstname("Chaitali");
        profileDTO.setLastname("Khachane");
        profileDTO.setPercentage("70");
        profileDTO.setPassing_year("2016");
        profileDTO.setHighest_degree("BE");
        profileDTO.setExperience("2");
        profileDTO.setExpected_salary(1.0);
        profileDTO.setMobno("8866049741");
        profileDTO.setStreet_add("170 IFA Tower");
        profileDTO.setState("MH");
        profileDTO.setCity("Pune");
        profileDTO.setUserID("1");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(profileDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/saveProfileDetail"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFetchUserDetails() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1L);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/userdetails"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}