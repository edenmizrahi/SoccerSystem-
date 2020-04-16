import javax.print.attribute.standard.DateTimeAtCreation;
import java.text.ParseException;

public class Goal extends Event  {

    private Player player;

    public Goal(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Goal by "+player.getName();
    }
}
