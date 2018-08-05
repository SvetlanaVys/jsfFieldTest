package com.javatest.app.dao;

import com.javatest.app.model.Profile;
import com.javatest.app.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
/**
 * Class for interplay with table 'Field' of DB
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
public class LoginDAO {

    private static Session session;
    private static SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    /**
     * User Authentication Method
     * @param email user email address
     * @param password user password
     * @return is this user exist in system
     */
    public static boolean validate(String email, String password) {
        /** Setting The Particular Field Details In Session */
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            session = sessionFactory.openSession();
            String hql = "SELECT p FROM Profile p WHERE email = :email";
            Query query = session.createQuery(hql);
            Profile profile = (Profile)query.setParameter("email", email).getSingleResult();
            if(session != null) {
                if(BCrypt.checkpw(password, profile.getPassword())) {
                    sessionMapObj.put("loginUser", profile);
                    return true;
                }
            }
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
            return false;
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return false;
    }
}
