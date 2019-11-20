package com.jobportal.jobportalsystem.dao.recruiter;


import com.jobportal.jobportalsystem.model.JobLocation;
import com.jobportal.jobportalsystem.model.RegistrationDetail;
import com.jobportal.jobportalsystem.model.recruiter.PostJobDetail;
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

    public void fetchJobDetail(String recruiter_id) {
        Query query = entityManager.createQuery("Select jp from PostJobDetail jp left join RegistrationDetail where jp.posted_by_id=:recruiter_id");
        query.setParameter("recruiter_id",recruiter_id);
        List<PostJobDetail> jobDetail = query.getResultList();
        LOGGER.info("jobDetail==="+jobDetail);
    }
}
