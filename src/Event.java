import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

public abstract class Event  {

    private Referee referee;
    private Match match;
    private Date dateTime;
    private static final Logger LOG = LogManager.getLogger();
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
