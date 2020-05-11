package Service;

import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.Referee;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Ref;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RefereeController {
    // TODO: 21/04/2020 next iteration

    SystemOperationsController systemOperationsController = new SystemOperationsController();


    public Referee getRefereeByName(String refName){
        List<Referee> allReferees = MainSystem.getInstance().getAllReferees();

        for (Referee ref: allReferees) {
            if(ref.getUserName().contains(refName)){
                return ref;
            }
        }
        return null;
    }


    //display matche that take place right now
    public LinkedList<String> displayAllMatches(String nameOfReferee){

        LinkedList<String> listOfMatches = new LinkedList<>();

        Referee currentReferee = this.getRefereeByName(nameOfReferee);
        Date currentDate = new Date(System.currentTimeMillis());
        for (Match match: currentReferee.getMatches()) {
                //check if the game is takes place right now
                if(currentDate.after(match.getStartDate()) && currentDate.before(DateUtils.addMinutes(match.getStartDate(),match.getNumOfMinutes()))) {
                    listOfMatches.add(match.toString());
                }
        }
        return listOfMatches;
    }

    //return Match object from String
    public Match matchObjectFromString(String match, String nameOfReferee) throws ParseException {

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy 20:00:00");

        String[] arrayOfTeamsAndDate = match.split("-");
        Team homeTeam = this.systemOperationsController.getTeambyTeamName(arrayOfTeamsAndDate[0]);

        String[] arrayOfAwayTeamAndDate = arrayOfTeamsAndDate[1].split(",");
        Team awayTeam = this.systemOperationsController.getTeambyTeamName(arrayOfAwayTeamAndDate[0]);

        String date = arrayOfAwayTeamAndDate[1];
        Date matchDate = dt.parse(date);

        Referee ref = this.getRefereeByName(nameOfReferee);

        for (Match m: ref.getMatches()) {
            if(m.getAwayTeam().equals(awayTeam) && m.getHomeTeam().equals(homeTeam) && m.getStartDate().equals(matchDate)){
                return m;
            }
        }

        return null;
    }

}
