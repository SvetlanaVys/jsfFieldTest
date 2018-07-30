package com.javatest.app.dao;

import com.javatest.app.model.QResponse;
import com.javatest.app.model.Response;
import com.javatest.app.util.HibernateUtil;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

/**
 * Class for interplay with table 'Response' of DB
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
public class ResponseDAO {
    private static Session session;
    private static SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
    /**
     *
     * @return all row in a table 'Response'
     */
    public static List<Response> getResponsesList() {
        List<Response> responsesList = null;
        try {
            /** session is a Hibernate session */
            session = HibernateUtil.getSessionFactory().openSession();
            HibernateQueryFactory queryFactory = new HibernateQueryFactory(session);
            QResponse response = QResponse.response;
            responsesList = queryFactory.selectFrom(response).fetch();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return responsesList;
    }

    /**
     *
     * @param newResponses is a created response
     * @return true if save was correct
     */
    public static String  saveResponseDetails(List<Response> newResponses) {
        int saveResult = 0;
        System.out.println("saveResponseDetails() : Responses" + newResponses);
        try {

            session = sessionFactory.openSession();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for(Response response: newResponses) {
                session.persist(response);
                session.flush();
                String hql = "UPDATE Field SET rowNumber = :rowNumber WHERE id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("rowNumber", (response.getField().getRowNumber() + 1));
                query.setParameter("id", response.getField().getId());
                int result = query.executeUpdate();
            }
        } catch(Exception sqlException){
            session.getTransaction().rollback();
            sqlException.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }

        return "/successResponse.xhtml?faces-redirect=true";
    }
}
