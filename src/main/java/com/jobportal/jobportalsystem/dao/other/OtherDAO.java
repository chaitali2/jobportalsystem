package com.jobportal.jobportalsystem.dao.other;

import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OtherDAO {


    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Category category, Skill skill,Skill skill1) {

        entityManager.persist(skill);
        entityManager.persist(skill1);
        entityManager.persist(category);
    }
}
