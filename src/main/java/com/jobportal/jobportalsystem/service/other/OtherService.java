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
        Skill skill=new Skill();
        skill.setSkill_name("MY-SQL");

        Skill skill1=new Skill();
        skill1.setSkill_name("Oracle");

        Set<Skill> list=new HashSet<>();
        list.add(skill);
        list.add(skill1);

        category.setCategoryName("DBA");
        category.setSkills(list);
        otherDAO.save(category,skill,skill1);
    }
}
