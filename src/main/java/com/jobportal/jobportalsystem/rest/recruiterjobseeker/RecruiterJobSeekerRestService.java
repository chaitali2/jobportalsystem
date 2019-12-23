package com.jobportal.jobportalsystem.rest.recruiterjobseeker;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.CategoryDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiterjobseeker.RecruiterJobSeekerService;
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

    @Autowired
    private RecruiterJobSeekerService recruiterService;

// ************************************ SAVE POST JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/add-job-posts")
    public ResponseEntity postJobDetail(@Valid PostJobDetailDTO postJobDetailDTO) throws ParseException {
        recruiterService.postJobDetail(postJobDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Success fully post data");
    }
    // ************************************ CATEGORY FOR WHICH POST APPLY *******************************************//

    @GET
    @Produces("application/json")
    @Path("recruiter/load-category")
    public ResponseEntity loadCategoryList() {
        List<CategoryDTO> categoryDTOList = recruiterService.loadCategoryList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }

    // ************************************ SKILLS WHICH IS REQUIRED  *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/load-skill")
    public ResponseEntity loadSkills(Map<String, Long> keyvalue) {
        List<SkillDTO> skillDTOList = recruiterService.loadSkills(keyvalue.get("categoryId"));
        ResponseEntity<List<SkillDTO>> body = ResponseEntity.status(HttpStatus.OK).body(skillDTOList);
        return body;
    }

    // ************************************ JOBS LIST FOR RECRUITER AND JOB SEEKER *******************************************//
    @POST
    @Produces("application/json")
    @Path("jobdetails")
    public ResponseEntity fetchJobDetails(Map<String, Long> keyValue){
        List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(keyValue);
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }

    // ************************************ FETCH SINGLE JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("jobseeker/jobdetailofcompany")
    public ResponseEntity fetchJobDetailsOfCompany(Map<String, Long> keyvalue) throws ParseException {
        PostJobDetailDTO postJobDetailDTOS = recruiterService.fetchJobDetailsOfCompany(keyvalue.get("jobId"));
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }

    // ************************************ DELETE JOB DETAIL *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/remove-jobpost")
    public ResponseEntity removeJobPostDetail(Map<String, Long> keyvalue) {
        recruiterService.removeJobPostDetail(keyvalue.get("job_id"));
        return ResponseEntity.status(HttpStatus.OK).body("Job Deleted!!");

    }

    // ************************************ APPLY FOR JOB *******************************************//
    @POST
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("jobseeker/applyfor-job")
    public ResponseEntity applyForJOB(@FormDataParam("file") InputStream fileInputStream,
                                      @FormDataParam("file") FormDataContentDisposition fileMetaData,
                                      @FormDataParam("job_id") String jobId,
                                      @FormDataParam("user_id") String userId) throws UserExistException {
        recruiterService.applyForJOB(fileInputStream, fileMetaData, jobId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("success");

    }

    // ************************************ JOB LIST WHICH IS APPLIED BY USER *******************************************//
    @POST
    @Produces("application/json")
    @Path("recruiter/view-jobs-applied")
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

    @POST
    @Path("categorys-skill")
    @Produces("application/json")
    public ResponseEntity inserSkillsWiseCategory(CategoryDTO categoryDTO) {
        recruiterService.inserSkillsWiseCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Success fully insert category!");
    }
}

