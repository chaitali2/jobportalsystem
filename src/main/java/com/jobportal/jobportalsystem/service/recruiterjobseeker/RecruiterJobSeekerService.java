package com.jobportal.jobportalsystem.service.recruiterjobseeker;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.recruiterjobseeker.RecruiterJobSeekerDAO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.CategoryDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Skill;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.PostJobDetail;
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
import java.util.*;

@Service
public class RecruiterJobSeekerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterJobSeekerService.class);

    @Autowired
    private RecruiterJobSeekerDAO recruiterDAO;

    @Autowired
    private Utility utility;

    public void postJobDetail(PostJobDetailDTO postJobDetailDTO) throws ParseException {
        recruiterDAO.saveJobPostDetail(convertDTOtoModel(postJobDetailDTO));
    }

    public List<PostJobDetailDTO> fetchJobDetails(Map<String, Long> keyValue) {
        List<PostJobDetail> jobDetails = null;
        if (keyValue != null) {
            jobDetails = recruiterDAO.fetchJobDetailsForRecruiter(keyValue);
        } else {
            jobDetails = recruiterDAO.fetchJobDetailsForJobSeeker();
        }
        if (jobDetails != null && !jobDetails.isEmpty()) {
            List<PostJobDetailDTO> jobDetailDTOS = new ArrayList<>();
            for (PostJobDetail jobDetail : jobDetails) {
                jobDetailDTOS.add(setJobDetailsFetch(jobDetail));
            }
            return jobDetailDTOS;
        } else {
            throw new RuntimeException("Job details not found !");
        }
    }

    public void applyForJOB(InputStream fileInputStream,
                            FormDataContentDisposition fileMetaData,
                            String jobId,
                            String userId) throws UserExistException {
        ApplyJOB applyJOB=setApplyJobDetails(fileMetaData,jobId,userId);
        List<ApplyJOB> existJobSeeker = recruiterDAO.checkAppliedForJob(applyJOB);
        if (existJobSeeker.isEmpty()) {
            uploadResume(fileInputStream, userId);
            recruiterDAO.applyForJOB(applyJOB);
        } else {
            throw new UserExistException("Already applied for job");
        }
    }


    private ApplyJOB setApplyJobDetails( FormDataContentDisposition fileMetaData,
                        String jobId,
                        String userId){
        ApplyJOB applyJOB = new ApplyJOB();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(Long.parseLong(jobId));
        applyJOB.setPostJobDetail(postJobDetail);
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(userId));
        applyJOB.setRegistrationDetail(registrationDetail);
        new File("F:/resume/" + fileMetaData.getFileName());
        applyJOB.setFilename(userId + ".pdf");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date applyDate = new Date();
        applyJOB.setApplyDate(df.format(applyDate));
        return applyJOB;
    }

    private void uploadResume(InputStream fileInputStream,
                              String user_id) {
        String UPLOAD_PATH = "F:/resume/";
        try {
            int read;
            byte[] bytes = new byte[1024];
            File file = new File(UPLOAD_PATH + user_id + ".pdf");
            OutputStream out;
            out = new FileOutputStream(file);
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
            applyJobDTO.setFirstName(details[0].toString());
            applyJobDTO.setLastName(details[1].toString());
            applyJobDTO.setCompany(details[2].toString());
            applyJobDTO.setDescription(details[3].toString());
            applyJobDTO.setApplyDate(details[4].toString());
            applyJobDTO.setFileName(details[5].toString());
            applyJobDTOList.add(applyJobDTO);
        }
        return applyJobDTOList;
    }

    public List<CategoryDTO> loadCategoryList() {
        List<Category> categories = recruiterDAO.loadCategoryList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(category.getId());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public List<SkillDTO> loadSkills(Long categoryId) {
        List<Object[]> skills = recruiterDAO.loadSkills(categoryId);
        List<SkillDTO> skillList = new ArrayList<>();
        for (Object[] a : skills) {
            SkillDTO skill = new SkillDTO();
            skill.setSkillId(Long.parseLong(a[0].toString()));
            skill.setSkillName(a[1].toString());
            skillList.add(skill);
        }
        return skillList;
    }


    private PostJobDetailDTO setJobDetailsFetch(PostJobDetail jobDetail) {
        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setJobId(jobDetail.getId());
        postJobDetailDTO.setCompany(jobDetail.getCompany());
        postJobDetailDTO.setCategoryName(jobDetail.getCategory().getCategoryName());
        String jobType = ("P").equals(jobDetail.getJob_type()) ? "Permanent" : "Contract";
        postJobDetailDTO.setJobType(jobType);
        postJobDetailDTO.setExperience(String.valueOf(jobDetail.getExperience()));
        postJobDetailDTO.setSalaryOffer(String.valueOf(jobDetail.getSalary_offer()));
        postJobDetailDTO.setStreet(jobDetail.getJobLocation().getStreet_add());
        postJobDetailDTO.setCity(jobDetail.getJobLocation().getCity());
        postJobDetailDTO.setState(jobDetail.getJobLocation().getState());
        postJobDetailDTO.setPincode(jobDetail.getJobLocation().getPincode());
        postJobDetailDTO.setJobOpeningDate(jobDetail.getJob_opening_date());
        postJobDetailDTO.setDescription(jobDetail.getDescription());

        Set<Skill> skillList = jobDetail.getSkillSet();
        Set<String> skills = new HashSet<>();
        for (Skill skill : skillList) {
            skills.add(skill.getSkillName());
        }
        postJobDetailDTO.setSkills(skills);
        return postJobDetailDTO;
    }

    public PostJobDetailDTO fetchJobDetailsOfCompany(Long user_id) {
        PostJobDetail jobDetails = recruiterDAO.fetchJobDetailsOfCompany(user_id);
        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setJobId(jobDetails.getId());
        postJobDetailDTO.setCompany(jobDetails.getCompany());
        postJobDetailDTO.setCategoryName(jobDetails.getCategory().getCategoryName());
        String jobType = jobDetails.getJob_type().equals("P") ? "Permanent" : "Contract";
        postJobDetailDTO.setJobType(jobType);
        postJobDetailDTO.setExperience(String.valueOf(jobDetails.getExperience()));
        postJobDetailDTO.setSalaryOffer(String.valueOf(jobDetails.getSalary_offer()));
        postJobDetailDTO.setStreet(jobDetails.getJobLocation().getStreet_add());
        postJobDetailDTO.setCity(jobDetails.getJobLocation().getCity());
        postJobDetailDTO.setState(jobDetails.getJobLocation().getState());
        postJobDetailDTO.setPincode(jobDetails.getJobLocation().getPincode());
        postJobDetailDTO.setJobOpeningDate(jobDetails.getJob_opening_date());
        postJobDetailDTO.setDescription(jobDetails.getDescription());
        return postJobDetailDTO;
    }


    public void removeJobPostDetail(Long user_id) {
        recruiterDAO.removeJobPostDetail(user_id);
    }


    public Response downloadPdf(String fileName) {
        File file = new File("F:/resume/" + fileName);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition",
                "attachment; filename=" + fileName);
        return response.build();
    }

    public void inserSkillsWiseCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setSkills(addSkilll(categoryDTO));
        recruiterDAO.save(category);
    }

    private Set<Skill> addSkilll(CategoryDTO categoryDTO) {
        Set<Skill> skillSet = new HashSet<>();
        List<String> skills = categoryDTO.getSkills();
        for (String s : skills) {
            Skill skill = new Skill();
            skill.setSkillName(s);
            skillSet.add(skill);
        }
        return skillSet;
    }

    private PostJobDetail convertDTOtoModel(PostJobDetailDTO postJobDetailDTO) throws ParseException {
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setCompany(postJobDetailDTO.getCompany());
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(postJobDetailDTO.getPostedById());
        postJobDetail.setRegistrationDetail(registrationDetail);
        Category category = new Category();
        category.setId(postJobDetailDTO.getCategoryId());
        postJobDetail.setCategory(category);
        postJobDetail.setJob_type(postJobDetailDTO.getJobType());
        postJobDetail.setExperience(Double.parseDouble(postJobDetailDTO.getExperience()));
        postJobDetail.setSalary_offer(Double.parseDouble(postJobDetailDTO.getSalaryOffer()));
        String jobOpeningDate = utility.changeDateFormatter(postJobDetailDTO.getJobOpeningDate(), "dd-MM-yyyy");
        postJobDetail.setJob_opening_date(jobOpeningDate);
        postJobDetail.setDescription(postJobDetailDTO.getDescription());
        postJobDetail.setJobLocation(getJobLocationDetail(postJobDetailDTO));
        postJobDetail.setSkillSet(getSkillDetail(postJobDetailDTO));
        return postJobDetail;
    }

    private JobLocation getJobLocationDetail(PostJobDetailDTO postJobDetailDTO) {
        JobLocation jobLocation = new JobLocation();
        jobLocation.setStreet_add(postJobDetailDTO.getStreet());
        jobLocation.setCity(postJobDetailDTO.getCity());
        jobLocation.setState(postJobDetailDTO.getState());
        jobLocation.setPincode(postJobDetailDTO.getPincode());
        return jobLocation;
    }

    private Set<Skill> getSkillDetail(PostJobDetailDTO postJobDetailDTO) {
        Set<String> skills = postJobDetailDTO.getSkills();
        Set<Skill> skillSet = new HashSet<>();
        for (String s : skills) {
            Skill skill = new Skill();
            skill.setId(Long.parseLong(s));
            skillSet.add(skill);
        }
        return skillSet;
    }

}
