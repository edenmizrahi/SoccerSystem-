import java.util.HashMap;
import java.util.HashSet;

public class CalculateOption1 implements CalculationPolicy {

    //win- +2
    //lose- 0
    //tiko- +1,+1
    @Override
    public void calculate(HashMap<League, HashSet<Team >> teamsInSeason) {

            /**For each League**/
            for(League l: teamsInSeason.keySet()){

                HashSet<Team> teamsPerLeague = teamsInSeason.get(l);
                /**For each team in league**/
                for (Team currentHomeTeam: teamsPerLeague) {
                    HashSet<Match> homeMatches = currentHomeTeam.getHome();
                    for (Match m: homeMatches) {
                        Team away = m.getAwayTeam();
                        //check if currentTeam is home team
                        if(m.getHomeTeam().equals(currentHomeTeam)){
                            //home team won
                            if(m.getHomeScore() > m.getGuestScore()){
                                currentHomeTeam.setScore(currentHomeTeam.getScore()+2);
                            }
                            else{
                                //away team won
                                if(m.getHomeScore() < m.getGuestScore()){
                                    away.setScore(away.getScore()+2);
                                }
                                else{
                                    //Tiko
                                    if(m.getHomeScore() == m.getGuestScore()){
                                        currentHomeTeam.setScore(currentHomeTeam.getScore()+1);
                                        away.setScore(away.getScore()+1);
                                    }
                                }
                            }
                        }
                    }//match
                }// currentHomeTeam
            }//league

        }


}
