package com.jobportal.jobportalsystem.dao.recruiter_jobseeker;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.recruiter_jobseeker.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter_jobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter_jobseeker.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruiterJobSeekerDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterJobSeekerDAOTest.class);

    @Autowired
    RecruiterJobSeekerDAO recruiterJobSeekerDAO;


    PostJobDetail postJobDetail;

    @Before
    public void setUp() {
        prepareData();
    }

    public void prepareData() {
        postJobDetail = new PostJobDetail();
        postJobDetail.setCompany("Infosys");
        Category category = new Category();
        category.setId(4l);
        postJobDetail.setCategory(category);
        Set<Skill> skillSet = new HashSet<>();
        Skill skill1 = new Skill();
        skill1.setId(2l);
        Skill skill2 = new Skill();
        skill2.setId(3l);
        skillSet.add(skill1);
        skillSet.add(skill2);
        postJobDetail.setSkillSet(skillSet);
        postJobDetail.setExperience(2l);
        postJobDetail.setJob_type("P");
        postJobDetail.setSalary_offer(4);
        postJobDetail.setDescription("Front end developer");
        postJobDetail.setJob_opening_date("09-12-2019");
        JobLocation jobLocation = new JobLocation();
        jobLocation.setState("MH");
        jobLocation.setStreet_add("Business bay,Yerwada");
        jobLocation.setCity("Pune");
        jobLocation.setPincode("396230");
        postJobDetail.setJobLocation(jobLocation);
    }

    @Test
    @Order(1)
    public void testSaveJobPostDetail() {
        LOGGER.info("postJobDetail=" + postJobDetail);
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
    }

    @Test
    @Order(2)
    public void testfetchJobDetails() {
        LOGGER.info("postJobDetail=" + postJobDetail);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("user_id", 1l);
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetails(keyValue);
        assertEquals(keyValue.get("user_id"), postJobDetails.get(0).getRegistrationDetail().getId());
    }

    @Test
    public void testFetchJobDetailsForJobSeeker() {
        LOGGER.info("postJobDetail=" + postJobDetail);
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetails(null);
        assertFalse(postJobDetails.isEmpty());
    }

    @Test
    public void testFetchJobDetailsOfCompany() {
        PostJobDetail jobDetail = recruiterJobSeekerDAO.fetchJobDetailsOfCompany(8l);
        LOGGER.info("jobDetail==" + jobDetail);
        assertEquals("Infosys", jobDetail.getCompany());
    }

    @Test
    public void testRemoveJobPostDetail() {
        recruiterJobSeekerDAO.removeJobPostDetail(13l);
    }

    @Test
    public void testApplyForJOB() {
        ApplyJOB applyJOB = new ApplyJOB();
        applyJOB.setFilename("1.pdf");
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(8l);
        applyJOB.setPostJobDetail(postJobDetail);
        RegistrationDetail jobSeekerDetail = new RegistrationDetail();
        jobSeekerDetail.setId(1l);
        applyJOB.setRegistrationDetail(jobSeekerDetail);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date applyDate = new Date();
        applyJOB.setApplyDate(df.format(applyDate));
        recruiterJobSeekerDAO.applyForJOB(applyJOB);
    }

    @Test
    public void testCheckAppliedForJob() {
        ApplyJOB applyJOB = new ApplyJOB();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(8l);
        applyJOB.setPostJobDetail(postJobDetail);
        RegistrationDetail jobSeekerDetail = new RegistrationDetail();
        jobSeekerDetail.setId(1l);
        applyJOB.setRegistrationDetail(jobSeekerDetail);
        List<ApplyJOB> existJobSeeker = recruiterJobSeekerDAO.checkAppliedForJob(applyJOB);
        assertTrue(existJobSeeker.size() > 0);
    }

    @Test
    public void testCheckAppliedNotForJob() {
        ApplyJOB applyJOB = new ApplyJOB();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(8l);
        applyJOB.setPostJobDetail(postJobDetail);
        RegistrationDetail jobSeekerDetail = new RegistrationDetail();
        jobSeekerDetail.setId(2l);
        applyJOB.setRegistrationDetail(jobSeekerDetail);
        List<ApplyJOB> existJobSeeker = recruiterJobSeekerDAO.checkAppliedForJob(applyJOB);
        assertTrue(existJobSeeker.size() == 0);
    }

    @Test
    public void testAppliedJobsList() {
        List<Object[]> jobAppliedList = recruiterJobSeekerDAO.appliedJobsList(8l);
        assertTrue(jobAppliedList.size() > 0);
    }

    @Test
    public void testNotAppliedJobsList() {
        List<Object[]> jobAppliedList = recruiterJobSeekerDAO.appliedJobsList(8l);
        assertTrue(jobAppliedList.size() == 0);
    }

    @Test
    public void testLoadCategoryList() {
        List<Category> categories = recruiterJobSeekerDAO.loadCategoryList();
        assertTrue(categories.size() > 0);
    }

    public void testLoadSkills() {
        List<Object[]> skills = recruiterJobSeekerDAO.loadSkills(4l);
        assertTrue(skills.size() > 0);
    }


}