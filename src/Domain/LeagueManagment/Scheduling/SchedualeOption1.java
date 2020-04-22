package Domain.LeagueManagment.Scheduling;

import Domain.Events.Event;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Scheduling.SchedulingPolicy;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.Referee;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SchedualeOption1 implements SchedulingPolicy {


    //TODO test
    /**
     * This function gets hash of each league and all the teams inside her, hash of referees and main referee
     * and create matches for teams according to this policy -
     * Each pair of teams will play against each other only once in a season.
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

        Date date = new Date(System.currentTimeMillis());
        Date afterAweek = DateUtils.addDays(date,7);
        String dateString = MainSystem.simpleDateFormat.format(afterAweek);

        /**For each League**/
        for (League l : teamsInSeason.keySet()) {

            HashSet<Team> teamsPerLeague = teamsInSeason.get(l);
            /**For each team in league**/
            ArrayList<Team> arrayList = new ArrayList<>();
            for (Team currentTeam : teamsPerLeague) {
                arrayList.add(currentTeam);
            }

            for(int i=0;i<arrayList.size();i++){

                Team currentHomeTeam = arrayList.get(i);

                for(int j=i+1;j<arrayList.size();j++){
                    Team currentAwayTeam = arrayList.get(j);
                    //create new match
                    Match match = new Match(0,0,currentAwayTeam, currentHomeTeam, currentHomeTeam.getField()
                            ,new HashSet<Event>(),referees,mainRef,dateString);
                    //insert the match to teams list
                    currentHomeTeam.addMatchToHomeMatches(match);
                    currentAwayTeam.addMatchToAwayMatches(match);

                    //update the date 2 days after, at the same hour
                    afterAweek = DateUtils.addDays(afterAweek,2);

                }
            }


        }
    }
}
