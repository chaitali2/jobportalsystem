package com.jobportal.jobportalsystem.service.other;

import com.jobportal.jobportalsystem.dao.other.OtherDAO;
import com.jobportal.jobportalsystem.dto.other.CategoryDTO;
import com.jobportal.jobportalsystem.dto.other.SkillDTO;
import com.jobportal.jobportalsystem.model.other.Category;
import com.jobportal.jobportalsystem.model.other.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OtherService {

    @Autowired
    OtherDAO otherDAO;

    public void insertCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        otherDAO.save(category);
    }

    public void inserSkillsWiseCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        List<SkillDTO> skillDTOList = categoryDTO.getSkills();

        List<Skill> skillList = new ArrayList<>();
        for (int i = 0; i < skillDTOList.size(); i++) {
            Skill skill = new Skill();
            skill.setSkill_name(skillDTOList.get(i).getSkill_name());
            skillList.add(skill);
        }
        category.setSkills(skillList);
        otherDAO.save(category);
    }
}
