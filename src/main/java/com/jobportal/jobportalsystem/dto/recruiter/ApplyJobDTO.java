package com.jobportal.jobportalsystem.dto.recruiter;

import org.springframework.web.multipart.MultipartFile;

public class ApplyJobDTO {
    private String user_id;
    private String job_id;
    private MultipartFile file;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ApplyJobDTO{" +
                "user_id='" + user_id + '\'' +
                ", job_id='" + job_id + '\'' +
                ", file=" + file +
                '}';
    }
}
