package com.jobportal.jobportalsystem.service.other;

import com.jobportal.jobportalsystem.dao.other.OtherDAO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OtherService {

    @Autowired
    OtherDAO otherDAO;

    public void insertCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
//        otherDAO.save(category);
    }

    public void inserSkillsWiseCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        Skill skill = new Skill();
        skill.setSkill_name("MY-SQL");

        Skill skill1 = new Skill();
        skill1.setSkill_name("Oracle");

        Set<Skill> list = new HashSet<>();
        list.add(skill);
        list.add(skill1);

        category.setCategoryName("DBA");
        category.setSkills(list);

        Skill skill2 = new Skill();
        skill2.setSkill_name("Spring");

        Skill skill3 = new Skill();
        skill3.setSkill_name("Hibernate");

        Set<Skill> list1 = new HashSet<>();
        list1.add(skill2);
        list1.add(skill3);
        Category category1 = new Category();
        category1.setCategoryName("Java Devloper");
        category1.setSkills(list1);

        otherDAO.save(category, skill, skill1,skill2,skill3,category1);
    }
}
