import java.util.LinkedList;
import java.util.List;

public class Referee extends Subscription{

    LinkedList<Notification> notifications;
    LinkedList<Match> matches;
    LinkedList<Event> events;
    String qualification;



    Referee(Subscription sub, MainSystem ms, String qualification){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        matches = new LinkedList<>();
        events = new LinkedList<>();
        notifications = new LinkedList<>();
        this.qualification = qualification;
        //TODO add permissions
        //this.permissions.add();
    }

    public LinkedList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(LinkedList<Notification> notifications) {
        this.notifications = notifications;
    }

    public LinkedList<Match> getMatches() {
        return matches;
    }

    public void setMatches(LinkedList<Match> matches) {
        this.matches = matches;
    }

    public LinkedList<Event> getEvents() {
        return events;
    }

    public void setEvents(LinkedList<Event> events) {
        this.events = events;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void addEvent(){}


}
