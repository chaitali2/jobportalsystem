package com.jobportal.jobportalsystem.service.recruiter;

import com.jobportal.jobportalsystem.dao.recruiter.RecruiterDAO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.model.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecruiterService {

    @Autowired
    RecruiterDAO recruiterDAO;

    @Transactional(propagation = Propagation.REQUIRED)
    public void postJobDetail(PostJobDetailDTO postJobDetailDTO) {
        recruiterDAO.saveJobPostDetail(convertDTOtoModel(postJobDetailDTO));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void fetchJobDetail(String recruiter_id) {
        recruiterDAO.fetchJobDetail(recruiter_id);

    }

    PostJobDetail convertDTOtoModel(PostJobDetailDTO postJobDetailDTO) {
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setCategory(postJobDetailDTO.getCategory());
        postJobDetail.setCompany(postJobDetailDTO.getCompany());

        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(postJobDetailDTO.getPosted_by_id());
        postJobDetail.setRegistrationDetail(registrationDetail);
        postJobDetail.setJob_type(postJobDetailDTO.getJob_type());
        postJobDetail.setExperience(postJobDetailDTO.getExperience());
        postJobDetail.setSalary_offer(postJobDetailDTO.getSalary_offer());
        postJobDetail.setJob_opening_date(postJobDetailDTO.getJob_opening_date());
        postJobDetail.setDescription(postJobDetailDTO.getDescription());

        JobLocation jobLocation = new JobLocation();
        jobLocation.setStreet_add(postJobDetailDTO.getStreet_add());
        jobLocation.setCity(postJobDetailDTO.getCity());
        jobLocation.setState(postJobDetailDTO.getState());
        jobLocation.setPincode(postJobDetailDTO.getPincode());
        postJobDetail.setJobLocation(jobLocation);

        postJobDetail.setSkills(postJobDetailDTO.getSkills());

        return postJobDetail;
    }
}
