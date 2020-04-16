import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class RedCard extends Event {

    private Player player;

    public RedCard(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Red card to "+ player.getName();
    }
}
