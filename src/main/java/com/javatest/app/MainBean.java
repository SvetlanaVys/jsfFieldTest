package com.javatest.app;

import com.javatest.app.util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean implements Serializable {

    private String inputText;
//    private String theme = "blitzer";
//    private String theme = "cruze";
    private String theme = "excite-bike";
//    private String theme = "hot-sneaks";
    private static SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();

    public String getTheme()
    {
        return theme;
    }
}