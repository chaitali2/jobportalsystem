package com.jobportal.jobportalsystem.rest.other;

import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.service.other.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/other")
public class OtherRestService {

    @Autowired
    OtherService otherService;

    @POST
    @Path("category")
    @Produces("application/json")
    public ResponseEntity inserCategory(CategoryDTO categoryDTO){
            otherService.insertCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Success fully insert category!");
    }


    @POST
    @Path("categoryskill")
    @Produces("application/json")
    public ResponseEntity inserSkillsWiseCategory(CategoryDTO categoryDTO){
            otherService.inserSkillsWiseCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Success fully insert category!");
    }
}
