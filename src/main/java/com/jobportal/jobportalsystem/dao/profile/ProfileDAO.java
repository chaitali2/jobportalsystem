package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public class ProfileDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Profile fetchUserDetails(Long user_id) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, user_id);
        TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile" +
                                                             " where profile.registrationDetail.id=:user_id",
                                                              Profile.class);
        query.setParameter("user_id", user_id);
        Profile profile;
        try {
            profile = query.getSingleResult();
            profile.setRegistrationDetail(registrationDetail);

        } catch (NoResultException ex) {
            profile = new Profile();
            profile.setRegistrationDetail(registrationDetail);
        }
        return profile;
    }

    @Transactional
    public void saveProfileDetail(Profile profileDetail) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, profileDetail.getRegistrationDetail().getId());
        registrationDetail.setMobileNo(profileDetail.getRegistrationDetail().getMobileNo());
        registrationDetail.setFirstName(profileDetail.getRegistrationDetail().getFirstName());
        registrationDetail.setLastName(profileDetail.getRegistrationDetail().getLastName());
        entityManager.merge(registrationDetail);
        Profile profile;
        try {
            TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile where " +
                    "profile.registrationDetail.id=:user_id", Profile.class);
            query.setParameter("user_id", profileDetail.getRegistrationDetail().getId());
            profile = query.getSingleResult();
            Address address = entityManager.find(Address.class, profile.getAddress().getId());
            address.setCity(profileDetail.getAddress().getCity());
            address.setState(profileDetail.getAddress().getState());
            address.setStreet(profileDetail.getAddress().getStreet());
            entityManager.merge(address);
            profile.setRegistrationDetail(profileDetail.getRegistrationDetail());
            if (registrationDetail.getUserType().equals("J")) {
                EducationExperience educationExperience = entityManager.find(EducationExperience.class, profile.getEducationExperience().getId());
                educationExperience.setExperience(profileDetail.getEducationExperience().getExperience());
                educationExperience.setExpectedSalary(profileDetail.getEducationExperience().getExpectedSalary());
                educationExperience.setHighestDegree(profileDetail.getEducationExperience().getHighestDegree());
                educationExperience.setPassingYear(profileDetail.getEducationExperience().getPassingYear());
                educationExperience.setPercentage(profileDetail.getEducationExperience().getPercentage());
                entityManager.merge(educationExperience);
                profile.setEducationExperience(educationExperience);
            }
            entityManager.merge(profile);
        } catch (NoResultException ex) {
            Address address = profileDetail.getAddress();
            entityManager.persist(address);
            entityManager.persist(profileDetail);
        }

    }
}
