import java.util.LinkedList;
import java.util.List;

public class Rfa extends Subscription {

    private BudgetControl budgetControl;//1
    private LinkedList<Notification> notifications;// *

    public Rfa(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.budgetControl = new BudgetControl();
        this.notifications = new LinkedList<>();
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

    public List<Notification> getNotifications() {
        return notifications;
    }

}
