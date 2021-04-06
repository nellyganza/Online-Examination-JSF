/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.GeneralDao;
import domain.Answer;
import domain.Exam;
import domain.Lecturer;
import domain.Question;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import service.DaoService;

@ManagedBean
@SessionScoped
public class CrudView implements Serializable {

    private List<Question> quess = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();

    private DaoService<Exam> examdao = new GeneralDao<>(Exam.class);
    private DaoService<Question> qdao = new GeneralDao<>(Question.class);
    private List<Exam> exams;

    private Exam selectedExam = new Exam();
    private Question ques = new Question();
    private Answer answer = new Answer();

    private List<Exam> selectedExams;

    public List<Exam> getExams() {
        exams = examdao.findAll();
        return exams;
    }

    public Exam getSelectedExam() {
        return selectedExam;
    }

    public void setSelectedExam(Exam selectedExam) {
        this.selectedExam = selectedExam;
    }

    public List<Exam> getSelectedExams() {
        return selectedExams;
    }

    public void setSelectedExams(List<Exam> selectedExams) {
        this.selectedExams = selectedExams;
    }

    public Question getQues() {
        return ques;
    }

    public void setQues(Question ques) {
        this.ques = ques;
    }

    public List<Question> getQuestions() {
        return quess;
    }

    public void setQuestions(List<Question> quess) {
        this.quess = quess;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void openNew() {
        this.selectedExam = new Exam();
    }

    public String saveExam() {
        Lecturer lec = (Lecturer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get('l');
        System.out.println("Saving Exam");
        Exam ex = new Exam();
        ex.setExamId(200);
        ex.setCategory(selectedExam.getCategory());
        ex.setName(selectedExam.getName());
        ex.setDate(selectedExam.getDate());
        ex.setHours(selectedExam.getHours());
        ex.setNumberofQuestion(selectedExam.getNumberofQuestion());
        ex.setOutofmarks(selectedExam.getOutofmarks());
        ex.setLecturer(lec);
        try {
            examdao.save(ex);
            System.out.println("Exam Saved");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("e", ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exam Prepared"));
            return "addExamsQuestions";
        } catch (Exception e) {
            System.out.println("Exam not Saved");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateExam() {
        System.out.println("Updating Exam");
        try {
            examdao.update(selectedExam);
            System.out.println("Exam Saved");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exam Updated"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-exams");
        } catch (Exception e) {
            System.out.println("Exam not UPdated");
            System.out.println(e.getMessage());
        }
    }

    public void deleteExam() {
        examdao.delete(selectedExam);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exam Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-exams");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedExams()) {
            int size = this.selectedExams.size();
            return size > 1 ? size + " exams selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedExams() {
        return this.selectedExams != null && !this.selectedExams.isEmpty();
    }

    public void deleteSelectedExams() {
        examdao.deleteAll(this.selectedExams);
        this.selectedExams = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exams Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-exams");
        PrimeFaces.current().executeScript("PF('dtExams').clearFilters()");
    }

    public List<Integer> numberOfQuestions() {
        List<Integer> numbs = new ArrayList<>();
        for (int i = 0; i < selectedExam.getNumberofQuestion(); i++) {
            numbs.add(i);
        }
        return numbs;
    }

//    Question Code
    public String saveQuestion() {
        try {
            System.out.println(ques.getQuestion());
            System.out.println(ques.getOutofmarks());
            ques.setExam(selectedExam);
            qdao.save(ques);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("q", ques);
            System.out.println("Question Saved");
            return "addQuestionAnswers";
        } catch (Exception e) {
            System.out.println("Question Not Saved");
        }
        return null;
    }

    public void addAnswerToList() {
        answers.add(answer);
        System.out.println("Added");
    }

}
