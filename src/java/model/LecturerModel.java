/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.GeneralDao;
import domain.Exam;
import domain.Lecturer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import service.DaoService;

/**
 *
 * @author Nishimwe Elysee
 */
@ManagedBean(name = "lecturerModel",eager = true)
@SessionScoped
public class LecturerModel {

    private Lecturer lect = new Lecturer();
    private Exam exam = new Exam();
    DaoService<Lecturer> Lecturerdao = new GeneralDao<>(Lecturer.class);
    DaoService<Exam> examdao = new GeneralDao<>(Exam.class);
    private Map<String, Object> sessionmap= null;
    private String role;
    private String confirm_password;

    public Lecturer getLect() {
        return lect;
    }

    public void setLect(Lecturer lect) {
        this.lect = lect;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String login() {
        sessionmap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        FacesContext context = FacesContext.getCurrentInstance();
        List<Lecturer> lects = Lecturerdao.findAll();
        
        for (Lecturer lect1 : lects) {
            
            if (lect1.getEmail().equals(lect.getEmail())) {
                if (lect1.getPassword().equals(lect.getPassword())) {
                    sessionmap.put("l", lect1);
                    lect = lect1;
                    context.addMessage(null, new FacesMessage("LoggedIn successfully"));
                    return "Dashboard?faces-redirect=true";
                } else {

                    context.addMessage(null, new FacesMessage("Invalid Password"));
                    lect.setEmail(null);
                    lect.setPassword(null);
                    return null;

                }
            }
        }
        System.out.println("No Data Found");
        context.addMessage(null, new FacesMessage("Invalid Email"));
        lect.setEmail(null);
        lect.setPassword(null);
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String signUp() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Lecturerdao.save(lect);
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage("Invalid Signup"));
            return null;
        }
        return "login?faces-redirect=true";
    }

    public String Username() {
//        Map<String, Object> ss = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//        Lecturer lec = (Lecturer) ss.get("l");
        if (lect.getEmail() == null) {
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
            System.out.println(lect.getFullname());
            return lect.getFullname();
        }
    }

    public String redirectPage(String page) {
        return page;
    }
}
