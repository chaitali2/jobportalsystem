package com.jobportal.jobportalsystem.service.recruiter;

import com.jobportal.jobportalsystem.dao.recruiter.RecruiterDAO;
import com.jobportal.jobportalsystem.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class RecruiterServiceTest {


    @MockBean
    RecruiterDAO recruiterDAO;

    @MockBean
    Utility utility;


}