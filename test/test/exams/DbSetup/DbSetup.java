/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.exams.DbSetup;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Nishimwe Elysee
 */
public class DbSetup {
    
    public static Operation INSERT_LECTURER = Operations
            .insertInto("lecturer")
            .columns("id","fullname","phone","email","username","password","Qualification")
            .values(100,"Nishimwe Elysee","0788787656","nishimwelys2@gmail.com","elysee","elysee@123","Masters")
            .build();
    public static Operation INSERT_EXAM = Operations
            .insertInto("exam")
            .columns("id","name","category","outofmarks","hours","numberofQuestion","lecturerId")
            .values(100,"Mathematic","Mathematics",20,3,5,100)
            .build();
    public static Operation INSERT_QUESTION = Operations
            .insertInto("question")
            .columns("id","examid","question","outofmarks")
            .values(100,100,"What is the Size of Rwanda?",2)
            .build();
    public static Operation INSERT_ANSWER = Operations
            .insertInto("answer")
            .columns("dtype","id","questionID")
            .values("answer",100,100)
            .build();
    public static Operation INSERT_ANSWER_OPTION = Operations
            .insertInto("answer_options")
            .columns("answer_id","answers")
            .values(100,"2000Km")
            .values(100,"50000Km")
            .values(100,"70000Km")
            .values(100,"30000Km")
            .build();
    public static Operation INSERT_CORRECT_ANSWER = Operations
            .insertInto("answer")
            .columns("dtype","id","questionID")
            .values("correctanswer",101,100)
            .build();
    public static Operation INSERT_CORRECT_ANSWER_OPTION = Operations
            .insertInto("answer_options")
            .columns("answer_id","answers")
            .values(101,"2000Km")
            .values(101,"50000Km")
            .build();
    public static Operation INSERT_STUDENT_ANSWER = Operations
            .insertInto("answer")
            .columns("dtype","id","questionID","student_id","gotmarks","passed")
            .values("studentAnswer",102,100,100,0,false)
            .build();
    public static Operation INSERT_STUDENT_ANSWER_OPTION = Operations
            .insertInto("answer_options")
            .columns("answer_id","answers")
            .values(102,"2000Km")
            .values(102,"50000Km")
            .build();
    public static Operation INSERT_STUDENT = Operations
            .insertInto("student")
            .columns("id","faculty","address","fullname","phone","email","username","password")
            .values(100,"IT","Kigali","Hirwa Elie","0788997876","elie@gmail.com","elie","elie@123")
            .build();
    
    public static Operation INSERT_RESULT = Operations
            .insertInto("result")
            .columns("id","total","exam_id","student_id")
            .values(100,10,100,100)
            .build();
    
    
    
    public static Operation DELETE_LECTURER = Operations
            .deleteAllFrom("lecturer");
    public static Operation DELETE_EXAM = Operations
            .deleteAllFrom("exam");
    public static Operation DELETE_QUESTION = Operations
            .deleteAllFrom("question");
    public static Operation DELETE_ANSWER = Operations
            .deleteAllFrom("answer");
    public static Operation DELETE_ANSWER_OPTION = Operations
            .deleteAllFrom("answer_options");
    public static Operation DELETE_STUDENT = Operations
            .deleteAllFrom("student");
    public static Operation DELETE_RESULT= Operations
            .deleteAllFrom("result");
}
