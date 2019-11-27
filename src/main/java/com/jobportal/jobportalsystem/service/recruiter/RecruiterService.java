package com.jobportal.jobportalsystem.service.recruiter;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.recruiter.RecruiterDAO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruiterService {

    @Autowired
    RecruiterDAO recruiterDAO;


    @Transactional(propagation = Propagation.REQUIRED)
    public void postJobDetail(PostJobDetailDTO postJobDetailDTO) {
        recruiterDAO.saveJobPostDetail(convertDTOtoModel(postJobDetailDTO));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RegistrationDetailDTO fetchUserDetails(String user_id) {
        RegistrationDetailDTO userDetailsDTO = new RegistrationDetailDTO();
        RegistrationDetail userDetails = recruiterDAO.fetchUserDetails(user_id);
        userDetailsDTO = convertDTOtoModel(userDetails);
        return userDetailsDTO;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void applyForJOB(ApplyJobDTO applyJobDTO) throws UserExistException {

        List<ApplyJOB> existJobSeeker = recruiterDAO.checkAppliedForJob(convertDTOtoModel(applyJobDTO));
        if(existJobSeeker.size()==0) {
            recruiterDAO.applyForJOB(convertDTOtoModel(applyJobDTO));
        }else{
            throw new UserExistException("Already applied for job");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List appliedJobsList(String job_id) throws UserExistException {

       return recruiterDAO.appliedJobsList(job_id);

    }


    ApplyJOB convertDTOtoModel(ApplyJobDTO applyJobDTO) {
        ApplyJOB applyJOB = new ApplyJOB();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(Long.parseLong(applyJobDTO.getJob_id()));
        applyJOB.setPostJobDetail(postJobDetail);

        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(applyJobDTO.getUser_id()));
        applyJOB.setRegistrationDetail(registrationDetail);
        return applyJOB;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<PostJobDetailDTO> fetchJobDetails(String user_id) {
        List<PostJobDetail> jobDetails = recruiterDAO.fetchJobDetails(user_id);
        List<PostJobDetailDTO> jobDetailDTOS = new ArrayList<>();

        for (int i = 0; i < jobDetails.size(); i++) {
            PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
            postJobDetailDTO.setId(jobDetails.get(i).getId());
            postJobDetailDTO.setCompany(jobDetails.get(i).getCompany());
            postJobDetailDTO.setCategory(jobDetails.get(i).getCategory());
            String jobType = ("P").equals(jobDetails.get(i).getJob_type()) ? "Permanent" : "Contract";
            postJobDetailDTO.setJob_type(jobType);
            postJobDetailDTO.setExperience(jobDetails.get(i).getExperience());
            postJobDetailDTO.setSalary_offer(jobDetails.get(i).getSalary_offer());
            postJobDetailDTO.setStreet_add(jobDetails.get(i).getJobLocation().getStreet_add());
            postJobDetailDTO.setCity(jobDetails.get(i).getJobLocation().getCity());
            postJobDetailDTO.setState(jobDetails.get(i).getJobLocation().getState());
            postJobDetailDTO.setPincode(jobDetails.get(i).getJobLocation().getPincode());
            postJobDetailDTO.setJob_opening_date(jobDetails.get(i).getJob_opening_date());
            postJobDetailDTO.setDescription(jobDetails.get(i).getDescription());
            postJobDetailDTO.setSkills(jobDetails.get(i).getSkills());
            jobDetailDTOS.add(postJobDetailDTO);
        }
        return jobDetailDTOS;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public PostJobDetailDTO fetchJobDetailsOfCompany(String user_id) {
        PostJobDetail jobDetails = recruiterDAO.fetchJobDetailsOfCompany(user_id);

        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setId(jobDetails.getId());
        postJobDetailDTO.setCompany(jobDetails.getCompany());
        postJobDetailDTO.setCategory(jobDetails.getCategory());
        String jobType = jobDetails.getJob_type().equals("P") ? "Permanent" : "Contract";
        postJobDetailDTO.setJob_type(jobType);
        postJobDetailDTO.setExperience(jobDetails.getExperience());
        postJobDetailDTO.setSalary_offer(jobDetails.getSalary_offer());
        postJobDetailDTO.setStreet_add(jobDetails.getJobLocation().getStreet_add());
        postJobDetailDTO.setCity(jobDetails.getJobLocation().getCity());
        postJobDetailDTO.setState(jobDetails.getJobLocation().getState());
        postJobDetailDTO.setPincode(jobDetails.getJobLocation().getPincode());
        postJobDetailDTO.setJob_opening_date(jobDetails.getJob_opening_date());
        postJobDetailDTO.setDescription(jobDetails.getDescription());
        postJobDetailDTO.setSkills(jobDetails.getSkills());
        return postJobDetailDTO;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void removeJobPostDetail(String user_id) {
        recruiterDAO.removeJobPostDetail(user_id);
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

    RegistrationDetailDTO convertDTOtoModel(RegistrationDetail registrationDetail) {
        RegistrationDetailDTO registrationDetailDTO = new RegistrationDetailDTO();
        registrationDetailDTO.setFirstname(registrationDetail.getFirstname());
        registrationDetailDTO.setLastname(registrationDetail.getLastname());
        registrationDetailDTO.setEmailid(registrationDetail.getEmailid());
        registrationDetailDTO.setDob(registrationDetail.getDob());
        registrationDetailDTO.setCity(registrationDetail.getCity());
        registrationDetailDTO.setState(registrationDetail.getState());
        registrationDetailDTO.setMobno(registrationDetail.getMobno());
        return registrationDetailDTO;
    }
}
