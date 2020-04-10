import java.util.List;

public class Rfa extends Subscription {

    private BudgetControl budgetControl;//1
    private List<Notification> notifications;// *

    public Rfa(BudgetControl budgetControl, List<Notification> notifications) {
        this.budgetControl = budgetControl;
        this.notifications = notifications;
    }

    public void addRefree(){

    }

    public void addLegue(){

    }

    public void deleteRefree(){

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

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
