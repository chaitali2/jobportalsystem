package com.jobportal.jobportalsystem.dto.other;

import java.util.List;

public class CategoryDTO {

    private Long category_id;
    private String categoryName;
    private List skills;

    public List getSkills() {
        return skills;
    }

    public void setSkills(List skills) {
        this.skills = skills;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "category_id=" + category_id +
                ", categoryName='" + categoryName + '\'' +
                ", skills=" + skills +
                '}';
    }
}
