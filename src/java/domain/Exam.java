/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer examId;
    private String name;
    private String category;
    private Integer outofmarks;
    private Double hours;
    private Integer numberofQuestion;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private ExamStatus status=ExamStatus.PENDING;
    
    @ManyToOne
    @JoinColumn(name = "lecturerId",nullable = false)
    private Lecturer lecturer;
    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL)
    private List<Student> students;
    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL)
    private List<Question> questions;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getOutofmarks() {
        return outofmarks;
    }

    public void setOutofmarks(Integer outofmarks) {
        this.outofmarks = outofmarks;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Integer getNumberofQuestion() {
        return numberofQuestion;
    }

    public void setNumberofQuestion(Integer numberofQuestion) {
        this.numberofQuestion = numberofQuestion;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ExamStatus getStatus() {
        return status;
    }

    public void setStatus(ExamStatus status) {
        this.status = status;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    
    
}
