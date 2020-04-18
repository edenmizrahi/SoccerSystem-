import java.util.HashMap;
import java.util.HashSet;

public class SchedualeOption1 implements SchedulingPolicy {

    /**Each pair of teams will play against each other only once in a season**/
    // one match in a day, start at20:00
    @Override
    public void assign(HashMap<League, HashSet<Team>> teamsInSeason, HashSet<Referee> referees, Referee mainRef) {

        /**For each League**/
        for (League l : teamsInSeason.keySet()) {

            HashSet<Team> teamsPerLeague = teamsInSeason.get(l);
            /**For each team in league**/
            for (Team currentTeam : teamsPerLeague) {

            }
        }
    }
}
