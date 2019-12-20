package com.jobportal.jobportalsystem.dao.recruiterjobseeker;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.Category;
import com.jobportal.jobportalsystem.model.recruiterjobseeker.ApplyJOB;
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
    public void applyForJOB(ApplyJOB applyJOB) {
        entityManager.persist(applyJOB);
    }

    public List<ApplyJOB> checkAppliedForJob(ApplyJOB applyJOB) {
        TypedQuery<ApplyJOB> query = entityManager.createQuery("select applyJOB from ApplyJOB applyJOB" +
                " where applyJOB.postJobDetail.id=:jobId" +
                " and applyJOB.registrationDetail.id=:seekerId", ApplyJOB.class);
        query.setParameter("jobId", applyJOB.getPostJobDetail().getId());
        query.setParameter("seekerId", applyJOB.getRegistrationDetail().getId());
        return query.getResultList();
    }

    public List<Object[]> appliedJobsList(Long job_id) {
        Query query = entityManager.createQuery("select registrationDetail.firstname ,registrationDetail.lastname, " +
                "postJobDetail.company,postJobDetail.description," +
                "applyJOB.applyDate,applyJOB.filename from ApplyJOB applyJOB" +
                " inner join RegistrationDetail registrationDetail on applyJOB.registrationDetail.id=registrationDetail.id" +
                " inner join PostJobDetail postJobDetail on postJobDetail.id=applyJOB.postJobDetail.id " +
                " where applyJOB.postJobDetail.id=:jobId");
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
