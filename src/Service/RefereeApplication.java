package Service;

import Domain.Controllers.RefereeController;
import Domain.LeagueManagment.Match;
import Domain.Users.Player;
import Domain.Users.Referee;
import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedList;

public class RefereeApplication {
    RefereeController refereeController = new RefereeController();

    public Referee getRefereeByUserName(String refName){
        return refereeController.getRefereeByUserName(refName);
    }

    //get list of players name
    public LinkedList<String> getPlayersFromTeamInList(HashSet<Player> players){
        return refereeController.getPlayersFromTeamInList(players);
    }

    /**
     *
     * @param nameOfReferee
     * @param match
     * @return LinkedList<String> of players at specific match
     * @throws ParseException
     */
    public LinkedList<String> displayPlayersAtMatch(String nameOfReferee, String match) throws ParseException {
        return refereeController.displayPlayersAtMatch(nameOfReferee, match);
    }

    //check if 2 players at the same team
    public boolean checkIfAtTheSameTeam(String firstPlayer, String secondPlayer, String nameOfReferee, String match) throws ParseException {
        return refereeController.checkIfAtTheSameTeam(firstPlayer, secondPlayer, nameOfReferee, match);
    }

    /**
     *
     * @param nameOfReferee
     * @return match that the referee judging that take place right now - probably just one
     */
    public String displayAllMatches(String nameOfReferee){
        return refereeController.displayAllMatches(nameOfReferee);
    }

    /**
     * This function return all the matches that the referee is a main referee, and already over
     * @param nameOfReferee
     * @return
     */
    public LinkedList<String> getAllMatches(String nameOfReferee) {
        return refereeController.getAllMatches(nameOfReferee);
    }


    public LinkedList<String> createReportOfMatch(String match, String nameOfReferee) throws Exception {
        return refereeController.createReportOfMatch(match, nameOfReferee);
    }


    public void createGoalEvent(String referee, String match, String player) throws Exception {
        refereeController.createGoalEvent(referee, match, player);
    }

    public void createInjuryEvent(String referee, String match, String player) throws Exception {
        refereeController.createInjuryEvent(referee, match, player);
    }

    public void createOffenseEvent(String referee, String match, String player) throws Exception {
        refereeController.createOffenseEvent(referee, match, player);
    }

    public void createOffSideEvent(String referee, String match, String player) throws Exception {
       refereeController.createOffSideEvent(referee, match, player);
    }

    public void createRedCardEvent(String referee, String match, String player) throws Exception {
        refereeController.createRedCardEvent(referee, match, player);
    }

    public void createYellowCardEvent(String referee, String match, String player) throws Exception {
        refereeController.createYellowCardEvent(referee, match, player);

    }

    public void createReplaceEvent(String referee, String match, String firstPlayer, String secondPlayer) throws Exception {
        refereeController.createReplaceEvent(referee, match, firstPlayer, secondPlayer);
    }

    public void createExtraTimeEvent(String referee, String match, String time) throws Exception {
        refereeController.createExtraTimeEvent(referee, match, time);
    }



}
