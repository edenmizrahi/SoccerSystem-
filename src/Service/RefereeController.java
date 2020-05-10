package Service;

import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Season;
import Domain.MainSystem;
import Domain.Users.Referee;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Ref;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RefereeController {
    // TODO: 21/04/2020 next iteration



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

    //return Match from String

}
