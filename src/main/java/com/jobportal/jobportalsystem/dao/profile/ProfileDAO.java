package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public class ProfileDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileDAO.class);
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Profile fetchUserDetails(Long user_id) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, user_id);
        LOGGER.info("profile==1");

        TypedQuery<Profile> query = entityManager.createQuery("select pro from Profile pro where pro.registrationDetail.id=:user_id", Profile.class);
        query.setParameter("user_id", user_id);
        LOGGER.info("profile==2");
        Profile profile;
        try {
            profile = query.getSingleResult();
            profile.setRegistrationDetail(registrationDetail);

        } catch (NoResultException ex) {
            LOGGER.info("profile==3");
            profile = new Profile();
            profile.setRegistrationDetail(registrationDetail);
        }

        LOGGER.info("profile==" + profile);
        return profile;
    }

    @Transactional
    public void saveProfileDetail(Profile profileDetail) {
        LOGGER.info("profileDetail==" + profileDetail);
        TypedQuery<Profile> query = entityManager.createQuery("select pro from Profile pro where pro.registrationDetail.id=:user_id", Profile.class);
        query.setParameter("user_id", profileDetail.getRegistrationDetail().getId());
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, profileDetail.getRegistrationDetail().getId());
//        LOGGER.info("registrationDetail==" + registrationDetail);
        registrationDetail.setMobno(profileDetail.getRegistrationDetail().getMobno());
        registrationDetail.setFirstname(profileDetail.getRegistrationDetail().getFirstname());
        registrationDetail.setLastname(profileDetail.getRegistrationDetail().getLastname());
        entityManager.merge(registrationDetail);
        Profile profile;
        try {
            LOGGER.info("profile==2");
            profile = query.getSingleResult();
            Address address = entityManager.find(Address.class, profile.getAddress().getId());
//            LOGGER.info("address=" + address);
            address.setCity(profileDetail.getAddress().getCity());
            address.setState(profileDetail.getAddress().getState());
            address.setStreet_add(profileDetail.getAddress().getStreet_add());
            entityManager.merge(address);
            profile.setRegistrationDetail(profileDetail.getRegistrationDetail());
            profile.setAddress(profileDetail.getAddress());
            if (registrationDetail.getUsertype().equals("J")) {
                EducationExperience educationExperience = entityManager.find(EducationExperience.class, profile.getEducationExperience().getId());
                educationExperience.setExperience(profileDetail.getEducationExperience().getExperience());
                educationExperience.setExpected_salary(profileDetail.getEducationExperience().getExpected_salary());
                educationExperience.setHighest_degree(profileDetail.getEducationExperience().getHighest_degree());
                educationExperience.setPassing_year(profileDetail.getEducationExperience().getPassing_year());
                educationExperience.setPercentage(profileDetail.getEducationExperience().getPercentage());
                entityManager.merge(educationExperience);
                profile.setEducationExperience(educationExperience);
            }
//            LOGGER.info("profile==" + profile);
            entityManager.merge(profile);

        } catch (NoResultException ex) {
            LOGGER.info("profile==3");
            Address address = profileDetail.getAddress();
            entityManager.persist(address);
            entityManager.persist(profileDetail);
        }

    }
}
