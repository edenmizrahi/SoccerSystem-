package Domain;

import java.text.ParseException;

public class Goal extends Event  {

    private Player player;

    public Goal(Referee referee, Match match, Player p) throws ParseException {
        super(referee, match);
        this.player = p;
        /**add score to the relevant team**/
        //player at home team
        if(match.getHomeTeam().getPlayers().contains(p)){
            match.setHomeScore(match.getHomeScore()+2);
        }
        else{
            //player at away team
            if(match.getAwayTeam().getPlayers().contains(p)){
                match.setGuestScore(match.getGuestScore()+2);
            }
        }
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +","+"Domain.Goal by "+player.getTeamRole().getName();
    }
}
