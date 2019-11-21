package com.jobportal.jobportalsystem.dao.recruiter;


import com.jobportal.jobportalsystem.dto.recruiter.PostJobDetailDTO;
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

        if (user_id != null || user_id.trim().isEmpty()) {
            query = entityManager.createQuery("select pjd from PostJobDetail pjd ");
        } else {
            query = entityManager.createQuery("select pjd from PostJobDetail pjd where pjd.registrationDetail.id=:recruiter_id");
            query.setParameter("recruiter_id", Long.parseLong(user_id));
        }

        List<PostJobDetail> jobDetail = query.getResultList();
        LOGGER.info("jobDetail===" + (jobDetail));
        return jobDetail;
    }
}
