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
import utils.HibernateUtil;
import domain.Student;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.exams.DbSetup.AbtractTest;
import test.exams.DbSetup.DbSetup;

/**
 *
 * @author Nishimwe Elysee
 */
public class TestLecturer extends AbtractTest{
    GeneralDao<Lecturer> ldao = new GeneralDao<>(Lecturer.class);
    GeneralDao<Student> sdao = new GeneralDao<>(Student.class);
    GeneralDao<Exam> edao = new GeneralDao<>(Exam.class);
    GeneralDao<Question> qdao = new GeneralDao<>(Question.class);
    GeneralDao<Answer> adao = new GeneralDao<>(Answer.class);
    GeneralDao<CorrectAnswer> cadao = new GeneralDao<>(CorrectAnswer.class);
    
    @Test
    public void registerLecturer(){
        Lecturer l = new Lecturer("Mukamisha Jacky", "0788985654", "mukamisha@gmail.com", "jacky", "jacky123", "Bachelor");
        l.setPersonId(0);
        ldao.save(l);
    }
    @Test
    public void testFindLecturer(){
        Lecturer l = ldao.findById(100);
        Assert.assertNotNull(l);
    }
    @Test
    public void testFindLecturerFailed(){
        Lecturer l = ldao.findById(97);
        Assert.assertNull(l);
    }
    @Test
    public void prepareExam(){
        Exam ex = new Exam();
        Lecturer l = ldao.findById(100);
        ex.setLecturer(l);
        ex.setCategory("Mathematic");
        ex.setHours(4.0);
        ex.setName("Mathematic");
        ex.setNumberofQuestion(5);
        ex.setOutofmarks(20);
        edao.save(ex);
    }
    @Test(expectedExceptions = HibernateException.class)
    public void prepareExamFailedWhenNoLecturer(){
        Exam ex = new Exam();
        Lecturer l = ldao.findById(98);
        ex.setLecturer(l);
        ex.setCategory("Mathematic");
        ex.setHours(4.0);
        ex.setName("Mathematic");
        ex.setNumberofQuestion(5);
        ex.setOutofmarks(20);
        edao.save(ex);
    }
    
    @Test
    public void testAddExamQuestion(){
        Question qus = new Question(7, edao.findById(100), "What id Webtechnology ?",2.5);
        qdao.save(qus);
    }
    @Test(expectedExceptions = HibernateException.class)
    public void testAddExamQuestionFail(){
        Question qus = new Question(8, edao.findById(2), null,2.5);
        qdao.save(qus);
    }
    
    @Test
    public void addAnswers(){
        List<String> answers = new ArrayList<>();
        answers.add("3000Km");
        answers.add("4000Km");
        answers.add("7000Km"); 
        answers.add("10000Km");
        answers.add("190000Km");
        Answer ans = new  Answer(2, answers, qdao.findById(100));
        adao.save(ans);
    }
    @Test(expectedExceptions = HibernateException.class)
    public void addAnswersFailWhenNoQuestion(){
        List<String> answers = new ArrayList<>();
        answers.add("3000Km");
        answers.add("4000Km");
        answers.add("7000Km"); 
        answers.add("10000Km");
        Answer ans = new  Answer();
        ans.setOptions(answers);
        ans.setQuestion(null);
        adao.save(ans);
    }
    @Test
    public void addCorrectAnswers(){
        List<String> answers = new ArrayList<>();
        answers.add("10000Km");
        CorrectAnswer ans = new  CorrectAnswer(3, answers, qdao.findById(100));
        cadao.save(ans);
    }
    @Test(expectedExceptions = HibernateException.class)
    public void addCorrectAnswersFailWhenNoQuestion(){
        List<String> answers = new ArrayList<>();
        answers.add("10000Km");
        CorrectAnswer ans = new  CorrectAnswer(4, answers, null);
        cadao.save(ans);
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
