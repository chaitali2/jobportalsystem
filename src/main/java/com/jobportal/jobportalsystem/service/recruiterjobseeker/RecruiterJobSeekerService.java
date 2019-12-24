package com.jobportal.jobportalsystem.service.recruiterjobseeker;

import com.jobportal.jobportalsystem.customizedException.UserExistException;
import com.jobportal.jobportalsystem.dao.recruiterjobseeker.RecruiterJobSeekerDAO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.CategoryDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.SkillDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.ApplyJobDTO;
import com.jobportal.jobportalsystem.dto.recruiterjobseeker.PostJobDetailDTO;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Skill;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.ApplyJob;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.JobLocation;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import com.jobportal.jobportalsystem.utility.Utility;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
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
        ApplyJob applyJob = setApplyJobDetails(fileMetaData, jobId, userId);
        ApplyJob existJobSeeker = recruiterDAO.checkAppliedForJob(applyJob);
        if (existJobSeeker == null) {
            uploadResume(fileInputStream, userId);
            recruiterDAO.applyForJOB(applyJob);
        } else {
            throw new UserExistException("Already applied for job");
        }
    }

    private ApplyJob setApplyJobDetails(FormDataContentDisposition fileMetaData,
                                        String jobId,
                                        String userId) {
        ApplyJob applyJob = new ApplyJob();
        PostJobDetail postJobDetail = new PostJobDetail();
        postJobDetail.setId(Long.parseLong(jobId));
        applyJob.setPostJobDetail(postJobDetail);
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(userId));
        applyJob.setRegistrationDetail(registrationDetail);
        new File("E:/resume/" + fileMetaData.getFileName());
        applyJob.setFilename(userId + ".pdf");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date applyDate = new Date();
        applyJob.setApplyDate(df.format(applyDate));
        return applyJob;
    }

    private void uploadResume(InputStream fileInputStream,
                              String user_id) {
        String UPLOAD_PATH = "E:/resume/";
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

    public  List<SkillDTO> loadSkills(Long categoryId) {
        Category category = recruiterDAO.loadSkills(categoryId);
        List<SkillDTO> skillList = new ArrayList<>();
        for (Skill skill : category.getSkills()) {
            SkillDTO skillDTO = new SkillDTO();
            skillDTO.setSkillId(skill.getId());
            skillDTO.setSkillName(skill.getSkillName());
            skillList.add(skillDTO);
        }
        return skillList;
    }


    private PostJobDetailDTO setJobDetailsFetch(PostJobDetail jobDetail) {
        PostJobDetailDTO postJobDetailDTO = new PostJobDetailDTO();
        postJobDetailDTO.setJobId(jobDetail.getId());
        postJobDetailDTO.setCompany(jobDetail.getCompany());
        postJobDetailDTO.setCategoryName(jobDetail.getCategory().getCategoryName());
        String jobType = ("P").equals(jobDetail.getJobType().toString()) ? "Permanent" : "Contract";
        postJobDetailDTO.setJobType(jobType);
        postJobDetailDTO.setExperience(String.valueOf(jobDetail.getExperience()));
        postJobDetailDTO.setSalaryOffer(String.valueOf(jobDetail.getSalaryOffer()));
        postJobDetailDTO.setStreet(jobDetail.getJobLocation().getStreet());
        postJobDetailDTO.setCity(jobDetail.getJobLocation().getCity());
        postJobDetailDTO.setState(jobDetail.getJobLocation().getState());
        postJobDetailDTO.setPincode(jobDetail.getJobLocation().getPincode());
        postJobDetailDTO.setJobOpeningDate(new Utility().dateToString(jobDetail.getJobOpeningDate()));
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
        String jobType = jobDetails.getJobType().toString().equals("P") ? "Permanent" : "Contract";
        postJobDetailDTO.setJobType(jobType);
        postJobDetailDTO.setExperience(String.valueOf(jobDetails.getExperience()));
        postJobDetailDTO.setSalaryOffer(String.valueOf(jobDetails.getSalaryOffer()));
        postJobDetailDTO.setStreet(jobDetails.getJobLocation().getStreet());
        postJobDetailDTO.setCity(jobDetails.getJobLocation().getCity());
        postJobDetailDTO.setState(jobDetails.getJobLocation().getState());
        postJobDetailDTO.setPincode(jobDetails.getJobLocation().getPincode());
        postJobDetailDTO.setJobOpeningDate(new Utility().dateToString(jobDetails.getJobOpeningDate()));
        postJobDetailDTO.setDescription(jobDetails.getDescription());
        return postJobDetailDTO;
    }


    public void removeJobPostDetail(Long job_id) {
        recruiterDAO.removeJobPostDetail(job_id);
    }


    public Response downloadPdf(String fileName) {
        File file = new File("E:/resume/" + fileName);
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
        postJobDetail.setJobType(postJobDetailDTO.getJobType().equals("P") ? PostJobDetail.Job.P : PostJobDetail.Job.C);
        postJobDetail.setExperience(Double.parseDouble(postJobDetailDTO.getExperience()));
        postJobDetail.setSalaryOffer(Double.parseDouble(postJobDetailDTO.getSalaryOffer()));
        Date jobOpeningDate = utility.changeDateFormatter(postJobDetailDTO.getJobOpeningDate());
        postJobDetail.setJobOpeningDate(jobOpeningDate);
        postJobDetail.setDescription(postJobDetailDTO.getDescription());
        postJobDetail.setJobLocation(getJobLocationDetail(postJobDetailDTO));
        postJobDetail.setSkillSet(getSkillDetail(postJobDetailDTO));
        return postJobDetail;
    }

    private JobLocation getJobLocationDetail(PostJobDetailDTO postJobDetailDTO) {
        JobLocation jobLocation = new JobLocation();
        jobLocation.setStreet(postJobDetailDTO.getStreet());
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
