package com.jobportal.jobportalsystem.model.recruiter;

import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;

import javax.persistence.*;


@Entity
@Table(name = "seeker_apply_job")
public class ApplyJOB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "job_id")
    PostJobDetail postJobDetail;

    @OneToOne
    @JoinColumn(name = "seeker_id")
    private RegistrationDetail registrationDetail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostJobDetail getPostJobDetail() {
        return postJobDetail;
    }

    public void setPostJobDetail(PostJobDetail postJobDetail) {
        this.postJobDetail = postJobDetail;
    }

    public RegistrationDetail getRegistrationDetail() {
        return registrationDetail;
    }

    public void setRegistrationDetail(RegistrationDetail registrationDetail) {
        this.registrationDetail = registrationDetail;
    }

    @Override
    public String toString() {
        return "ApplyForJOB{" +
                "id=" + id +
                ", postJobDetail=" + postJobDetail +
                ", registrationDetail=" + registrationDetail +
                '}';
    }
}
