package com.jobportal.jobportalsystem.dao.recruiter;


import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecruiterDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterDAO.class);

    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void saveJobPostDetail(PostJobDetail postJobDetail) {
        LOGGER.info("postJobDetail=="+postJobDetail);
        JobLocation jobLocation = postJobDetail.getJobLocation();
        entityManager.persist(jobLocation);
        entityManager.persist(postJobDetail);
    }




    public List<PostJobDetail> fetchJobDetails(String user_id) {
        Query query;
        if (user_id.trim().isEmpty()) {
            LOGGER.info("jobDetail IN SEEKER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd ");
        } else {
            LOGGER.info("jobDetail IN RECRUITRTER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd where pjd.registrationDetail.id=:recruiter_id");
            query.setParameter("recruiter_id", Long.parseLong(user_id));
        }

        List<PostJobDetail> jobDetail = query.getResultList();
        LOGGER.info("jobDetail===" + (jobDetail));
        if (jobDetail != null && !jobDetail.isEmpty()) {
            return jobDetail;
        } else {
            throw new RuntimeException("Job details not found !");
        }
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
        query = entityManager.createQuery("select aj from ApplyJOB aj" +
                                          " where aj.postJobDetail.id=:job_id" +
                                          " and aj.registrationDetail.id=:seeker_id");
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

    public List<Category> getCategory() {
        Query query;
        query = entityManager.createQuery("select ca from Category ca" );
        List<Category> categories = query.getResultList();
        LOGGER.info("categories=="+categories);
        return categories;
    }

    public List<Object[]> loadSkills(String categoryId) {
        Query query;
        query = entityManager.createNativeQuery("select s.id,s.skill_name from CATEGORY_SKILL_LIST c INNER JOIN SKILLS s on c.SKILL_LIST_ID=s.ID where c.CATEGORY_ID=:categoryId" );
        query.setParameter("categoryId", Long.parseLong(categoryId));

        List<Object[]> skills =query.getResultList();


        return skills;
    }

}
