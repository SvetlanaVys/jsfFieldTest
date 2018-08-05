package com.javatest.app.dao;

import com.javatest.app.model.Field;
import com.javatest.app.model.Option;
import com.javatest.app.model.QField;
import com.javatest.app.util.HibernateUtil;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Class for interplay with table 'Field' of DB
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
public class FieldDAO {
    private static Session session;
    private static SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    /**
     *
     * @return all row in a table 'Field'
     */
    public static List<Field> getFieldsList() {
        List<Field> fieldsList = null;
        try {
            session = sessionFactory.openSession();
            HibernateQueryFactory queryFactory = new HibernateQueryFactory(session);
            QField field = QField.field;
            fieldsList = queryFactory.selectFrom(field).fetch();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return fieldsList;
    }

    /**
     *
     * @return all row in a table 'Field' where property isActive is true
     */
    public static List<Field> getActiveFieldsList() {
        List<Field> fieldsList = null;
        try {
            session = sessionFactory.openSession();
            String hql = "SELECT f FROM Field f WHERE isActive = true";
            Query query = session.createQuery(hql);
            fieldsList = (List<Field>)query.getResultList();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return fieldsList;
    }

    /**
     *
     * @param newField is a created field
     * @return page with List of fields obtained using the method {@link #getFieldsList()}
     */
    public static String saveFieldDetails(Field newField) {
        int saveResult = 0;
        System.out.println("saveFieldDetails() : Field: " + newField);
        try {

            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(newField);
            session.getTransaction().commit();

        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }

        return "/views/fieldsList.xhtml?faces-redirect=true";
    }

    /**
     * This method receive a field by id for further modification
     * @param fieldId is field unique identifier
     * @return page for updating field
     */
    public static String editFieldRecord(Long fieldId) {

        Field editRecord = null;

        System.out.println("editFieldRecord() : Field Id: " + fieldId);

        /** Setting The Particular Field Details In Session */
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Map<String, Object> sessionMapOpt = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        try {
            session = sessionFactory.openSession();
            String hql = "SELECT f FROM Field f WHERE id = :field_id";
            Query query = session.createQuery(hql);
            editRecord = (Field)query.setParameter("field_id", fieldId).getSingleResult();
            sessionMapObj.put("editRecordObj", editRecord);
            sessionMapOpt.put("editRecordOpt", makeOptionString(editRecord));
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return  "/views/editField.xhtml?faces-redirect=true";
    }

    /**
     * Method updates the field received in the parameter
     * @param updateField is field unique identifier
     * @return page with List of fields obtained using the method {@link #getFieldsList()}
     */
    public static String updateFieldDetails(Field updateField) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(updateField);
            for(Option option: updateField.getOptions()) {
                if (option.getName() != "") {
                    session.saveOrUpdate(option);
                } else {
                    String hql = "DELETE FROM Option WHERE id = :id";
                    Query query = session.createQuery(hql);
                    query.setParameter("id", option.getId());
                    int result = query.executeUpdate();
                }
            }
            session.getTransaction().commit();

        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return "/views/fieldsList.xhtml?faces-redirect=true";
    }

    /**
     *
     * @param field is a field to remove
     * @return page with List of fields obtained using the method {@link #getFieldsList()}
     */
    public static String deleteFieldRecord(Field field){
        System.out.println("deleteFieldRecordInDB() : Field Id: " + field);
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(field);
            session.getTransaction().commit();
        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return "/views/fieldsList.xhtml?faces-redirect=true";
    }

    /**
     *
     * @param editField the object whose options will be turned into a string
     * @return string with this options
     */
    public static String makeOptionString(Field editField) {
        String optionString = "";
        for(Option option: editField.getOptions()) {
            optionString += option.getName() + "\n";
        }
        return optionString;
    }
}
