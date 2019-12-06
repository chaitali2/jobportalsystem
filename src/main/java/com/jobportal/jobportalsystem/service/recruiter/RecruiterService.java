package com.jobportal.jobportalsystem.service.recruiter;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.recruiter.RecruiterDAO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiter.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
import com.jobportal.jobportalsystem.dto.registration.RegistrationDetailDTO;
import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.Utility;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecruiterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterService.class);

    @Autowired
    RecruiterDAO recruiterDAO;

    @Autowired
    Utility utility;

    public void postJobDetail(PostJobDetailDTO postJobDetailDTO) throws ParseException {
        recruiterDAO.saveJobPostDetail(convertDTOtoModel(postJobDetailDTO));
    }


    public void applyForJOB(InputStream fileInputStream,
                            FormDataContentDisposition fileMetaData,
                            String job_id,
                            String user_id) throws UserExistException {

        ApplyJOB applyJOB = new ApplyJOB();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(Long.parseLong(job_id));
        applyJOB.setPostJobDetail(postJobDetail);

        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(user_id));
        applyJOB.setRegistrationDetail(registrationDetail);
        File file = new File("F:/resume/" + fileMetaData.getFileName());
        File newFile = new File("F:/resume/" + job_id + ".pdf");
        boolean isRename = file.renameTo(newFile);
        applyJOB.setFilename(file.getName());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date applyDate = new Date();
        applyJOB.setApplyDate(df.format(applyDate));

        List<ApplyJOB> existJobSeeker = recruiterDAO.checkAppliedForJob(applyJOB);

        if (existJobSeeker.size() == 0) {
            uploadResume(fileInputStream, fileMetaData, job_id);

            recruiterDAO.applyForJOB(applyJOB);
        } else {
            throw new UserExistException("Already applied for job");
        }
    }

    public void uploadResume(InputStream fileInputStream,
                             FormDataContentDisposition fileMetaData, String job_id) {
        LOGGER.info("hello upload resume");
        String UPLOAD_PATH = "F:/resume/";
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            File file = new File(UPLOAD_PATH + fileMetaData.getFileName());
            OutputStream out = null;
                out = new FileOutputStream(file);

            LOGGER.info("hello upload resume 2");

            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }

    }

    public List appliedJobsList(Long job_id) {
        List<Object[]> jobAppliedList = recruiterDAO.appliedJobsList(job_id);
        List<ApplyJobDTO> applyJobDTOList = new ArrayList<>();
        for (Object[] details : jobAppliedList) {
            ApplyJobDTO applyJobDTO = new ApplyJobDTO();
            applyJobDTO.setFirstname(details[0].toString());
            applyJobDTO.setLastname(details[1].toString());
            applyJobDTO.setCompany(details[2].toString());
            applyJobDTO.setDescription(details[3].toString());
            applyJobDTO.setApplyDate(details[4].toString());
            applyJobDTO.setFileName(details[5].toString());
            LOGGER.info("applyJobDTO=" + applyJobDTO);
            applyJobDTOList.add(applyJobDTO);
        }
        LOGGER.info("applyJobDTOList==" + applyJobDTOList);
        return applyJobDTOList;
    }

    public List<CategoryDTO> loadCategoryList() {
        List<Category> categories = recruiterDAO.loadCategoryList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategory_id(categories.get(i).getId());
            categoryDTO.setCategoryName(categories.get(i).getCategoryName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public List<SkillDTO> loadSkills(Long categoryId) {
        List<Object[]> skills = recruiterDAO.loadSkills(categoryId);
        List<SkillDTO> skillList = new ArrayList<>();
        for (Object[] a : skills) {
            SkillDTO skill = new SkillDTO();
            skill.setSkill_id(Long.parseLong(a[0].toString()));
            skill.setSkill_name(a[1].toString());
            skillList.add(skill);
        }
        return skillList;
    }

    public List<PostJobDetailDTO> fetchJobDetails(Map<String, Long> keyValue) throws ParseException {
        List<PostJobDetail> jobDetails = recruiterDAO.fetchJobDetails(keyValue);

        LOGGER.info("jobDetail===" + (jobDetails));
        if (jobDetails != null && !jobDetails.isEmpty()) {
            List<PostJobDetailDTO> jobDetailDTOS = new ArrayList<>();
            for (int i = 0; i < jobDetails.size(); i++) {
                PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
                postJobDetailDTO.setId(jobDetails.get(i).getId());
                postJobDetailDTO.setCompany(jobDetails.get(i).getCompany());
                postJobDetailDTO.setCategory_name(jobDetails.get(i).getCategory().getCategoryName());
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
//                postJobDetailDTO.setSkills(jobDetails.get(i).getSkills());
                jobDetailDTOS.add(postJobDetailDTO);
            }
            LOGGER.info("jobDetailDTOS==" + jobDetailDTOS);
            return jobDetailDTOS;
        } else {
            throw new RuntimeException("Job details not found !");
        }
    }


    public PostJobDetailDTO fetchJobDetailsOfCompany(Long user_id) throws ParseException {
        PostJobDetail jobDetails = recruiterDAO.fetchJobDetailsOfCompany(user_id);

        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setId(jobDetails.getId());
        postJobDetailDTO.setCompany(jobDetails.getCompany());
        postJobDetailDTO.setCategory_name(jobDetails.getCategory().getCategoryName());
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
//        postJobDetailDTO.setSkills(jobDetails.getSkills());
        return postJobDetailDTO;
    }


    public void removeJobPostDetail(Long user_id) {
        recruiterDAO.removeJobPostDetail(user_id);
    }

    PostJobDetail convertDTOtoModel(PostJobDetailDTO postJobDetailDTO) throws ParseException {
        PostJobDetail postJobDetail = new PostJobDetail();
//        postJobDetail.setCategory(postJobDetailDTO.getCategory_name());
        postJobDetail.setCompany(postJobDetailDTO.getCompany());

        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(postJobDetailDTO.getPosted_by_id());
        postJobDetail.setRegistrationDetail(registrationDetail);


        Category category = new Category();
        category.setId(postJobDetailDTO.getCategory_id());
        postJobDetail.setCategory(category);

        postJobDetail.setJob_type(postJobDetailDTO.getJob_type());
        postJobDetail.setExperience(postJobDetailDTO.getExperience());
        postJobDetail.setSalary_offer(postJobDetailDTO.getSalary_offer());

        String jobOpeningDate = utility.changedateformatter(postJobDetailDTO.getJob_opening_date(), "dd-MM-yyyy");
        postJobDetail.setJob_opening_date(jobOpeningDate);
        postJobDetail.setDescription(postJobDetailDTO.getDescription());

        JobLocation jobLocation = new JobLocation();
        jobLocation.setStreet_add(postJobDetailDTO.getStreet_add());
        jobLocation.setCity(postJobDetailDTO.getCity());
        jobLocation.setState(postJobDetailDTO.getState());
        jobLocation.setPincode(postJobDetailDTO.getPincode());
        postJobDetail.setJobLocation(jobLocation);


        return postJobDetail;
    }

    RegistrationDetailDTO convertDTOtoModel(RegistrationDetail registrationDetail) throws ParseException {
        RegistrationDetailDTO registrationDetailDTO = new RegistrationDetailDTO();
        registrationDetailDTO.setFirstname(registrationDetail.getFirstname());
        registrationDetailDTO.setLastname(registrationDetail.getLastname());
        registrationDetailDTO.setEmailid(registrationDetail.getEmailid());
        registrationDetailDTO.setDob(registrationDetail.getDob());
        registrationDetailDTO.setMobno(registrationDetail.getMobno());
        return registrationDetailDTO;
    }

    public void downloadPdf(String fileName) {
        File file = new File("F:/resume/" + fileName);
        Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
        responseBuilder.header("Content-Disposition", "attachment; filename=\"MyJerseyFile.pdf\"");
        responseBuilder.build();
    }
}
