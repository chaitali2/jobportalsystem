package com.jobportal.jobportalsystem.dto.other;

public class SkillDTO {

    private Long skill_id;
    private String skill_name;

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "skill_id=" + skill_id +
                ", skill_name='" + skill_name + '\'' +
                '}';
    }
}
