/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
public class Student extends Person{
    private String faculty;
    private String department;
    private String address;
    @ManyToOne
    @JoinColumn(name = "exam")
    private Exam exam;

    public Student(String faculty, String address, String fullname, String phone, String email, String username, String password) {
        super(fullname, phone, email, username, password);
        this.faculty = faculty;
        this.address = address;
    }

    public Student() {
    }
    

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepaartment() {
        return department;
    }

    public void setDepaartment(String depaartment) {
        this.department = depaartment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
