package Domain.Events;

import Domain.LeagueManagment.Match;
import Domain.Users.Player;
import Domain.Users.Referee;

import java.text.ParseException;

public class YellowCard extends Event {

    private Player player;
    public YellowCard(Referee referee, Match match, Player p) throws Exception {
        super(referee, match);
        if(p != null){
            this.player = p;
        }
        else{
            throw new Exception("Please insert valid player");
        }
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Yellow card to "+player.getTeamRole().getName();
    }
}
