import java.util.LinkedList;

public class Referee extends Subscription{

    private LinkedList<Notification> notifications;
    private LinkedList<Match> matches;
    private LinkedList<Event> events;


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
