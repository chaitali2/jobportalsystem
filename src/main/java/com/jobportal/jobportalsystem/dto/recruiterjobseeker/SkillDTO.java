package com.jobportal.jobportalsystem.dto.recruiterjobseeker;

public class SkillDTO {

    private Long skillId;
    private String skillName;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "skillId=" + skillId +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
