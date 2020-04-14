import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Rfa extends Subscription {

    private BudgetControl budgetControl;//1
    private static final Logger LOG = LogManager.getLogger();

    public Rfa(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.budgetControl = new BudgetControl();
        //TODO add permissions

    }

    public Rfa(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.budgetControl = new BudgetControl();
        //TODO add permissions
        //this.permissions.add();
    }

    /**Yarden**/
    public void createNewLeague(){

    }

    /**Yarden**/
    public void addReferee(String name, String phoneNumber, String email, String userName, String password, String qualification) throws Exception {
        if (checkValidDetails(userName, password, phoneNumber,email)) {
            Referee newRef = new Referee(system, name, phoneNumber, email, userName, password, qualification);
            system.addUser(newRef);
        }
        else {
            throw new Exception("Invalid details - You can not add this referee");
        }
    }

    /**Yarden**/
    public void deleteReferee(Referee ref) throws Exception {
        //check the all matches that the referee is refereeing
        for (Match m : ref.getMatches()) {
            //can't delete, match still didn't take place
            if (m.getDate().after(new Date(System.currentTimeMillis()))) {
                throw new Exception("You can not delete this referee");
            }
        }
        system.removeUser(ref);
    }

    public void addPolicy(){

    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    public void setBudgetControl(BudgetControl budgetControl) {
        this.budgetControl = budgetControl;
    }


}
