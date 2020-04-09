import javax.print.attribute.standard.DateTimeAtCreation;

public class Goal extends Event  {
    /****Who keeps and maintains an events calender ?
     * @param referee
     * @param match
     * @param dateTime****/
    public Goal(Referee referee, Match match, DateTimeAtCreation dateTime) {
        super(referee, match, dateTime);
    }
}
