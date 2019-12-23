package com.jobportal.jobportalsystem.dao.recruiterjobseeker;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.ApplyJob;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.PostJobDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Repository
public class RecruiterJobSeekerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveJobPostDetail(PostJobDetail postJobDetail) {
        entityManager.persist(postJobDetail);
    }

    @Transactional
    public List<PostJobDetail> fetchJobDetailsForRecruiter(Map<String, Long> keyValue) {
        TypedQuery<PostJobDetail> query = entityManager.createQuery("select postJobDetail " +
                        "from PostJobDetail postJobDetail " +
                        "where postJobDetail.registrationDetail.id=:recruiter_id",
                PostJobDetail.class);
        query.setParameter("recruiter_id", keyValue.get("userId"));
        return query.getResultList();
    }

    public List<PostJobDetail> fetchJobDetailsForJobSeeker() {
        TypedQuery<PostJobDetail> query = entityManager.createQuery("select postJobDetail " +
                        "from PostJobDetail postJobDetail ",
                PostJobDetail.class);
        return query.getResultList();
    }

    public PostJobDetail fetchJobDetailsOfCompany(Long job_id) {
        return entityManager.find(PostJobDetail.class, job_id);
    }

    @Transactional
    public void removeJobPostDetail(Long job_id) {
        PostJobDetail jobDetail = entityManager.find(PostJobDetail.class, job_id);
        entityManager.remove(jobDetail);
    }

    @Transactional
    public void applyForJOB(ApplyJob applyJob) {

        entityManager.persist(applyJob);
    }

    public List<ApplyJob> checkAppliedForJob(ApplyJob applyJob) {
        TypedQuery<ApplyJob> query = entityManager.createQuery("select applyJob from ApplyJOB applyJob" +
                " where applyJob.postJobDetail.id=:jobId" +
                " and applyJob.registrationDetail.id=:seekerId", ApplyJob.class);
        query.setParameter("jobId", applyJob.getPostJobDetail().getId());
        query.setParameter("seekerId", applyJob.getRegistrationDetail().getId());
        return query.getResultList();
    }

    public List<Object[]> appliedJobsList(Long job_id) {
        Query query = entityManager.createQuery("select registrationDetail.firstName ,registrationDetail.lastName, " +
                "postJobDetail.company,postJobDetail.description," +
                "applyJob.applyDate,applyJob.filename from ApplyJOB applyJob" +
                " inner join RegistrationDetail registrationDetail on applyJob.registrationDetail.id=registrationDetail.id" +
                " inner join PostJobDetail postJobDetail on postJobDetail.id=applyJob.postJobDetail.id " +
                " where applyJob.postJobDetail.id=:jobId");
        query.setParameter("jobId", job_id);
        return (List<Object[]>) query.getResultList();
    }

    public List<Category> loadCategoryList() {
        TypedQuery<Category> query = entityManager.createQuery("select category from Category category", Category.class);
        return query.getResultList();
    }

    public List<Object[]> loadSkills(Long categoryId) {
        Query query;
        query = entityManager.createNativeQuery("select s.id,s.skill_name " +
                "from skills s" +
                " where s.category_id=:categoryId");
        query.setParameter("categoryId", categoryId);
        return (List<Object[]>) query.getResultList();
    }

    @Transactional
    public void save(Category category) {
        entityManager.persist(category);
    }
}
