import java.util.HashSet;

public class Match {

    private int homeScore;
    private int guestScore;
    private Match match;
    private Team awayTeam;
    private Team homeTeam;
    private Field field;
    private HashSet<Event> events;
    private HashSet<Referee> referees;


    public Match(int homeScore, int guestScore, Match match, Team awayTeam, Team homeTeam, Field field, HashSet<Event> events, HashSet<Referee> referees) {
        this.homeScore = homeScore;
        this.guestScore = guestScore;
        this.match = match;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.field = field;
        this.events = events;
        this.referees = referees;
    }

    public int getHomeScore() { return homeScore; }

    public int getGuestScore() { return guestScore; }

    public Match getMatch() { return match; }

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
