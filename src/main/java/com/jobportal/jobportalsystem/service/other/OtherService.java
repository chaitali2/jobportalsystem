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

    public void inserSkillsWiseCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        Set<Skill> skillSet = new HashSet<>();
        List<String> skills = categoryDTO.getSkills();
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = new Skill();
            skill.setSkill_name(skills.get(i));
            skillSet.add(skill);
        }
        category.setSkills(skillSet);
        otherDAO.save(category);
    }
}
