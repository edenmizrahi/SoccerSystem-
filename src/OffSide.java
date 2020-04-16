import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class OffSide extends Event {

    private Player player;

    public OffSide(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +", offSide by "+ player.getName();
    }
}
