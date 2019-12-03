package com.jobportal.jobportalsystem.rest.recruiter;

import com.jobportal.jobportalsystem.dto.ErrorDTO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/jobportal/recruiter")
public class RecruiterRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterRestService.class);

    @Autowired
    RecruiterService recruiterService;

    @POST
    @Produces("application/json")
    @Path("addjob_posts")
    public ResponseEntity postJobDetail(@Valid PostJobDetailDTO postJobDetailDTO) {
        LOGGER.info("===postJobDetailDTO====" + postJobDetailDTO);
        recruiterService.postJobDetail(postJobDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Success fully post data");

    }

    @GET
    @Produces("application/json")
    @Path("loadCategory")
    public ResponseEntity getCategory() {
        List<CategoryDTO> categoryDTOList = recruiterService.getCategory();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);

    }

    @POST
    @Produces("application/json")
    @Path("loadskill")
    public ResponseEntity loadSkills(String categoryId) {
        LOGGER.info("category id=" + categoryId);
        List<SkillDTO> skillDTOList = recruiterService.loadSkills(categoryId);
        LOGGER.info("skillDTOList=" + skillDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(skillDTOList);
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
    public ResponseEntity fetchJobDetailsOfCompany(String job_id) throws ParseException {
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
        }
    }


    @POST
    @Produces("application/json")
    @Path("viewJobsApplied")
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


}

