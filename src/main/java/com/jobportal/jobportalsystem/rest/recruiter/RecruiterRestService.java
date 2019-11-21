package com.jobportal.jobportalsystem.rest.recruiter;

import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.service.recruiter.RecruiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate formatDateTime = LocalDate.parse(postJobDetailDTO.getJob_opening_date(), formatter);
//        String job_opening_date = formatDateTime.format(formatter);
//        postJobDetailDTO.setJob_opening_date(job_opening_date);
        LOGGER.info("===postJobDetailDTO====" + postJobDetailDTO);
        recruiterService.postJobDetail(postJobDetailDTO);
        return ResponseEntity.status(HttpStatus.OK).body("userProfile");
    }


    @POST
    @Produces("application/json")
    @Path("jobDetails")
    public ResponseEntity fetchJobDetails(String user_id) {
        LOGGER.info("recruiter_id==" + user_id);
        List<PostJobDetailDTO> postJobDetailDTOS = recruiterService.fetchJobDetails(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(postJobDetailDTOS);
    }


}

