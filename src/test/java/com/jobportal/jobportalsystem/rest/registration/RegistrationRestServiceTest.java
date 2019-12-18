package com.jobportal.jobportalsystem.rest.registration;

import static org.junit.Assert.assertEquals;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.registration.RegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RegistrationRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    RegistrationService registrationService;

    @Test
    public void testRegisterUserDetail(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RegistrationDetailDTO registrationDetailDTO = new RegistrationDetailDTO();
        registrationDetailDTO.setFirstname("chaitali");
        registrationDetailDTO.setLastname("Khachane");
        registrationDetailDTO.setDob("11-01-1195");
        registrationDetailDTO.setEmailid("chaitali@gmail.com");
        registrationDetailDTO.setMobno("8866049741");
        registrationDetailDTO.setPassword("Chai@1234");
        registrationDetailDTO.setConfpassword("Chai@1234");
        registrationDetailDTO.setUsertype("R");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(registrationDetailDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/signup"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}