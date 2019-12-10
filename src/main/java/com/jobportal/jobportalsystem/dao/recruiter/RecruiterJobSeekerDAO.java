package com.jobportal.jobportalsystem.dao.recruiter;


import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.recruiter.ApplyJOB;
import com.jobportal.jobportalsystem.model.recruiter.JobLocation;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class RecruiterJobSeekerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruiterJobSeekerDAO.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveJobPostDetail(PostJobDetail postJobDetail) {
        LOGGER.info("postJobDetail==" + postJobDetail);
        JobLocation jobLocation = postJobDetail.getJobLocation();
        LOGGER.info("jobLocation==" + jobLocation);

        entityManager.persist(jobLocation);
        entityManager.persist(postJobDetail);
    }

    @Transactional
    public List<PostJobDetail> fetchJobDetails(Map<String, Long> keyValue) {
        Query query;
        if (keyValue != null) {
            LOGGER.info("jobDetail IN RECRUITRTER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd where pjd.registrationDetail.id=:recruiter_id");
            query.setParameter("recruiter_id", keyValue.get("user_id"));
        } else {
            LOGGER.info("jobDetail IN SEEKER===");

            query = entityManager.createQuery("select pjd from PostJobDetail pjd ");
        }

        List<PostJobDetail> jobDetail = query.getResultList();
        return jobDetail;
    }

    @Transactional
    public PostJobDetail fetchJobDetailsOfCompany(Long job_id) {

        PostJobDetail jobDetail = entityManager.find(PostJobDetail.class, job_id);
        LOGGER.info("jobDetail===" + (jobDetail));
        return jobDetail;
    }

    @Transactional
    public void removeJobPostDetail(Long job_id) {
        PostJobDetail jobDetail = entityManager.find(PostJobDetail.class, job_id);
        entityManager.remove(jobDetail);
    }

    @Transactional
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

    @Transactional
    public List<Object[]> appliedJobsList(Long job_id) {
        Query query;
        query = entityManager.createQuery("select rd.firstname ,rd.lastname, pj.company,pj.description,aj.applyDate,aj.filename from ApplyJOB aj" +
                " inner join RegistrationDetail rd on aj.registrationDetail.id=rd.id" +
                " inner join PostJobDetail pj on pj.id=aj.postJobDetail.id " +
                " where aj.postJobDetail.id=:job_id");
        query.setParameter("job_id", job_id);

        List<Object[]> jobAppliedList = query.getResultList();
        LOGGER.info("size==" + jobAppliedList.size());

        return jobAppliedList;
    }

    public List<Category> loadCategoryList() {
        Query query;
        query = entityManager.createQuery("select ca from Category ca");
        List<Category> categories = query.getResultList();
        LOGGER.info("categories==" + categories);
        return categories;
    }

    public List<Object[]> loadSkills(Long categoryId) {
        Query query;
        query = entityManager.createNativeQuery("select s.id,s.skill_name from skills s where s.category_id=:categoryId");
        query.setParameter("categoryId", categoryId);
        List<Object[]> skills = query.getResultList();
        return skills;
    }

}
