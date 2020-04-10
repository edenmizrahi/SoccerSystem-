import java.util.List;

public class SystemManager extends Subscription{

    private MainSystem mainSystem; //1
    private List<Notification> notifications;//*
    private List<Complaint> complaints;//*

    public SystemManager(MainSystem mainSystem, List<Notification> notifications, List<Complaint> complaints) {
        this.mainSystem = mainSystem;
        this.notifications = notifications;
        this.complaints = complaints;
    }


    public void buildRecomandationSystem(){

    }

    public MainSystem getMainSystem() {
        return mainSystem;
    }

    public void setMainSystem(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}

