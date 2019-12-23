package com.jobportal.jobportalsystem.service.recruiterjobseeker;

import com.jobportal.jobportalsystem.dao.recruiterjobseeker.RecruiterJobSeekerDAO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.CategoryDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Skill;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.PostJobDetail;
import com.jobportal.jobportalsystem.utility.Utility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruiterJobseekerServiceTest {

    @Mock
    private RecruiterJobSeekerDAO recruiterDAO;
    @Mock
    private Utility utility;
    @Captor
    private ArgumentCaptor<PostJobDetail> postJobDetailArgumentCaptor;
    @InjectMocks
    private RecruiterJobSeekerService recruiterService;
    private PostJobDetailDTO postJobDetailDTO;

    @Before
    public void setUp() {
        prepareData();
    }

    private void prepareData() {
        postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setCompany("NJ India Invest");
        postJobDetailDTO.setCategoryId(2L);
        Set<String> skills = new HashSet<>();
        skills.add("2");
        skills.add("3");
        postJobDetailDTO.setSkills(skills);
        postJobDetailDTO.setJobType("P");
        postJobDetailDTO.setExperience("2");
        postJobDetailDTO.setSalaryOffer("4");
        postJobDetailDTO.setStreet("710 Alfa Tower");
        postJobDetailDTO.setState("Gujarat");
        postJobDetailDTO.setCity("Surat");
        postJobDetailDTO.setJobOpeningDate("30-03-2019");
        postJobDetailDTO.setDescription("requirement for developer");
    }

    @Test
    public void testPostJobDetail() throws ParseException {
        Mockito.doNothing().when(recruiterDAO).saveJobPostDetail(postJobDetailArgumentCaptor.capture());
        recruiterService.postJobDetail(postJobDetailDTO);
        PostJobDetail postJobDetail = postJobDetailArgumentCaptor.getValue();
        assertNotNull(postJobDetail);
        assertEquals(postJobDetailDTO.getCompany(), postJobDetail.getCompany());
        assertEquals(postJobDetailDTO.getDescription(), postJobDetail.getDescription());
        assertEquals(Double.valueOf(postJobDetailDTO.getExperience()), postJobDetail.getExperience());
        assertEquals(postJobDetailDTO.getCity(), postJobDetail.getJobLocation().getCity());
        assertEquals(postJobDetailDTO.getState(), postJobDetail.getJobLocation().getState());
        assertEquals(postJobDetailDTO.getStreet(), postJobDetail.getJobLocation().getStreet());
        assertEquals(Double.valueOf(postJobDetailDTO.getSalaryOffer()), postJobDetail.getSalaryOffer());
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
        assertEquals(2, applyJobDTOList.size());
        assertEquals("firstname", applyJobDTOList.get(0).getFirstName());
        assertEquals("lastname", applyJobDTOList.get(0).getLastName());
        assertEquals("company", applyJobDTOList.get(0).getCompany());
        assertEquals("description", applyJobDTOList.get(0).getDescription());
        assertEquals("12-12-2019", applyJobDTOList.get(0).getApplyDate());
        assertEquals("filename", applyJobDTOList.get(0).getFileName());

        assertEquals("firstname1", applyJobDTOList.get(1).getFirstName());
        assertEquals("lastname1", applyJobDTOList.get(1).getLastName());
        assertEquals("company1", applyJobDTOList.get(1).getCompany());
        assertEquals("description1", applyJobDTOList.get(1).getDescription());
        assertEquals("12-12-2019", applyJobDTOList.get(1).getApplyDate());
        assertEquals("filename1", applyJobDTOList.get(1).getFileName());
    }

    @Test
    public void testLoadCategoryList() {
        List<Category> categories = new ArrayList<>();
        Skill skill = new Skill();
        skill.setId(3L);
        skill.setSkillName("oracle");
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(skill);
        Category category = new Category();
        category.setId(4L);
        category.setCategoryName("DBA");
        category.setSkills(skillSet);
        categories.add(category);
        Mockito.when(recruiterDAO.loadCategoryList()).thenReturn(categories);
        List<CategoryDTO> categoryDTOList = recruiterService.loadCategoryList();
        assertNotNull(categoryDTOList);
        assertEquals(categoryDTOList.size(), categories.size());
        assertEquals(categoryDTOList.get(0).getCategoryName(), categories.get(0).getCategoryName());
    }

    @Test
    public void testFetchJobDetails() {
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1L);
        List<PostJobDetail> jobDetails = new ArrayList<>();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setCompany("NJ India Invest");
        Category category = new Category();
        category.setId(4L);
        category.setCategoryName("DBA");
        postJobDetail.setCategory(category);
        Set<Skill> skillSet = new HashSet<>();
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setSkillName("MY-SQL");
        skillSet.add(skill);
        postJobDetail.setSkillSet(skillSet);
        postJobDetail.setJobType(PostJobDetail.Job.P);
        postJobDetail.setExperience(2);
        postJobDetail.setSalaryOffer(4);
        JobLocation jobLocation = new JobLocation();
        jobLocation.setCity("surat");
        jobLocation.setStreet("710 Alfa Tower");
        jobLocation.setState("Gujarat");
        postJobDetail.setJobLocation(jobLocation);
        postJobDetail.setJobOpeningDate(new Date());
        postJobDetail.setDescription("requirement for developer");
        jobDetails.add(postJobDetail);
        Mockito.when(recruiterDAO.fetchJobDetailsForRecruiter(keyValue)).thenReturn(jobDetails);
        List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(keyValue);
        assertNotNull(postJobDetailDTOS);
        assertFalse(postJobDetailDTOS.isEmpty());
        assertEquals(jobDetails.get(0).getCompany(), postJobDetailDTOS.get(0).getCompany());
    }
}