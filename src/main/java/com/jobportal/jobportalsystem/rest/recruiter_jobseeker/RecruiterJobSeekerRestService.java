package com.jobportal.jobportalsystem.rest.recruiter_jobseeker;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiter_jobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter_jobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter_jobseeker.RecruiterJobSeekerService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Path("/jobportal")
public class RecruiterJobSeekerRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterJobSeekerRestService.class);

    @Autowired
    RecruiterJobSeekerService recruiterService;

// ************************************ SAVE POST JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/addjob_posts")
    public ResponseEntity postJobDetail(@Valid PostJobDetailDTO postJobDetailDTO) throws ParseException {
        recruiterService.postJobDetail(postJobDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Success fully post data");

    }

    // ************************************ CATEGORY FOR WHICH POST APPLY *******************************************//

    @GET
    @Produces("application/json")
    @Path("recruiter/loadCategory")
    public ResponseEntity loadCategoryList() {
        List<CategoryDTO> categoryDTOList = recruiterService.loadCategoryList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }

    // ************************************ SKILLS WHICH IS REQUIRED  *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/loadskill")
    public ResponseEntity loadSkills(Map<String, Long> keyvalue) {
        List<SkillDTO> skillDTOList = recruiterService.loadSkills(keyvalue.get("category_id"));
        LOGGER.info("skillDTOList=" + skillDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(skillDTOList);
    }


    // ************************************ JOBS LIST FOR RECRUITER AND JOB SEEKER *******************************************//
    @POST
    @Produces("application/json")
    @Path("jobDetails")
    public ResponseEntity fetchJobDetails(Map<String, Long> keyValue) throws ParseException {
        List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(keyValue);
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);

    }

    // ************************************ FETCH SINGLE JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("jobseeker/jobdetailofcompany")
    public ResponseEntity fetchJobDetailsOfCompany(Map<String, Long> keyvalue) throws ParseException {
        PostJobDetailDTO postJobDetailDTOS = recruiterService.fetchJobDetailsOfCompany(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }

    // ************************************ DELETE JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/removejobpost")
    public ResponseEntity removeJobPostDetail(Map<String, Long> keyvalue) {
        recruiterService.removeJobPostDetail(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body("Job Deleted!!");

    }


    // ************************************ APPLY FOR JOB *******************************************//
    @POST
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("jobseeker/applyforjob")
    public ResponseEntity applyForJOB(@FormDataParam("file") InputStream fileInputStream,
                                      @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                      @FormDataParam("job_id") String job_id,
                                      @FormDataParam("user_id") String user_id) throws UserExistException {
        recruiterService.applyForJOB(fileInputStream, fileMetaData, job_id, user_id);
        return ResponseEntity.status(HttpStatus.OK).body("success");

    }

    // ************************************ JOB LIST WHICH IS APPLIED BY USER *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/viewJobsApplied")
    public ResponseEntity appliedJobsList(Map<String, Long> keyvalue) {

        List<ApplyJobDTO> applyJobDTOList = recruiterService.appliedJobsList(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body(applyJobDTOList);

    }

    // ************************************ DOWNLOAD THE RESUME *******************************************//
    @POST
    @Path("recruiter/download/pdf")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response downloadPdfFile(Map<String, String> keyValue) {
        return recruiterService.downloadPdf(keyValue.get("filename"));
    }

}

