package com.jobportal.jobportalsystem.rest.recruiter;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RecruiterRestServiceTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    RecruiterService recruiterService;

    @Test
    public void testPostJobDetail() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setCompany("NJ India Invest");
        postJobDetailDTO.setCategory_id(2l);
        List<String> skills = new ArrayList<>();
        skills.add("2");
        skills.add("3");
        postJobDetailDTO.setSkills(skills);
        postJobDetailDTO.setJob_type("P");
        postJobDetailDTO.setExperience(2);
        postJobDetailDTO.setSalary_offer(4);
        postJobDetailDTO.setStreet_add("710 Alfa Tower");
        postJobDetailDTO.setState("Gujarat");
        postJobDetailDTO.setCity("Surat");
        postJobDetailDTO.setPincode(369874);
        postJobDetailDTO.setJob_opening_date("30-03-2019");
        postJobDetailDTO.setDescription("requirement for developer");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(postJobDetailDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/addjob_posts"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    public void testLoadSkills() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String categoryId = "2";
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(categoryId), headers);
        System.out.println("load skill");
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/loadskill"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}