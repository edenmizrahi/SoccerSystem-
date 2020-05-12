package Domain.Events;

import Domain.MainSystem;
import Domain.LeagueManagment.Match;
import Domain.Users.Referee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class Event  {

    private Referee referee;
    private Match match;
    private Date dateTime;
    protected static final Logger LOG = LogManager.getLogger("Event");
    private int minuteOfMatch;
    private String name;
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public Event(Referee referee, Match match) throws Exception {
        if(referee != null && match != null) {
            this.referee = referee;
            this.match = match;
//            this.name="";
            Date currentDate = new Date(System.currentTimeMillis());
            this.dateTime = currentDate;
            long diff = currentDate.getTime() - match.getStartDate().getTime();
            this.minuteOfMatch = (int) TimeUnit.MINUTES.convert(diff,TimeUnit.MILLISECONDS);

//            if(minuteOfMatch > match.getNumOfMinutes() || minuteOfMatch < 0){
//                LOG.error("invalid event creation");
//                throw new Exception("invalid event creation");
//            }
        }
        else{
            LOG.error("one of parameters null");
            throw new NullPointerException();
        }
    }

    public Referee getReferee() { return referee; }

    public Match getMatch() { return match; }

    public Date getDateTime() { return dateTime; }

    public int getMinuteOfMatch() { return minuteOfMatch; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
