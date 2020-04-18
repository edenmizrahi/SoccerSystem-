import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SchedualeOption2 implements SchedulingPolicy {

    /**Each pair of teams will play twice in the season, each time in one of the teams' home field**/
    @Override
    public void assign(HashMap<League, HashSet<Team>> teamsInSeason, HashSet<Referee> referees, Referee mainRef) throws Exception {

        Date date = new Date(System.currentTimeMillis());
        Date dateWithFormat = MainSystem.simpleDateFormat.parse(date.toString());
        String dateString = dateWithFormat.toString();
        dateWithFormat = DateUtils.addDays(dateWithFormat,7);
        /**For each League**/
        for (League l : teamsInSeason.keySet()) {

            HashSet<Team> teamsPerLeague = teamsInSeason.get(l);
            /**First loop - For each team in league create match at her home field**/
            for (Team currentHomeTeam : teamsPerLeague) {
                /**Second loop - For each team in league create match in away team**/
                for (Team currentAwayTeam : teamsPerLeague) {
                    //check if not the same. match is between 2 different teams
                    if(!currentAwayTeam.equals(currentAwayTeam)){
                        //create new match
                        Match match = new Match(0,0,currentAwayTeam, currentHomeTeam, currentHomeTeam.getField()
                        ,new HashSet<Event>(),referees,mainRef,dateString);
                        //insert the match to teams list
                        currentHomeTeam.addMatchToHomeMatches(match);
                        currentAwayTeam.addMatchToAwayMatches(match);
                    }
//                    dateWithFormat = MainSystem.simpleDateFormat.parse(dateString);
                    //update the date 2 days after, at the same hour
                    dateWithFormat = DateUtils.addDays(dateWithFormat,2);
                }

            }
        }

    }
}
