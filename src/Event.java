import javax.print.attribute.standard.DateTimeAtCreation;

public abstract class Event  {

    private Referee referee;
    private Match match;

    private DateTimeAtCreation dateTime;// i dont know how to do timestamp
    /****Who keeps and maintains an events calender ?****/

    public Event(Referee referee, Match match, DateTimeAtCreation dateTime) {
        this.referee = referee;
        this.match = match;
        this.dateTime = dateTime;
    }

    public Referee getReferee() { return referee; }

    public Match getMatch() { return match; }

    public DateTimeAtCreation getDateTime() { return dateTime; }

    public void setReferee(Referee referee) { this.referee = referee; }

    public void setMatch(Match match) { this.match = match; }
}
