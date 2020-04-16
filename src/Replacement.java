import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class Replacement extends Event {
    Player firstPlayer;
    Player secondPlayer;

    public Replacement(Referee referee, Match match, Player p1, Player p2) throws ParseException {
        super(referee, match);
        // TODO: 16/04/2020 implement replacement
        /**implement replacement**/
        this.firstPlayer = p1;
        this.secondPlayer = p2;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +", replace "+ firstPlayer.getName()+" by " + secondPlayer.getName();
    }
}
