import java.util.LinkedList;
import java.util.List;

public class Referee extends Subscription{

    LinkedList<Notification> notifications;
    LinkedList<Match> matches;
    LinkedList<Event> events;


    Referee(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        matches = new LinkedList<>();
        events = new LinkedList<>();
        notifications = new LinkedList<>();
        //TODO add permissions
        //this.permissions.add();
    }


    public void addEvent(){}


}
