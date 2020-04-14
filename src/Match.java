import javafx.beans.InvalidationListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.util.Date;
import java.util.HashSet;
import java.util.Observable;

public class Match extends Observable{
    private int homeScore;
    private int guestScore;
    private Team awayTeam;
    private Team homeTeam;
    private Field field;
    private HashSet<Event> events;
    private Referee mainReferee;
    private HashSet<Referee> referees;
    private Date date;
    private static final Logger LOG = LogManager.getLogger();


    public Match(int homeScore, int guestScore, Team awayTeam, Team homeTeam, Field field, HashSet<Event> events, HashSet<Referee> referees
    , Referee mainReferee, Date date) {
        this.homeScore = homeScore;
        this.guestScore = guestScore;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.field = field;
        this.events = events;
        this.referees = referees;
        this.mainReferee = mainReferee;
        this.date = date;
    }


    public Referee getMainReferee() {
        return mainReferee;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
    }

    public int getHomeScore() { return homeScore; }

    public int getGuestScore() { return guestScore; }


    public Date getDate() { return date; }

    public Team getAwayTeam() { return awayTeam; }

    public Team getHomeTeam() { return homeTeam; }

    public Field getField() { return field; }

    public HashSet<Event> getEvents() { return events; }

    public HashSet<Referee> getReferees() { return referees; }

    /**** not supposed to be at referee ? just him legal to add event to his match ****/
    /**** hat about the player that did the event ? ****/
    /**** minute of game ****/
    /**** date - timeStamp in event ? ****/
    public void addEvent(Event e){

    }


}
