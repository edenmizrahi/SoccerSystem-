package Domain.Events;

import Domain.LeagueManagment.Match;
import Domain.Users.Referee;

import java.text.ParseException;

public class ExtraTime extends Event {

    private int extraMinute;

    public ExtraTime(Referee referee, Match match, int minutesToAdd) throws Exception {
        super(referee, match);
        if (minutesToAdd > 0) {
            this.extraMinute = minutesToAdd;
            match.setNumOfMinutes(match.getNumOfMinutes()+minutesToAdd);
        }
        else{
            throw new Exception("You can not give a negative extra time");
        }

    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"extra time of "+extraMinute+" minutes";
    }
}
