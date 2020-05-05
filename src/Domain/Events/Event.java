package Domain.Events;

import Domain.MainSystem;
import Domain.LeagueManagment.Match;
import Domain.Users.Referee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Event  {

    private Referee referee;
    private Match match;
    private Date dateTime;
    private static final Logger LOG = LogManager.getLogger();
    private int minuteOfMatch;

    public Event(Referee referee, Match match) throws Exception {
        if(referee != null && match != null) {
            this.referee = referee;
            this.match = match;
            Date currentDate = new Date(System.currentTimeMillis());
            this.dateTime = MainSystem.simpleDateFormat.parse(currentDate.toString());
            long diff = currentDate.getTime() - match.getStartDate().getTime();
            this.minuteOfMatch = (int) TimeUnit.MINUTES.convert(diff,TimeUnit.MILLISECONDS);
//            this.minuteOfMatch = (int) (currentDate.getTime() - match.getStartDate().getTime());
            if(minuteOfMatch > match.getNumOfMinutes() || minuteOfMatch < 0){
                throw new Exception("invalid event creation");
            }
        }
        else{
            throw new NullPointerException();
        }
    }

    public Referee getReferee() { return referee; }

    public Match getMatch() { return match; }

    public Date getDateTime() { return dateTime; }

    public int getMinuteOfMatch() { return minuteOfMatch; }
}
