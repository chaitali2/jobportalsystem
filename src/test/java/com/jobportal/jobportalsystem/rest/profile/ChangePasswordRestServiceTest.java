package com.jobportal.jobportalsystem.rest.profile;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.profile.PasswordDTO;
import com.jobportal.jobportalsystem.service.profile.ChangePasswordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ChangePasswordRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    ChangePasswordService changePasswordService;

    @Test
    public void testChangePassword() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXJzaEBnbWFpbC5jb20iLCJleHAiOjE1NzY1OTI1NjgsInVzZXIiOnsidXNlcm5hbWUiOiJoYXJzaEBnbWFpbC5jb20iLCJwYXNzd29yZCI6IkhhcnNoQDEyMzQifSwiaWF0IjoxNTc2NTc0NTY4fQ.bKJmelEE5XtE-zU4M8IvjLeOItTk4l4dDg1ADK2cApc6Wx-tDK1VnrMJwbBTzpFZZmeVHdXdtCZp7o8SI1slag");
        headers.set("username", "harsh@gmail.com");
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setNew_password("Chai@1234");
        passwordDTO.setOld_password("Chai@1234");
        passwordDTO.setConfirm_password("Chai@1234");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(passwordDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/changePassword"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}