package com.javatest.app.bean;

import com.javatest.app.dao.LoginDAO;
import com.javatest.app.util.SessionUtil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.faces.context.FacesContext;

/**
 * Class for interplay with Login page
 * @author Vyskrebentseva Svetlana
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * validate login
     * @return accessible page or login page if validation fails
     */
    public String validateUsernamePassword() {
        if(user == "" || pwd == "") {
            return "login";
        }
        boolean valid = LoginDAO.validate(user, pwd);
        if (valid) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("username", user);
            return "/views/fieldsList.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Email and Passowrd",
                            "Please enter correct Email and Password"));
            return "login";
        }
    }

    /**
     * logout event, invalidate session
     * @return login page
     */
    public String logout() {
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}