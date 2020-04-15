import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.awt.image.ImageWatched;

import java.util.Date;
import java.util.LinkedList;

public class Referee extends Subscription{

    private LinkedList<Match> matches;
    private LinkedList<Event> events;
    private String qualification;
    private static final Logger LOG = LogManager.getLogger();

    Referee(Subscription sub, MainSystem ms){
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        matches = new LinkedList<>();
        events = new LinkedList<>();
//        notifications = new LinkedList<>();
    }

    Referee(Subscription sub, MainSystem ms, String qualification){
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

    public String getQualification() { return qualification; }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**Yarden**/
    public void addEvent(Match match){

    }

    /**Yarden**/
    public void editEventsSchedule(Match match){
        //just if you are a main referee

    }

    /**Yarden**/
    public void createReport(Match match){
        //just if you are a main referee
    }

    /**Yarden**/
    //just matches that still not take place
    public LinkedList<Match> showMatches(){

        LinkedList<Match> matchesToShow = new LinkedList<>();

        for (Match m: matches) {
            if (m.getDate().after(new Date(System.currentTimeMillis()))){
                //print details about the game
                matchesToShow.add(m);
//                System.out.println("Date:"+m.getDate().toString()+""+"At field:"+m.getField().getNameOfField()+""+"Guest score:"+m.getGuestScore()
//                        +"Home score:"+""+ m.getHomeScore());
            }
        }
        return matchesToShow;
    }

}
