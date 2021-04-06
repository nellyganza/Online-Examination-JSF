/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
public class Lecturer extends Person{
    private String Qualification;

    public Lecturer(String fullname, String phone, String email, String username, String password,String Qualification) {
        super(fullname, phone, email, username, password);
        this.Qualification =Qualification;
    }

    public Lecturer() {
    }
    

   

    
    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }
    @OneToMany(mappedBy = "lecturer",cascade = CascadeType.ALL)
    private List<Exam> exams;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
    
    
}
