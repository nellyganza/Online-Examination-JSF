/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
@Table(name = "StudentAnswer")
public class StudentAnswer extends Answer implements Serializable{
    @OneToOne
    private Student student;
    private double gotmarks=0.0;
    private boolean passed=false;
    public StudentAnswer() {
    }

    public StudentAnswer(Student student, double gotmarks, boolean passed, Integer answerID, List<String> options, Question question) {
        super(answerID, options, question);
        this.student = student;
        this.gotmarks = gotmarks;
        this.passed = passed;
    }
    
    
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getGotmarks() {
        return gotmarks;
    }

    public void setGotmarks(double gotmarks) {
        this.gotmarks = gotmarks;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
    
    
}
