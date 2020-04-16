import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class Injury extends Event {

    Player player;

    public Injury(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +", "+ player.getName()+" is injured";
    }
}
