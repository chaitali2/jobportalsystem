package com.jobportal.jobportalsystem.rest.recruiter_jobseeker;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterJobSeekerService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    RecruiterJobSeekerService recruiterService;

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
//        postJobDetailDTO.setSkills(skills);
        postJobDetailDTO.setJob_type("P");
        postJobDetailDTO.setExperience(2);
        postJobDetailDTO.setSalary_offer(4);
        postJobDetailDTO.setStreet_add("710 Alfa Tower");
        postJobDetailDTO.setState("Gujarat");
        postJobDetailDTO.setCity("Surat");
//        postJobDetailDTO.setPincode(369874);
        postJobDetailDTO.setJob_opening_date("30-03-2019");
        postJobDetailDTO.setDescription("requirement for developer");

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(postJobDetailDTO), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/addjob_posts"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

//    @Test
//    public void testLoadSkills() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        Map<String, Long> keyValue = new HashMap<>();
//        keyValue.put("category_id", 4l);
//
//        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
//        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
//        System.out.println("load skill");
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/api/jobportal/recruiter/loadskill"), HttpMethod.POST, entity, String.class);
//        System.out.println("response==" + response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    public void testLoadCategoryList() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/loadCategory"), HttpMethod.GET, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFetchJobDetails(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1L);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/jobDetails"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFetchJobDetailsOfCompany(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("job_id", 1L);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/jobdetailofcompany"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRemoveJobPostDetail(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("job_id", 1L);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/removejobpost"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAppliedJobsList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("job_id", 1L);

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        HttpEntity<String> entity = new HttpEntity<String>(jacksonJsonProvider.toJson(keyValue), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/jobportal/recruiter/removejobpost"), HttpMethod.POST, entity, String.class);
        System.out.println("response==" + response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}