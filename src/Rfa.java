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

    }

    //yarden
    public boolean addReferee(String name, String phoneNumber, String email, String userName, String password, String qualification){
        if(checkValidDetails(userName,password,phoneNumber)){
            Referee newRef= new Referee(system,name,phoneNumber,email,userName,password,qualification);
            system.addUser(newRef);
            return true;
        }
        return false;
    }

    public void addLeague(){

    }

    //yarden
    public boolean deleteReferee(Referee ref){
        //check the all matches that the referee is refereeing
        for (Match m : ref.getMatches()) {
            //can't delete, match can't be without referee
            if(m.getReferees().size() == 1){
                System.out.println("You can not delete this referee");
                return false;
            }
        }
        return system.removeUser(ref);
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
