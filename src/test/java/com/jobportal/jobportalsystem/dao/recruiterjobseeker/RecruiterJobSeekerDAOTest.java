package com.jobportal.jobportalsystem.dao.recruiterjobseeker;

import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Skill;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.ApplyJob;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.PostJobDetail;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecruiterJobSeekerDAOTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private RecruiterJobSeekerDAO recruiterJobSeekerDAO;
    private PostJobDetail postJobDetail;
    private RegistrationDetail registrationDetail;

    @Before
    public void setUp() {
        prepareData();
    }

    private void registerPrepareData() {
        registrationDetail = new RegistrationDetail();
        registrationDetail.setFirstName("chaitali");
        registrationDetail.setLastName("Khachane");
        registrationDetail.setDateOfBirth(new Date());
        registrationDetail.setEmailId("xyz@gmail.com");
        registrationDetail.setMobileNo("8866049741");
        registrationDetail.setPassword("+C8168xm8tONJIvVO1STKSfoek5SNIqSEURNpiGjo=");
        registrationDetail.setUserType(RegistrationDetail.user.R);
        registrationDetail.setUsername("xyz@gmail.com");
        registrationDetail.setSalt("Lpm28h5myQheIFNflzA7oaB1bFGSCn");
        entityManager.persist(registrationDetail);
    }

    public void prepareData() {
        registerPrepareData();
        postJobDetail = new PostJobDetail();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        Query query = entityManager.createQuery("Select d.id " +
                "from RegistrationDetail d " +
                "where d.emailId=:emailid");

        query.setParameter("emailid", "xyz@gmail.com");
        Long userId = Long.parseLong(query.getSingleResult().toString());

        registrationDetail.setId(userId);
        postJobDetail.setRegistrationDetail(registrationDetail);
        postJobDetail.setCompany("Infosys");
        Category category = new Category();
        category.setCategoryName("Tester");
        Skill skill = new Skill();
        skill.setSkillName("Manual");
        skill.setSkillName("Selinium");
        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(skill);
        category.setSkills(skillSet);
        entityManager.persist(category);
        postJobDetail.setCategory(category);
        postJobDetail.setSkillSet(skillSet);
        postJobDetail.setExperience(2.0);
        postJobDetail.setJobType(PostJobDetail.Job.P);
        postJobDetail.setSalaryOffer(4.0);
        postJobDetail.setDescription("Front end developer");
        postJobDetail.setJobOpeningDate(new Date());
        JobLocation jobLocation = new JobLocation();
        jobLocation.setState("MH");
        jobLocation.setStreet("Business bay,Yerwada");
        jobLocation.setCity("Pune");
        jobLocation.setPincode("396230");
        postJobDetail.setJobLocation(jobLocation);
    }


    @Test
    public void testSaveJobPostDetail() {
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("userId", postJobDetail.getRegistrationDetail().getId());
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetailsForRecruiter(keyValue);
        assertNotNull(postJobDetails);
        assertFalse(postJobDetails.isEmpty());
        assertEquals(1, postJobDetails.size());
        assertEquals(keyValue.get("userId"), postJobDetails.get(0).getRegistrationDetail().getId());
        assertEquals("Infosys", postJobDetails.get(0).getCompany());
        assertEquals("Tester", postJobDetails.get(0).getCategory().getCategoryName());
        assertEquals("P", postJobDetails.get(0).getJobType().toString());
        assertEquals(2.0, postJobDetails.get(0).getExperience(), 0.0);
        assertEquals("Front end developer", postJobDetails.get(0).getDescription());
        assertEquals(4.0, postJobDetails.get(0).getSalaryOffer(), 0.0);
        assertEquals("Pune", postJobDetails.get(0).getJobLocation().getCity());
        assertEquals("MH", postJobDetails.get(0).getJobLocation().getState());
        assertEquals("Business bay,Yerwada", postJobDetails.get(0).getJobLocation().getStreet());
    }

    @Test
    public void testfetchJobDetails() {
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("userId", postJobDetail.getRegistrationDetail().getId());
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetailsForRecruiter(keyValue);
        assertNotNull(postJobDetails);
        assertFalse(postJobDetails.isEmpty());
        assertEquals(1, postJobDetails.size());
        assertEquals(keyValue.get("userId"), postJobDetails.get(0).getRegistrationDetail().getId());
        assertEquals("Infosys", postJobDetails.get(0).getCompany());
        assertEquals("Tester", postJobDetails.get(0).getCategory().getCategoryName());
        assertEquals("P", postJobDetails.get(0).getJobType().toString());
        assertEquals(2.0, postJobDetails.get(0).getExperience(), 0.0);
        assertEquals("Front end developer", postJobDetails.get(0).getDescription());
        assertEquals(4.0, postJobDetails.get(0).getSalaryOffer(), 0.0);
        assertEquals("Pune", postJobDetails.get(0).getJobLocation().getCity());
        assertEquals("MH", postJobDetails.get(0).getJobLocation().getState());
        assertEquals("Business bay,Yerwada", postJobDetails.get(0).getJobLocation().getStreet());
    }

    @Test
    public void testFetchJobDetailsForJobSeeker() {
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
        List<PostJobDetail> postJobDetails = recruiterJobSeekerDAO.fetchJobDetailsForJobSeeker();
        assertNotNull(postJobDetails);
        assertFalse(postJobDetails.isEmpty());
        assertEquals(1, postJobDetails.size());
    }

    @Test
    public void testFetchJobDetailsOfCompany() {
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
        Query query = entityManager.createQuery("Select pj.id " +
                "from  PostJobDetail pj " +
                "where pj.registrationDetail.id=:userId");
        query.setParameter("userId", registrationDetail.getId());
        PostJobDetail jobDetail = recruiterJobSeekerDAO.fetchJobDetailsOfCompany(
                Long.valueOf(query.getSingleResult().toString()));
        assertEquals("Infosys", jobDetail.getCompany());
        assertEquals(2.0, jobDetail.getExperience(), 0.0);
        assertEquals(4.0, jobDetail.getSalaryOffer(), 0.0);
        assertEquals("Front end developer", jobDetail.getDescription());
        assertEquals("Pune", jobDetail.getJobLocation().getCity());
        assertEquals("MH", jobDetail.getJobLocation().getState());
        assertEquals("Business bay,Yerwada", jobDetail.getJobLocation().getStreet());

    }

    @Test
    public void testRemoveJobPostDetail() {
        recruiterJobSeekerDAO.saveJobPostDetail(postJobDetail);
        Query query = entityManager.createQuery("Select pj.id " +
                "from  PostJobDetail pj " +
                "where pj.registrationDetail.id=:userId");
        query.setParameter("userId", registrationDetail.getId());
        Long jobId = Long.valueOf(query.getSingleResult().toString());
        recruiterJobSeekerDAO.removeJobPostDetail(jobId);
        PostJobDetail postJobDetail = entityManager.find(PostJobDetail.class, jobId);
        assertNull(postJobDetail);
    }
}