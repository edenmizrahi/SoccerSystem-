package Domain;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class SchedualeOption1 implements SchedulingPolicy {

    /**Each pair of teams will play against each other only once in a season**/
    // one match in a day, start at20:00

    //TODO test
    @Override
    public void assign(HashMap<League, HashSet<Team>> teamsInSeason, HashSet<Referee> referees, Referee mainRef) throws Exception {

        Date date = new Date(System.currentTimeMillis());
        Date dateWithFormat = MainSystem.simpleDateFormat.parse(date.toString());
        String dateString = dateWithFormat.toString();
        dateWithFormat = DateUtils.addDays(dateWithFormat,7);

        /**For each Domain.League**/
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
                    dateWithFormat = DateUtils.addDays(dateWithFormat,2);

                }
            }


        }
    }
}
