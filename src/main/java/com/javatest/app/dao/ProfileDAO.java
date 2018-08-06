package com.javatest.app.dao;

import com.javatest.app.model.Profile;
import com.javatest.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.context.RequestContext;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Class for interplay with table 'Profile' of DB
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
public class ProfileDAO {
    private static Session session;
    private static SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    /**
     * Create user`s profile
     * @param newProfile information about new user
     * @return login page
     */
    public static String saveProfileDetails(Profile newProfile) {
        int saveResult = 0;
        System.out.println("saveProfileDetails() : Profile Id: " + newProfile);
        newProfile.setPassword(BCrypt.hashpw(newProfile.getPassword(), BCrypt.gensalt()));
        try {

            session = sessionFactory.openSession();
            session.save(newProfile);
            return "login";

        } catch(Exception sqlException){
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     * Make information about current user for further modification
     * @param profileId id of current user
     * @return page for updating user`s information
     */
    public static String editProfileRecord(Long profileId) {

        Profile editRecord = null;

        System.out.println("editProfileRecord() : Profile Id: " + profileId);

        /* Setting The Particular Profile Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            session = sessionFactory.openSession();
            String hql = "SELECT p FROM Profile p WHERE id = :profile_id";
            Query query = session.createQuery(hql);
            editRecord = (Profile)query.setParameter("profile_id", profileId).getSingleResult();
            sessionMapObj.put("editRecordObj", editRecord);
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
//        return  editRecord;
        RequestContext context = RequestContext.getCurrentInstance();
        return "/views/editProfile.xhtml?faces-redirect=true";
    }

    /**
     * Update information about current user
     * @param updateProfile new information about user
     * @return login page
     */
    public static String updateProfileDetails(Profile updateProfile) {
        try {

            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(updateProfile);
            session.getTransaction().commit();
            return "/login.xhtml?faces-redirect=true";

        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     *
     * @param currentProfile registered user
     * @param oldPassword entered current user password
     * @param newPassword password to update
     * @return Login page or null if the current password does not match with @param oldPassword
     */
    public static String updateProfilePassword(Profile currentProfile, String oldPassword, String newPassword) {
        try {

            session = sessionFactory.openSession();
            session.beginTransaction();
            String selectHql = "SELECT p FROM Profile p WHERE id = :id";
            Query selectQuery = session.createQuery(selectHql);
            Profile profile = (Profile)selectQuery.setParameter("id", currentProfile.getId()).getSingleResult();
            if(BCrypt.checkpw(oldPassword, profile.getPassword())) {
                profile.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                session.update(profile);
                session.getTransaction().commit();
//                String hql = "UPDATE Profile SET password = :password WHERE id = :id";
//                Query query = session.createQuery(hql);
//                query.setParameter("id", id);
//                query.setParameter("password", BCrypt.hashpw(newPassword, BCrypt.gensalt()));
//                int result = query.executeUpdate();
                return "/login.xhtml?faces-redirect=true";
            }

        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return null;
    }

    /**
     *
     * @param profile is a profile to remove
     */
    public static void deleteFieldRecord(Profile profile){
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(profile);
            session.getTransaction().commit();
        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
