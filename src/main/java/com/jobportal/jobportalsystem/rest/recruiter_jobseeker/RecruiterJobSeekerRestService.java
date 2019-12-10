package com.jobportal.jobportalsystem.rest.recruiter_jobseeker;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterJobSeekerService;
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
import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Path("/jobportal")
public class RecruiterJobSeekerRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterJobSeekerRestService.class);

    @Autowired
    RecruiterJobSeekerService recruiterService;

    @POST
    @Produces("application/json")
    @Path("recruiter/addjob_posts")
    public ResponseEntity postJobDetail(@Valid PostJobDetailDTO postJobDetailDTO) throws ParseException {
        LOGGER.info("===postJobDetailDTO====" + postJobDetailDTO);
        recruiterService.postJobDetail(postJobDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Success fully post data");

    }

    @GET
    @Produces("application/json")
    @Path("recruiter/loadCategory")
    public ResponseEntity loadCategoryList() {
        List<CategoryDTO> categoryDTOList = recruiterService.loadCategoryList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }

    @POST
    @Produces("application/json")
    @Path("recruiter/loadskill")
    public ResponseEntity loadSkills(Map<String, Long> keyvalue) {
        LOGGER.info("category id=" + keyvalue);
        List<SkillDTO> skillDTOList = recruiterService.loadSkills(keyvalue.get("category_id"));
        LOGGER.info("skillDTOList=" + skillDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(skillDTOList);
    }

    @POST
    @Produces("application/json")
    @Path("jobDetails")
    public ResponseEntity fetchJobDetails(Map<String, Long> keyValue) throws ParseException {
        List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(keyValue);
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);

    }

    @POST
    @Produces("application/json")
    @Path("jobseeker/jobdetailofcompany")
    public ResponseEntity fetchJobDetailsOfCompany(Map<String, Long> keyvalue) throws ParseException {
        LOGGER.info("job_id==" + keyvalue.get("job_id"));
        PostJobDetailDTO postJobDetailDTOS = recruiterService.fetchJobDetailsOfCompany(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }

    @POST
    @Produces("application/json")
    @Path("recruiter/removejobpost")
    public ResponseEntity removeJobPostDetail(Map<String, Long> keyvalue) {
        LOGGER.info("job_id==" + keyvalue.get("job_id"));
        recruiterService.removeJobPostDetail(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body("Job Deleted!!");

    }

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

    @POST
    @Produces("application/json")
    @Path("recruiter/viewJobsApplied")
    public ResponseEntity appliedJobsList(Map<String, Long> keyvalue) {

        List<ApplyJobDTO> applyJobDTOList = recruiterService.appliedJobsList(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body(applyJobDTOList);

    }

    @POST
    @Path("recruiter/download/pdf")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response downloadPdfFile(Map<String,String> keyValue) {
       return recruiterService.downloadPdf(keyValue.get("filename"));
    }

}

