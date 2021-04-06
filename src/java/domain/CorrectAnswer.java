/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Nishimwe Elysee
 */
@Entity
@Table(name = "CorrectAnswer")
public class CorrectAnswer extends Answer implements Serializable{

    public CorrectAnswer() {
    }
    
    public CorrectAnswer(Integer answerID, List<String> options, Question question) {
        super(answerID, options, question);
    }
    
    
}
