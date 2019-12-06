package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.dao.profile.ProfileDAO;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileDAO profileDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileService.class);

    public ProfileDTO fetchUserDetails(Long user_id) {
        ProfileDTO profileDTO = new ProfileDTO();
        Profile profileDetail = profileDAO.fetchUserDetails(user_id);
        LOGGER.info("profileDetail=" + profileDetail);
        profileDTO = convertModeltoDTO(profileDetail);
        LOGGER.info("profileDTO=" + profileDTO);
        return profileDTO;
    }

    public void saveProfileDetail(ProfileDTO profileDTO) {
        profileDAO.saveProfileDetail(convertDTOtoModel(profileDTO));
    }

    public ProfileDTO convertModeltoDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        if (profile.getRegistrationDetail() != null) {
            profileDTO.setFirstname(profile.getRegistrationDetail().getFirstname());
            profileDTO.setLastname(profile.getRegistrationDetail().getLastname());
            profileDTO.setMobno(profile.getRegistrationDetail().getMobno());
        }
        if (profile.getAddress() != null) {
            profileDTO.setCity(profile.getAddress().getCity());
            profileDTO.setState(profile.getAddress().getState());
            profileDTO.setStreet_add(profile.getAddress().getStreet_add());
        }
        if (profile.getEducationExperience() != null) {
            profileDTO.setExperience(profile.getEducationExperience().getExperience());
            profileDTO.setExpected_salary(profile.getEducationExperience().getExpected_salary());
            profileDTO.setHighest_degree(profile.getEducationExperience().getHighest_degree());
            profileDTO.setPassing_year(profile.getEducationExperience().getPassing_year());
            profileDTO.setPercentage(profile.getEducationExperience().getPercentage());
        }

        return profileDTO;
    }

    public Profile convertDTOtoModel(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(profileDTO.getUserID()));
        registrationDetail.setFirstname(profileDTO.getFirstname());
        registrationDetail.setLastname(profileDTO.getLastname());
        registrationDetail.setMobno(profileDTO.getMobno());
        profile.setRegistrationDetail(registrationDetail);
        Address address = new Address();
        address.setState(profileDTO.getState());
        address.setCity(profileDTO.getCity());
        address.setStreet_add(profileDTO.getStreet_add());
        profile.setAddress(address);
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setExperience(profileDTO.getExperience());
        educationExperience.setExpected_salary(profileDTO.getExpected_salary());
        educationExperience.setHighest_degree(profileDTO.getHighest_degree());
        educationExperience.setPassing_year(profileDTO.getPassing_year());
        educationExperience.setPercentage(profileDTO.getPercentage());
        profile.setEducationExperience(educationExperience);
        return profile;
    }


}
