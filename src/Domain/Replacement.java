package Domain;

public class Replacement extends Event {
    Player firstPlayer;
    Player secondPlayer;

    public Replacement(Referee referee, Match match, Player p1, Player p2) throws Exception {
        super(referee, match);

        /**implement replacement**/
        this.firstPlayer = p1;
        this.secondPlayer = p2;
        //p1 is player in away team
        if(match.getAwayTeam().getPlayers().contains(p1)){
            match.getAwayTeam().removePlayer(p1);
            match.getAwayTeam().addPlayer(p2);
        }
        else{
            if(match.getHomeTeam().getPlayers().contains(p1)){
                match.getHomeTeam().removePlayer(p1);
                match.getHomeTeam().addPlayer(p2);
            }
        }
    }

    @Override
    public String toString() {
        return super.getDateTime() +","+super.getMinuteOfMatch() +", replace "+ firstPlayer.getTeamRole().getName()+" by " + secondPlayer.getTeamRole().getName();
    }
}
