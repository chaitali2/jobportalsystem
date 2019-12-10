package com.jobportal.jobportalsystem.dao.recruiter;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecruiterDAOTest {

    @Autowired
    RecruiterJobSeekerDAO recruiterDAO;
    PostJobDetail postJobDetail;

    @Before
    public void setUp() {
        prepareData();
    }


    public void prepareData() {
        postJobDetail = new PostJobDetail();
    }

    @Test
    public void testSaveJobPostDetail() {
        recruiterDAO.saveJobPostDetail(postJobDetail);
    }

    @Test
    public void testFetchJobDetails() {
        Map<String, Long> keyValue = new HashMap<>();
        keyValue.put("recruiter_id", 4l);
        List<PostJobDetail> jobDetail = recruiterDAO.fetchJobDetails(keyValue);
    }

    @Test
    public void testFetchJobDetailsOfCompany() {
        PostJobDetail jobDetail = recruiterDAO.fetchJobDetailsOfCompany(4l);
    }

    @Test
    public void testRemoveJobPostDetail() {
        recruiterDAO.removeJobPostDetail(4l);
    }

    @Test
    public void testApplyForJOB() {
        ApplyJOB applyJOB = new ApplyJOB();
        recruiterDAO.applyForJOB(applyJOB);
    }

    @Test
    public void testCheckAppliedForJob() {
        ApplyJOB applyJOB = new ApplyJOB();
        List<ApplyJOB> applyJOBList = recruiterDAO.checkAppliedForJob(applyJOB);
    }

    @Test
    public void testAppliedJobsList() {
        List<Object[]> appliedJobsList = recruiterDAO.appliedJobsList(4l);
    }

    @Test
    public void testLoadCategoryList() {
        List<Category> categories = recruiterDAO.loadCategoryList();
    }
}