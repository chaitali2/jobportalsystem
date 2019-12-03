package com.jobportal.jobportalsystem.dao.profile;

import com.jobportal.jobportalsystem.model.profile.Address;
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
    public Profile fetchUserDetails(String user_id) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, Long.parseLong(user_id));
        LOGGER.info("profile==1");

        TypedQuery<Profile> query = entityManager.createQuery("select pro from Profile pro where pro.registrationDetail.id=:user_id", Profile.class);
        query.setParameter("user_id", Long.parseLong(user_id));
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
        LOGGER.info("registrationDetail==" + registrationDetail);
        registrationDetail.setMobno(profileDetail.getRegistrationDetail().getMobno());
        registrationDetail.setFirstname(profileDetail.getRegistrationDetail().getFirstname());
        registrationDetail.setLastname(profileDetail.getRegistrationDetail().getLastname());
        entityManager.merge(registrationDetail);
        Profile profile;
        try {
            LOGGER.info("profile==2");
            profile = query.getSingleResult();
            Address address = entityManager.find(Address.class, profile.getAddress().getId());
            LOGGER.info("address="+address);
            address.setCity(profileDetail.getAddress().getCity());
            address.setState(profileDetail.getAddress().getState());
            address.setStreet_add(profileDetail.getAddress().getStreet_add());
            entityManager.merge(address);
            profile.setRegistrationDetail(registrationDetail);
            profile.setAddress(address);
            profile.setExperience(profileDetail.getExperience());
            profile.setExpected_salary(profileDetail.getExpected_salary());
            profile.setHighest_degree(profileDetail.getHighest_degree());
            profile.setPassing_year(profileDetail.getPassing_year());
            profile.setPercentage(profileDetail.getPercentage());
            LOGGER.info("profile=="+profile);
            entityManager.merge(profile);

        } catch (NoResultException ex) {
            LOGGER.info("profile==3");
            Address address = profileDetail.getAddress();
            entityManager.persist(address);
            entityManager.persist(profileDetail);
        }

    }
}
