/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.GeneralDao;
import domain.Exam;
import domain.Question;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import service.DaoService;

/**
 *
 * @author Nishimwe Elysee
 */
@ManagedBean
@RequestScoped
public class QuestionModel {

    //    search
    private DaoService qdao = new GeneralDao<>(Question.class);
    private Integer text1 = 0;
    private String question;
    private double outofmarks;

    public Integer getText1() {
        return text1;
    }

    public List<Integer> listofQuestions() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < text1; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public void setText1(Integer text1) {
        this.text1 = text1;
    }
    
    public void printdata(){
        System.out.println(this.getQuestion()+"     "+this.getOutofmarks());
    }

//    public void saveQuestion(){
//        
//        Exam ex = (Exam) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get('e');
//        System.out.println(ques.getQuestion());
//        System.out.println(ques.getOutofmarks());
//        System.out.println(ex.getName());
//        ques.setExam(ex);
//        qdao.save(ex);
//        System.out.println("Question Saved");
//    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getOutofmarks() {
        return outofmarks;
    }

    public void setOutofmarks(double outofmarks) {
        this.outofmarks = outofmarks;
    }
}
