import java.util.LinkedList;
import java.util.List;

public class SystemManager extends Subscription{

    private LinkedList<Notification> notifications;//*
    private LinkedList<Complaint> complaints;//*

    public SystemManager(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.notifications = new LinkedList<>();
        this.complaints = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public void buildRecomandationSystem(){

    }

    //<editor-fold desc="getters and setters">
    public List<Notification> getNotifications() {
        return notifications;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    //</editor-fold>
}

