package com.jobportal.jobportalsystem.dao.recruiter;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Skill> skillList = new ArrayList<>();
        Skill skill1 = new Skill();
        skill1.setId(2l);
        Skill skill2 = new Skill();
        skill2.setId(3l);
//        skillList.add(skill1);
        skillList.add(skill2);
        postJobDetail.setSkillList(skillList);
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
    public void testSaveJobPostDetail() {
        LOGGER.info("postJobDetail=" + postJobDetail);
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
    }

    @Test
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
        Map<String, Long> keyValue = new HashMap<>();
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetails(keyValue);
        assertTrue(postJobDetails.isEmpty());
    }

    @Test
    public void testFetchJobDetailsOfCompany() {
        PostJobDetail jobDetail = recruiterJobSeekerDAO.fetchJobDetailsOfCompany(6l);
        LOGGER.info("jobDetail==" + jobDetail);
        assertNull(jobDetail);
        assertEquals("Infosys", jobDetail.getCompany());
    }

    @Test
    public void testRemoveJobPostDetail() {
        recruiterJobSeekerDAO.removeJobPostDetail(6l);
    }

    @Test
    public void testApplyForJOB(){
        ApplyJOB applyJOB=new ApplyJOB();
        applyJOB.setFilename("7.pdf");
        PostJobDetail postJobDetail=new PostJobDetail();
        postJobDetail.setId(6l);
        applyJOB.setPostJobDetail(postJobDetail);
        RegistrationDetail jobSeekerDetail=new RegistrationDetail();
        jobSeekerDetail.setId(1l);
        postJobDetail.setRegistrationDetail(jobSeekerDetail);
        recruiterJobSeekerDAO.applyForJOB(applyJOB);
    }


}