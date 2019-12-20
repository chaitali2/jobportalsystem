package com.jobportal.jobportalsystem.service.profile;

import com.jobportal.jobportalsystem.dao.profile.ProfileDAO;
import com.jobportal.jobportalsystem.dto.profile.ProfileDTO;
import com.jobportal.jobportalsystem.model.profile.Address;
import com.jobportal.jobportalsystem.model.profile.EducationExperience;
import com.jobportal.jobportalsystem.model.profile.Profile;
import com.jobportal.jobportalsystem.model.registration.RegistrationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileDAO profileDAO;

    public ProfileDTO fetchUserDetails(Long user_id) {
        Profile profileDetail = profileDAO.fetchUserDetails(user_id);
        ProfileDTO profileDTO = convertModeltoDTO(profileDetail);
        return profileDTO;
    }

    public void saveProfileDetail(ProfileDTO profileDTO) {
        profileDAO.saveProfileDetail(convertDTOtoModel(profileDTO));
    }

    public ProfileDTO convertModeltoDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        if (profile.getRegistrationDetail() != null) {
            profileDTO.setFirstName(profile.getRegistrationDetail().getFirstName());
            profileDTO.setLastName(profile.getRegistrationDetail().getLastName());
            profileDTO.setMobileNo(profile.getRegistrationDetail().getMobileNo());
        }
        if (profile.getAddress() != null) {
            profileDTO.setCity(profile.getAddress().getCity());
            profileDTO.setState(profile.getAddress().getState());
            profileDTO.setStreet(profile.getAddress().getStreet());
        }
        if (profile.getEducationExperience() != null) {
            profileDTO.setExperience(profile.getEducationExperience().getExperience());
            profileDTO.setExpectedSalary(profile.getEducationExperience().getExpectedSalary());
            profileDTO.setHighestDegree(profile.getEducationExperience().getHighestDegree());
            profileDTO.setPassingYear(profile.getEducationExperience().getPassingYear());
            profileDTO.setPercentage(profile.getEducationExperience().getPercentage());
        }
        return profileDTO;
    }

    public Profile convertDTOtoModel(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setRegistrationDetail(getRegistrationUserDetail(profileDTO));
        profile.setAddress(getAddressDetail(profileDTO));
        profile.setEducationExperience(getEducationalExperienceDetail(profileDTO));
        return profile;
    }

    private RegistrationDetail getRegistrationUserDetail(ProfileDTO profileDTO) {
        RegistrationDetail registrationDetail = new RegistrationDetail();
        registrationDetail.setId(Long.parseLong(profileDTO.getUserID()));
        registrationDetail.setFirstName(profileDTO.getFirstName());
        registrationDetail.setLastName(profileDTO.getLastName());
        registrationDetail.setMobileNo(profileDTO.getMobileNo());
        return registrationDetail;
    }

    private Address getAddressDetail(ProfileDTO profileDTO) {
        Address address = new Address();
        address.setState(profileDTO.getState());
        address.setCity(profileDTO.getCity());
        address.setStreet(profileDTO.getStreet());
        return address;
    }

    private EducationExperience getEducationalExperienceDetail(ProfileDTO profileDTO) {
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setExperience(profileDTO.getExperience());
        educationExperience.setExpectedSalary(profileDTO.getExpectedSalary());
        educationExperience.setHighestDegree(profileDTO.getHighestDegree());
        educationExperience.setPassingYear(profileDTO.getPassingYear());
        educationExperience.setPercentage(profileDTO.getPercentage());
        return educationExperience;
    }

}
