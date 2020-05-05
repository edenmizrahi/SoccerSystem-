package Domain.Events;

import Domain.LeagueManagment.Match;
import Domain.Users.Player;
import Domain.Users.Referee;

import java.text.ParseException;

public class Offense extends Event {

    Player player;

    public Offense(Referee referee, Match match, Player p) throws Exception {
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
        return super.getDateTime() +","+super.getMinuteOfMatch() +", offense by "+ player.getTeamRole().getName();
    }

}
