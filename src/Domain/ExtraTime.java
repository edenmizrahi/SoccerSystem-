package Domain;

import java.text.ParseException;

public class ExtraTime extends Event {

    private int extraMinute;

    public ExtraTime(Referee referee, Match match, int minutesToAdd) throws ParseException {

        super(referee, match);
        this.extraMinute = minutesToAdd;
        match.setNumOfMinutes(match.getNumOfMinutes()+minutesToAdd);
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"extra time of "+extraMinute+" minutes";
    }
}
