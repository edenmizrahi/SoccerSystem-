import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Referee extends Subscription{

    private LinkedList<Match> matches;
    private LinkedList<Event> events;
    private String qualification;
    private static final Logger LOG = LogManager.getLogger();


    public Referee(Subscription sub, MainSystem ms, String qualification){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        matches = new LinkedList<>();
        events = new LinkedList<>();
        this.qualification = qualification;
        //TODO add permissions
        //this.permissions.add();
    }

    public Referee (MainSystem ms, String name, String phoneNumber, String email, String userName, String password, String qualification) {
        super(ms,name,phoneNumber,email,userName,password);
        matches = new LinkedList<>();
        events = new LinkedList<>();
        this.qualification=qualification;
        //TODO add permissions
        //this.permissions.add();
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
