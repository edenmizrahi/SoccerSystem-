package Domain.LeagueManagment.Scheduling;

import Domain.Events.Event;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.Referee;
import org.apache.commons.lang3.time.DateUtils;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SchedualeOption2 implements SchedulingPolicy {

    //TODO test
    /**
     * This function gets hash of each league and all the teams inside her, hash of referees and main referee
     * and create matches for teams according to this policy -
     * Each pair of teams will play twice in the season, each time in one of the teams' home field.
     * start date of match - a week from today at 8PM and all the other matches after the first create
     * will be two-day differentials.
     * the referees refereeing at all the matches in the season
     * @param teamsInSeason
     * @param referees
     * @param mainRef
     * @throws Exception
     */
    @Override
    public void assign(HashMap<League, HashSet<Team>> teamsInSeason, HashSet<Referee> referees, Referee mainRef) throws Exception {

        Date date = new Date();
        Date afterAweek = DateUtils.addDays(date,7);
        String dateString = MainSystem.simpleDateFormat.format(afterAweek);


        /**For each League**/
        for (League l : teamsInSeason.keySet()) {

            HashSet<Team> teamsPerLeague = teamsInSeason.get(l);
            /**First loop - For each team in league create match at her home field**/
            for (Team currentHomeTeam : teamsPerLeague) {
                /**Second loop - For each team in league create match in away team**/
                for (Team currentAwayTeam : teamsPerLeague) {
                    //check if not the same. match is between 2 different teams
                    if(!currentHomeTeam.equals(currentAwayTeam)){
                        //create new match
                        Match match = new Match(0,0,currentAwayTeam, currentHomeTeam, currentHomeTeam.getField()
                        ,new HashSet<Event>(),referees,mainRef,dateString);
                        //insert the match to teams list
                        currentHomeTeam.addMatchToHomeMatches(match);
                        currentAwayTeam.addMatchToAwayMatches(match);

                        //update the date 2 days after, at the same hour
//                        afterAweek = dateTime.plusDays(2);
//                        dateString = MainSystem.simpleDateFormat.format(afterAweek);
                        afterAweek = DateUtils.addDays(afterAweek,2);
                    }

                }

            }
        }

    }
}
