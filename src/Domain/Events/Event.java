package Domain.Events;

import Domain.MainSystem;
import Domain.LeagueManagment.Match;
import Domain.Users.Referee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Date;

public abstract class Event  {

    private Referee referee;
    private Match match;
    private Date dateTime;
    private static final Logger LOG = LogManager.getLogger("Event");
    private int minuteOfMatch;

    public Event(Referee referee, Match match) throws ParseException {
        this.referee = referee;
        this.match = match;
        Date currentDate = new Date(System.currentTimeMillis());
        this.dateTime = MainSystem.simpleDateFormat.parse(currentDate.toString());
        this.minuteOfMatch = (int)(currentDate.getTime() - match.getStartDate().getTime());
    }

    public Referee getReferee() { return referee; }

    public Match getMatch() { return match; }

    public Date getDateTime() { return dateTime; }

    public int getMinuteOfMatch() { return minuteOfMatch; }
}
