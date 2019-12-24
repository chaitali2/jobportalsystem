package com.jobportal.jobportalsystem.model.recruiterjobseeker;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String categoryName;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Set<Skill> skills;

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
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
                ", skills=" + skills +
                '}';
    }
}
