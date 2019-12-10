package com.jobportal.jobportalsystem.service.recruiter_jobseeker;

import com.jobportal.jobportalsystem.dao.recruiter_jobseeker.RecruiterJobSeekerDAO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiter_jobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter_jobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.recruiter_jobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter_jobseeker.PostJobDetail;
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
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RecruiterJobseekerServiceTest {

    @Mock
    RecruiterJobSeekerDAO recruiterDAO;

    @Captor
    ArgumentCaptor<PostJobDetail> postJobDetailArgumentCaptor;
    @Mock
    Utility utility;

    @InjectMocks
    RecruiterJobSeekerService recruiterService;
    PostJobDetailDTO postJobDetailDTO;

    @Before
    public void setUp() {
        prepareData();
    }

    void prepareData() {
        postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setCompany("NJ India Invest");
        postJobDetailDTO.setCategory_id(2l);
        Set<String> skills = new HashSet<>();
        skills.add("2");
        skills.add("3");
        postJobDetailDTO.setSkills(skills);
        postJobDetailDTO.setJob_type("P");
        postJobDetailDTO.setExperience(2);
        postJobDetailDTO.setSalary_offer(4);
        postJobDetailDTO.setStreet_add("710 Alfa Tower");
        postJobDetailDTO.setState("Gujarat");
        postJobDetailDTO.setCity("Surat");
//        postJobDetailDTO.setPincode(369874);
        postJobDetailDTO.setJob_opening_date("30-03-2019");
        postJobDetailDTO.setDescription("requirement for developer");
    }

    @Test
    public void testPostJobDetail() throws ParseException {
        Mockito.doNothing().when(recruiterDAO).saveJobPostDetail(postJobDetailArgumentCaptor.capture());
        recruiterService.postJobDetail(postJobDetailDTO);
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
        assertEquals("firstname", applyJobDTOList.get(0).getFirstname());
        assertEquals("lastname", applyJobDTOList.get(0).getLastname());
        assertEquals("company", applyJobDTOList.get(0).getCompany());
        assertEquals("description", applyJobDTOList.get(0).getDescription());
        assertEquals("12-12-2019", applyJobDTOList.get(0).getApplyDate());
        assertEquals("filename", applyJobDTOList.get(0).getFileName());

        assertEquals("firstname1", applyJobDTOList.get(1).getFirstname());
        assertEquals("lastname1", applyJobDTOList.get(1).getLastname());
        assertEquals("company1", applyJobDTOList.get(1).getCompany());
        assertEquals("description1", applyJobDTOList.get(1).getDescription());
        assertEquals("12-12-2019", applyJobDTOList.get(1).getApplyDate());
        assertEquals("filename1", applyJobDTOList.get(1).getFileName());
    }

    @Test
    public void testLoadCategoryList() {
        List<Category> categories = new ArrayList<>();
        Skill skill = new Skill();
        skill.setId(3l);
        skill.setSkill_name("oracle");
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(skill);
        Category category = new Category();
        category.setId(4l);
        category.setCategoryName("DBA");
        category.setSkills(skillSet);
        categories.add(category);
        Mockito.when(recruiterDAO.loadCategoryList()).thenReturn(categories);
        List<CategoryDTO> categoryDTOList = recruiterService.loadCategoryList();
        assertEquals(categoryDTOList.size(), categories.size());
        assertEquals(categoryDTOList.get(0).getCategoryName(), categories.get(0).getCategoryName());
    }


    @Test
    public void testLoadSkills() {
        Long category_id = 4l;
        List<Object[]> skills = new ArrayList<>();
        Object[] skillarr = {2l, "MYSQL"};
        skills.add(skillarr);
        Mockito.when(recruiterDAO.loadSkills(category_id)).thenReturn(skills);
        List<SkillDTO> skillDTOList = recruiterService.loadSkills(4l);
        assertEquals(skillDTOList.size(),skills.size());
        assertEquals(skillDTOList.get(0).getSkill_name(),skills.get(0)[1]);
    }

    @Test
    public void testFetchJobDetails() {
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1l);
        List<PostJobDetail> jobDetails = new ArrayList<>();
        PostJobDetail postJobDetail=new PostJobDetail();
        postJobDetail.setCompany("NJ India Invest");
        Category category=new Category();
        category.setId(4l);
        category.setCategoryName("DBA");
        postJobDetail.setCategory(category);
        Set<Skill> skillSet = new HashSet<>();
        Skill skill=new Skill();
        skill.setId(1l);
        skill.setSkill_name("MY-SQL");
        skillSet.add(skill);

        postJobDetail.setSkillSet(skillSet);
        postJobDetail.setJob_type("P");
        postJobDetail.setExperience(2);
        postJobDetail.setSalary_offer(4);
        JobLocation jobLocation=new JobLocation();
        jobLocation.setCity("surat");
        jobLocation.setStreet_add("710 Alfa Tower");
        jobLocation.setState("Gujarat");
        postJobDetail.setJobLocation(jobLocation);
        postJobDetailDTO.setPincode("369874");
        postJobDetail.setJob_opening_date("30-03-2019");
        postJobDetail.setDescription("requirement for developer");
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