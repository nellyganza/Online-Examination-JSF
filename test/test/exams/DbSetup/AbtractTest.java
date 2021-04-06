/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.exams.DbSetup;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 *
 * @author Nishimwe Elysee
 */
public class AbtractTest {
    public void excute(Operation operation){
        DbSetup db = new DbSetup(new DriverManagerDestination("jdbc:postgresql://localhost:5432/onlineExams", "elysee", "Elysee@123"),operation);
        db.launch();
    }
}
