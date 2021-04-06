package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import model.*;
import dao.GeneralDao;
import domain.Exam;
import domain.Lecturer;
import domain.Student;
import domain.StudentAnswer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import service.DaoService;

/**
 *
 * @author Nishimwe Elysee
 */
@ManagedBean(name = "StudentModel")
@RequestScoped
public class StudentModel {

    private Map<String, Object> sessionmap= FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    FacesContext context = FacesContext.getCurrentInstance();
    private String role;
    private String confirm_password;

    private Student st = new Student();
    DaoService<Student> dao = new GeneralDao<>(Student.class);
    DaoService<StudentAnswer> sadao = new GeneralDao<>(StudentAnswer.class);
    public Student getSt() {
        return st;
    }

    public void setSt(Student st) {
        this.st = st;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    

    public String loginStudent() {
         List<Student> students = dao.findAll();
        
        for (Student std : students) {
            System.out.println(std.getFullname()+"  "+st.getEmail());
            if (std.getEmail().equals(st.getEmail())) {
                if (std.getPassword().equals(st.getPassword())) {
                    sessionmap.put("s", std);
                    context.addMessage(null, new FacesMessage("LoggedIn successfully"));
                    return "DashboardStudent?faces-redirect=true";
                } else {

                    context.addMessage(null, new FacesMessage("Invalid Password"));
                    st.setEmail(null);
                    st.setPassword(null);
                    return null;

                }
            }
        }
        System.out.println("No Data Found");
        context.addMessage(null, new FacesMessage("Invalid Email"));
        st.setEmail(null);
        st.setPassword(null);
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String signUp() {
        try {
            dao.save(st);
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Invalid Signup"));
            return null;
        }
        return "login?faces-redirect=true";
    }

    public String Username() {
        Student std = (Student) sessionmap.get("s");
        System.out.println("The Studwent is " + std);
        if (std == null) {
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            try {
                ec.redirect(ec.getRequestContextPath()
                        + "/faces/login.xhtml");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        } else {
            return std.getFullname();
        }
    }
}
