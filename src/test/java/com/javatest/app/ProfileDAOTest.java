package com.javatest.app;

import com.javatest.app.dao.ProfileDAO;
import com.javatest.app.model.Profile;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileDAOTest {

    private ProfileDAO profileDAO = new ProfileDAO();
    private Profile profile = createProfile("test-email@gmail.com", "password", "FirstName", "LastName", "0995436230");

    private Profile createProfile(String email, String password, String firstName, String lastName, String phone) {
        Profile profile = new Profile();
        profile.setEmail(email);
        profile.setPassword(password);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhone(phone);
        return profile;
    }

    @Test
    public void testSaveProfile() {
        this.profileDAO.saveProfileDetails(this.profile);
    }

    @Test(dependsOnMethods={"testSaveProfile"})
    public void testUpdateProfile() {
        Long id = this.profile.getId();
        this.profile = createProfile("newemail@com.ua", this.profile.getPassword(), "NewName", "NewLastName", "09992162307");
        this.profile.setId(id);
        this.profileDAO.updateProfileDetails(this.profile);
    }

    @Test(dependsOnMethods={"testSaveProfile"})
    public void testUpdatePrassword() {
        this.profileDAO.updateProfilePassword(this.profile, this.profile.getPassword(), "newpassword");
    }

    @Test(dependsOnMethods={"testSaveProfile"})
    public void testNoUpdatePrassword() {
        Assert.assertNull(this.profileDAO.updateProfilePassword(this.profile, "invalidpassword", "newpassword"));
    }

    @Test(dependsOnMethods={"testNoUpdatePrassword", "testUpdatePrassword", "testUpdateProfile"})
    public void testDeleteProfile() {
        this.profileDAO.deleteFieldRecord(this.profile);
    }
}
