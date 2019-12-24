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

    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileDAO.class);

    public Profile fetchUserDetails(Long user_id) {
        RegistrationDetail registrationDetail = findRegistrationDetail(user_id);

        TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile" +
                        " where profile.registrationDetail.id=:user_id",
                Profile.class);
        query.setParameter("user_id", user_id);
        Profile profile;
        try {
            profile = query.getSingleResult();
            profile.setRegistrationDetail(registrationDetail);
            return profile;
        } catch (NoResultException ex) {
            return null;
        }
    }


    public RegistrationDetail findRegistrationDetail(Long user_id) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, user_id);
        return registrationDetail;
    }

    @Transactional
    public void saveProfileDetail(Profile profileDetail) {
        RegistrationDetail registrationDetail = updateProfileRegisterDetail(profileDetail);
        try {
            TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile where " +
                    "profile.registrationDetail.id=:user_id", Profile.class);
            query.setParameter("user_id", profileDetail.getRegistrationDetail().getId());
            Profile profile = query.getSingleResult();
            updateAddressDetail(profile, profileDetail);
            profile.setRegistrationDetail(profileDetail.getRegistrationDetail());
            if (registrationDetail.getUserType().toString().equals("J")) {
                profile.setEducationExperience(updateEducationExperience(profile,profileDetail));
            }
            entityManager.merge(profile);
        } catch (NoResultException ex) {
            LOGGER.info("exception", ex);
            entityManager.persist(profileDetail);
        }
    }

    private RegistrationDetail updateProfileRegisterDetail(Profile profileDetail) {
        RegistrationDetail registrationDetail = entityManager.find(RegistrationDetail.class, profileDetail.getRegistrationDetail().getId());
        registrationDetail.setMobileNo(profileDetail.getRegistrationDetail().getMobileNo());
        registrationDetail.setFirstName(profileDetail.getRegistrationDetail().getFirstName());
        registrationDetail.setLastName(profileDetail.getRegistrationDetail().getLastName());
        entityManager.merge(registrationDetail);
        return registrationDetail;
    }

    private void updateAddressDetail(Profile profile, Profile profileDetail) {
        Address address = entityManager.find(Address.class, profile.getAddress().getId());
        address.setCity(profileDetail.getAddress().getCity());
        address.setState(profileDetail.getAddress().getState());
        address.setStreet(profileDetail.getAddress().getStreet());
        entityManager.merge(address);
    }

    private EducationExperience updateEducationExperience(Profile profile, Profile profileDetail) {
        EducationExperience educationExperience = entityManager.find(EducationExperience.class, profile.getEducationExperience().getId());
        educationExperience.setExperience(profileDetail.getEducationExperience().getExperience());
        educationExperience.setExpectedSalary(profileDetail.getEducationExperience().getExpectedSalary());
        educationExperience.setHighestDegree(profileDetail.getEducationExperience().getHighestDegree());
        educationExperience.setPassingYear(profileDetail.getEducationExperience().getPassingYear());
        educationExperience.setPercentage(profileDetail.getEducationExperience().getPercentage());
        entityManager.merge(educationExperience);
        return educationExperience;
    }
}
