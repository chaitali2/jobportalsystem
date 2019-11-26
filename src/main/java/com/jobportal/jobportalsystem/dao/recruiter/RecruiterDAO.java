package com.jobportal.jobportalsystem.dao.recruiter;


import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RecruiterDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterDAO.class);

    @PersistenceContext
    EntityManager entityManager;

    public void saveJobPostDetail(PostJobDetail postJobDetail) {
        JobLocation jobLocation = postJobDetail.getJobLocation();
        entityManager.persist(jobLocation);
        entityManager.persist(postJobDetail);
    }

    public RegistrationDetail fetchUserDetails(String user_id) {
        RegistrationDetail userDetail = entityManager.find(RegistrationDetail.class, Long.parseLong(user_id));
        LOGGER.info("user detail==" + userDetail);
        return userDetail;
    }


    public List<PostJobDetail> fetchJobDetails(String user_id) {
//        Query query = entityManager.createQuery("Select jp.company,jp.category,jp.job_type,jp.experience," +
//                "jp.salary_offer,jl.street_add,jl.city,jl.state,jl.pincode,jp.job_opening_date," +
//                                                        "jp.description" +
//                                                        "" +
////                ",jps.skills" +
//                                                        " from " +
////                "skills jps" +
////                                                        " left join" +
//                " PostJobDetail jp " +
////                                                        " on jps.skills.id=jp.id" +
//                                                        " inner join " +
//                                                        " JobLocation jl " +
//                                                        " on jp.jobLocation.id=jl.id " +
//                                                        " where jp.registrationDetail.id=:recruiter_id");
        Query query;
        LOGGER.info("user_id ===" + user_id);

        if (user_id.trim().isEmpty()) {
            LOGGER.info("jobDetail IN SEEKER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd ");
        } else {
            LOGGER.info("jobDetail IN RECRUITRTER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd where pjd.registrationDetail.id=:recruiter_id");
            query.setParameter("recruiter_id", Long.parseLong(user_id));


        }
        List<PostJobDetail> jobDetail = query.getResultList();
        entityManager.flush();
        LOGGER.info("jobDetail===" + (jobDetail));
        return jobDetail;
    }


    public PostJobDetail fetchJobDetailsOfCompany(String job_id) {

        PostJobDetail jobDetail = entityManager.find(PostJobDetail.class, Long.parseLong(job_id));
        LOGGER.info("jobDetail===" + (jobDetail));
        return jobDetail;
    }

    public void removeJobPostDetail(String job_id) {
        PostJobDetail jobDetail = entityManager.find(PostJobDetail.class, Long.parseLong(job_id));
        entityManager.remove(jobDetail);
    }


    public void applyForJOB(ApplyJOB applyJOB) {
        entityManager.persist(applyJOB);
    }

    public List<ApplyJOB> checkAppliedForJob(ApplyJOB applyJOB) {
        Query query;
        query = entityManager.createQuery("select aj from ApplyJOB aj where aj.postJobDetail.id=:job_id and aj.registrationDetail.id=:seeker_id");
        query.setParameter("job_id", applyJOB.getPostJobDetail().getId());
        query.setParameter("seeker_id", applyJOB.getRegistrationDetail().getId());
        List<ApplyJOB> existJobSeeker = query.getResultList();
        LOGGER.info("existJobSeeker==" + existJobSeeker.size());
        return existJobSeeker;
    }

    public List appliedJobsList(String job_id) {
        Query query;
        query = entityManager.createQuery("select rd.firstname from ApplyJOB aj" +
                " inner join RegistrationDetail rd on aj.registrationDetail.id=rd.id" +
                " where aj.postJobDetail.id=:job_id");
        query.setParameter("job_id", Long.parseLong(job_id));

        List jobAppliedList = query.getResultList();
        LOGGER.info("existJobSeeker==" + jobAppliedList);
        LOGGER.info("existJobSeeker==" + jobAppliedList.size());
        return jobAppliedList;
    }


}
