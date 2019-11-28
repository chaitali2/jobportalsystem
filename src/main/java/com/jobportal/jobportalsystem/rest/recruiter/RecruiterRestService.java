package com.jobportal.jobportalsystem.rest.recruiter;

import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/recruiter")
public class RecruiterRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterRestService.class);

    @Autowired
    RecruiterService recruiterService;

    @POST
    @Produces("application/json")
    @Path("post_jobs")
    public ResponseEntity postJobDetail(PostJobDetailDTO postJobDetailDTO) {
        LOGGER.info("===postJobDetailDTO====" + postJobDetailDTO);
        try {
            recruiterService.postJobDetail(postJobDetailDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Success fully post data");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }


    @POST
    @Produces("application/json")
    @Path("jobDetails")
    public ResponseEntity fetchJobDetails(String user_id) {
        LOGGER.info("recruiter_id==" + user_id);
        try {
            List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(user_id);
            return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @POST
    @Produces("application/json")
    @Path("jobdetailofcompany")
    public ResponseEntity fetchJobDetailsOfCompany(String job_id) {
        LOGGER.info("job_id==" + job_id);
        PostJobDetailDTO postJobDetailDTOS = recruiterService.fetchJobDetailsOfCompany(job_id);
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }

    @POST
    @Produces("application/json")
    @Path("removejobpost")
    public ResponseEntity removeJobPostDetail(String job_id) {
        try {
            LOGGER.info("job_id==" + job_id);
            recruiterService.removeJobPostDetail(job_id);
            return ResponseEntity.status(HttpStatus.OK).body("Job Deleted!!");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }

    @POST
    @Produces("application/json")
    @Path("userdetails")
    public ResponseEntity fetchUserDetails(String user_id) {
        LOGGER.info("recruiter_id==" + user_id);
        RegistrationDetailDTO registrationDetailDTO = recruiterService.fetchUserDetails(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(registrationDetailDTO);
    }

    @POST
    @Produces("application/json")
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("applyforjob")
    public ResponseEntity applyForJOB(ApplyJobDTO applyJobDTO) {
        try {
            LOGGER.info("======apply job=========" + applyJobDTO);
            recruiterService.applyForJOB(applyJobDTO);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            LOGGER.error("error==" + e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(errorDTO);
        }
    }


    @POST
    @Produces("application/json")
    @Path("appliedJobs")
    public ResponseEntity appliedJobsList(String job_id) {
        try {
            List jobseeeker = recruiterService.appliedJobsList(job_id);
            return ResponseEntity.status(HttpStatus.OK).body(jobseeeker);
        } catch (Exception e) {
            LOGGER.error("error==" + e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(errorDTO);
        }
    }

    @GET
    @Produces("application/json")
    @Path("categories")
    public ResponseEntity getCategory() {
        try {
            List<CategoryDTO> categoryDTOList = recruiterService.getCategory();
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
        } catch (Exception e) {
            LOGGER.error("error==" + e.getMessage());
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(errorDTO);
        }
    }

}

