import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Rfa extends Subscription {

    private BudgetControl budgetControl;//1
    private static final Logger LOG = LogManager.getLogger();

    public Rfa(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.budgetControl = new BudgetControl();
        //TODO add permissions
        //this.permissions.add();
    }

    public Rfa(MainSystem ms, String name, String phoneNumber, String email, String userName, String password) {
        super(ms,name,phoneNumber,email,userName,password);
        this.budgetControl = new BudgetControl();
        //TODO add permissions
        //this.permissions.add();
    }
    public void addReferee(){

    }

    public void addLeague(){

    }

    public void deleteReferee(){

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
