import javax.print.attribute.standard.DateTimeAtCreation;

public class Offense extends Event {
    /****Who keeps and maintains an events calender ?
     * @param referee
     * @param match
     * @param dateTime****/
    public Offense(Referee referee, Match match, DateTimeAtCreation dateTime) {
        super(referee, match, dateTime);
    }
}
