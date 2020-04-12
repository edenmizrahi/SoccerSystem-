import java.util.*;

public class SystemManager extends Subscription implements Observer {

    private LinkedList<Notification> notifications;//*
    private HashSet<Complaint> complaints;//*

    public SystemManager(Subscription sub, MainSystem ms) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        this.notifications = new LinkedList<>();
        this.complaints = new HashSet<>();
        //TODO add permissions
        //this.permissions.add();
    }

    public void buildRecomandationSystem(){

    }

    //<editor-fold desc="getters and setters">
    public List<Notification> getNotifications() {
        return notifications;
    }

    public HashSet<Complaint> getComplaints() {
        return complaints;
    }


    /**Eden*/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  Complaint){

            if(arg instanceof Complaint){
                /**if it's not an answer notify*/
                if(((Complaint)arg).getAnswer()==null){
                    // TODO: 11/04/2020 Notifies about new Complaint
                }

            }
        }
    }
    /**Eden*/
    public void answerToComplaint (Complaint com, String ans){
        if(complaints.contains(com)){
            com.setAnswer(ans);
            com.send();
        }
    }
    /**Eden*/
    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        complaint.addObserver(this);
    }


    //</editor-fold>
}

