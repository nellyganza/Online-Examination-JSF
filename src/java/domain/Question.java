/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer questionId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "examId",nullable = false)
    private Exam exam;
    @Column(nullable = false)
    private String question;
    private double outofmarks;
    
    
    
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question() {
    }

    public Question(Integer questionId, Exam exam, String question, double outofmarks) {
        this.questionId = questionId;
        this.exam = exam;
        this.question = question;
        this.outofmarks = outofmarks;
    }

    
    

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public double getOutofmarks() {
        return outofmarks;
    }

    public void setOutofmarks(double outofmarks) {
        this.outofmarks = outofmarks;
    }
    
    
    
}
