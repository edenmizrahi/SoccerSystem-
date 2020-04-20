package Domain;

import java.text.ParseException;

public class YellowCard extends Event {

    private  Player player;
    public YellowCard(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Yellow card to "+player.getTeamRole().getName();
    }
}
