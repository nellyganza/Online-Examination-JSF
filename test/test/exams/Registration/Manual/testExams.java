/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.exams.Registration.Manual;

import dao.GeneralDao;
import domain.Answer;
import domain.CorrectAnswer;
import domain.Exam;
import domain.Lecturer;
import domain.Question;
import domain.Student;
import java.time.LocalDateTime;
import java.util.List;
import model.CrudView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.exams.DbSetup.AbtractTest;
import test.exams.DbSetup.DbSetup;
import utils.HibernateUtil;

/**
 *
 * @author Nishimwe Elysee
 */
public class testExams extends AbtractTest{
    GeneralDao<Lecturer> ldao = new GeneralDao<>(Lecturer.class);
    GeneralDao<Student> sdao = new GeneralDao<>(Student.class);
    GeneralDao<Exam> edao = new GeneralDao<>(Exam.class);
    GeneralDao<Question> qdao = new GeneralDao<>(Question.class);
    GeneralDao<Answer> adao = new GeneralDao<>(Answer.class);
    GeneralDao<CorrectAnswer> cadao = new GeneralDao<>(CorrectAnswer.class);
    
    @Test
    public void testgetAllExams(){
        CrudView exmodel = new CrudView();
        List<Exam> exams = exmodel.getExams();
        Assert.assertNotNull(exams);
    }
    
    
    @Test
    public void testCreateExam(){
        Exam ex = new Exam();
        ex.setCategory("Accounting");
        ex.setName("Principle of Accounting");
        ex.setDate(LocalDateTime.MAX);
        ex.setHours(3.0);
        ex.setNumberofQuestion(5);
        ex.setOutofmarks(30);
        ex.setLecturer(ldao.findById(100));
        edao.save(ex);
    }
    
    @BeforeClass
    public void hibernateInit(){
        HibernateUtil.getSessionFactory();
    }
    @BeforeMethod
    public void initializeDb(){
        excute(DbSetup.INSERT_LECTURER);
        excute(DbSetup.INSERT_STUDENT);
        excute(DbSetup.INSERT_EXAM);
        excute(DbSetup.INSERT_QUESTION);
        excute(DbSetup.INSERT_ANSWER);
        excute(DbSetup.INSERT_ANSWER_OPTION);
        excute(DbSetup.INSERT_CORRECT_ANSWER);
        excute(DbSetup.INSERT_CORRECT_ANSWER_OPTION);
        excute(DbSetup.INSERT_STUDENT_ANSWER);
        excute(DbSetup.INSERT_STUDENT_ANSWER_OPTION);
        excute(DbSetup.INSERT_RESULT);
    }
    @AfterMethod
    public void cleanDb(){
        excute(DbSetup.DELETE_RESULT);
        excute(DbSetup.DELETE_ANSWER_OPTION);
        excute(DbSetup.DELETE_ANSWER);
        excute(DbSetup.DELETE_QUESTION);
        excute(DbSetup.DELETE_EXAM);
        excute(DbSetup.DELETE_LECTURER);
        excute(DbSetup.DELETE_STUDENT);
    }
}
