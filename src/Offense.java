import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class Offense extends Event {

    Player player;

    public Offense(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +", offense by "+ player.getName();
    }

}
