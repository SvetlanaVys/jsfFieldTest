package com.javatest.app.bean;

import com.javatest.app.dao.ProfileDAO;
import com.javatest.app.model.Profile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Class for interplay with 'profile' pages
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@ManagedBean
@RequestScoped
public class ProfileBean {
    private Profile profile;
    private String newPassword;
    private String oldPassword;

    public Profile getProfile() {
        return this.profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getNewPassword() {
        return this.newPassword;
    }
    public void setNewPassword(String password) {
        this.newPassword = password;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }
    public void setOldPassword(String password) {
        this.oldPassword = password;
    }

    /**
     * Initialize method
     */
    @PostConstruct
    public void init() {
        profile = new Profile();
    }

    /**
     * Create Account (Method for user registration)
     * @param newProfile new user information
     * @return result string or current if it was error
     */
    public String saveProfileDetails(Profile newProfile) {
        String result = ProfileDAO.saveProfileDetails(newProfile);
        if(result != null) {
            return result;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Email already used",
                            "Please enter other email"));
            return "createProfile.xhtml";
        }

    }
    public String editProfileRecord(Long profileId) {
        return ProfileDAO.editProfileRecord(profileId);
    }

    /**
     *
     * @param updateProfile
     * @return
     */
    public String updateProfileDetails(Profile updateProfile) {
        String result = ProfileDAO.updateProfileDetails(updateProfile);
        if(result != null) {
            return result;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Email already used",
                            "Please enter other email"));
            return "/views/editProfile.xhtml";
        }
    }

    public String updateProfilePassword(Long id) {
        String result = ProfileDAO.updateProfilePassword(id, oldPassword, newPassword);
        if(result != null) {
            return result;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid current password",
                            "Please enter correct current password"));
            return "/views/editPassword.xhtml";
        }
    }
}

