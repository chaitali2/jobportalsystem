package com.jobportal.jobportalsystem.service.recruiter;

import com.jobportal.jobportalsystem.dao.recruiter.RecruiterJobSeekerDAO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.utility.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RecruiterServiceTest {

    @Mock
    RecruiterJobSeekerDAO recruiterDAO;

    @Mock
    Utility utility;


    @InjectMocks
    RecruiterJobSeekerService recruiterService;

    @Test
    public void testPostJobDetail() {

    }

    @Test
    public void testAppliedJobsList() {
        Long job_id = 4L;
        Object[] recrutierArr = {"firstname", "lastname", "company", "description", "12-12-2019", "filename"};
        Object[] recrutierArr1 = {"firstname1", "lastname1", "company1", "description1", "12-12-2019", "filename1"};
        List<Object[]> recrutierList = new ArrayList<>();
        recrutierList.add(recrutierArr);
        recrutierList.add(recrutierArr1);
        Mockito.when(recruiterDAO.appliedJobsList(Mockito.eq(job_id))).thenReturn(recrutierList);
        List<ApplyJobDTO> applyJobDTOList = recruiterService.appliedJobsList(job_id);
        assertNotNull(applyJobDTOList);
        assertFalse(applyJobDTOList.isEmpty());
        assertEquals(2,applyJobDTOList.size());
        assertEquals("firstname",applyJobDTOList.get(0).getFirstname());
        assertEquals("lastname",applyJobDTOList.get(0).getLastname());
        assertEquals("company",applyJobDTOList.get(0).getCompany());
        assertEquals("description",applyJobDTOList.get(0).getDescription());
        assertEquals("12-12-2019",applyJobDTOList.get(0).getApplyDate());
        assertEquals("filename",applyJobDTOList.get(0).getFileName());

        assertEquals("firstname1",applyJobDTOList.get(1).getFirstname());
        assertEquals("lastname1",applyJobDTOList.get(1).getLastname());
        assertEquals("company1",applyJobDTOList.get(1).getCompany());
        assertEquals("description1",applyJobDTOList.get(1).getDescription());
        assertEquals("12-12-2019",applyJobDTOList.get(1).getApplyDate());
        assertEquals("filename1",applyJobDTOList.get(1).getFileName());
    }

    @Test
    public void testLoadCategoryList() {
        List<Category> categories = new ArrayList<>();
        Mockito.when(recruiterDAO.loadCategoryList()).thenReturn(categories);


    }

    @Test
    public void testLoadSkills() {
        Long category_id = 4l;
        List<Object[]> skills = new ArrayList<>();
        Mockito.when(recruiterDAO.loadSkills(category_id)).thenReturn(skills);
    }

    @Test
    public void testFetchJobDetails() {
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1l);
        List<PostJobDetail> jobDetails = new ArrayList<>();
        Mockito.when(recruiterDAO.fetchJobDetails(keyValue)).thenReturn(jobDetails);

    }

    @Test
    public void testFetchJobDetailsOfCompany() {
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1l);
        PostJobDetail jobDetails = new PostJobDetail();
        Mockito.when(recruiterDAO.fetchJobDetailsOfCompany(keyValue.get("user_id"))).thenReturn(jobDetails);
    }

    @Test
    public void testRemoveJobPostDetail() {

    }


}