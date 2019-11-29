package com.jobportal.jobportalsystem.model.other;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoryName;

    @OneToMany(targetEntity = Skill.class,fetch = FetchType.EAGER)
    List skillList;

    public List getSkillList() {
        return skillList;
    }

    public void setSkillList(List skillList) {
        this.skillList = skillList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", skillList=" + skillList +
                '}';
    }
}
