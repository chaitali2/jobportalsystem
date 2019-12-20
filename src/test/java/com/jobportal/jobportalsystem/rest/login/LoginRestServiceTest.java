package com.jobportal.jobportalsystem.rest.login;

import static org.junit.Assert.assertEquals;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import com.jobportal.jobportalsystem.service.login.LoginService;
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
public class LoginRestServiceTest {


    @MockBean
    LoginService loginService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void testAuthenticateUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        LoginDetailDTO loginDetailDTO = new LoginDetailDTO();
        loginDetailDTO.setUsername("chaitali@gmail.com");
        loginDetailDTO.setPassword("Chai@1234");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(loginDetailDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/login"), HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}