package Service;

import Domain.Controllers.RefereeController;
import Domain.Users.Player;

import java.text.ParseException;
import java.util.HashSet;
import java.util.LinkedList;

public class RefereeApplication {
    RefereeController refereeController = new RefereeController();


    //get list of players name
    //TODO???
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
    public LinkedList<String> displayPlayersAtMatch(String nameOfReferee, String match) {
        return refereeController.displayPlayersAtMatch(nameOfReferee, match);
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


    public LinkedList<String> createReportOfMatch(String match, String nameOfReferee) {
        return refereeController.createReportOfMatch(match, nameOfReferee);
    }


    public String createGoalEvent(String referee, String match, String player) {
        return refereeController.createGoalEvent(referee, match, player);
    }

    public String createInjuryEvent(String referee, String match, String player) {
        return refereeController.createInjuryEvent(referee, match, player);
    }

    public String createOffenseEvent(String referee, String match, String player) {
        return refereeController.createOffenseEvent(referee, match, player);
    }

    public String createOffSideEvent(String referee, String match, String player) {
        return refereeController.createOffSideEvent(referee, match, player);
    }

    public String createRedCardEvent(String referee, String match, String player) {
        return refereeController.createRedCardEvent(referee, match, player);
    }

    public String createYellowCardEvent(String referee, String match, String player) {
        return refereeController.createYellowCardEvent(referee, match, player);

    }

    public String createReplaceEvent(String referee, String match, String firstPlayer, String secondPlayer){
        return refereeController.createReplaceEvent(referee, match, firstPlayer, secondPlayer);
    }

    public String createExtraTimeEvent(String referee, String match, String time) {
        return refereeController.createExtraTimeEvent(referee, match, time);
    }



}
