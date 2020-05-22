package Domain.Controllers;

import Domain.Events.Event;
import Domain.LeagueManagment.Match;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Users.Player;
import Domain.Users.Referee;
import javafx.scene.control.Alert;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class RefereeController {
    // TODO: 21/04/2020 next iteration

    SystemOperationsController systemOperationsController = new SystemOperationsController();


    public Referee getRefereeByUserName(String refName){
        List<Referee> allReferees = MainSystem.getInstance().getAllReferees();

        for (Referee ref: allReferees) {
            if(ref.getUserName().contains(refName)){
                return ref;
            }
        }
        return null;
    }

    //get list of players name
    public LinkedList<String> getPlayersFromTeamInList(HashSet<Player> players){

        LinkedList<String> listOfPlayers = new LinkedList<>();
        for (Player player: players) {
            listOfPlayers.add(player.getTeamRole().getUserName());
        }

        return listOfPlayers;
    }

    /**
     *
     * @param nameOfReferee
     * @param match
     * @return LinkedList<String> of players at specific match
     * @throws ParseException
     */
    public String displayPlayersAtMatch(String nameOfReferee, String match){
        try {
            Match m = this.matchObjectFromString(match, nameOfReferee);

            Team away = m.getAwayTeam();
            Team home = m.getHomeTeam();

            HashSet<Player> awayPlayers = away.getPlayers();
            LinkedList<String> awayPlayersName = this.getPlayersFromTeamInList(awayPlayers);

            HashSet<Player> homePlayers = home.getPlayers();
            LinkedList<String> homePlayersName = this.getPlayersFromTeamInList(homePlayers);

            //LinkedList<String> allPlayers = awayPlayersName;
            String allPlayers = new String();
            for(String pName : homePlayersName){
                allPlayers += pName + ",";
            }
            //allPlayers.addAll(homePlayersName);
            return allPlayers;
        }
        catch (ParseException e){
            //return "";
        }
        return null;
    }

    //check if 2 players at the same team
    public boolean checkIfAtTheSameTeam(String firstPlayer, String secondPlayer, String nameOfReferee, String match) throws ParseException {

        if(!firstPlayer.equals(secondPlayer)) {

            Match m = this.matchObjectFromString(match, nameOfReferee);

            Team away = m.getAwayTeam();
            HashSet<Player> awayPlayers = away.getPlayers();
            LinkedList<String> awayPlayersName = this.getPlayersFromTeamInList(awayPlayers);

            Team home = m.getHomeTeam();
            HashSet<Player> homePlayers = home.getPlayers();
            LinkedList<String> homePlayersName = this.getPlayersFromTeamInList(homePlayers);

            if (awayPlayersName.contains(firstPlayer)) {
                if (awayPlayersName.contains(secondPlayer)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (homePlayersName.contains(firstPlayer)) {
                    if (homePlayersName.contains(secondPlayer)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param nameOfReferee
     * @return match that the referee judging that take place right now - probably just one
     */
    public String displayAllMatches(String nameOfReferee){

        //LinkedList<String> listOfMatches = new LinkedList<>();
        Referee currentReferee = this.getRefereeByUserName(nameOfReferee);
        Date currentDate = new Date(System.currentTimeMillis());
        for (Match match: currentReferee.getMatches()) {
            //check if the game is takes place right now
            if(currentDate.after(match.getStartDate()) && currentDate.before(DateUtils.addMinutes(match.getStartDate(),match.getNumOfMinutes()))) {
                //listOfMatches.add(match.toString());
                return match.toString();
            }
        }
        return null;
        //return listOfMatches;
    }

    /**
     * This function return all the matches that the referee is a main referee, and already over
     * @param nameOfReferee
     * @return
     */
    public String getAllMatches(String nameOfReferee) {

        //LinkedList<String> listOfMatches = new LinkedList<>();
        String listOfMatches = new String();
        Referee currentReferee = this.getRefereeByUserName(nameOfReferee);
        Date currentDate = new Date(System.currentTimeMillis());

        for (Match match: currentReferee.getMatches()) {
            if(match.getMainReferee().getUserName().equals(nameOfReferee)) {
                if (currentDate.after(DateUtils.addMinutes(match.getStartDate(), match.getNumOfMinutes()))) {
                    //listOfMatches.add(match.toString());
                    listOfMatches += match.toString() + ",";
                }
            }
        }

        return listOfMatches;
    }


    public String createReportOfMatch(String match, String nameOfReferee) {
        try {
            //LinkedList<String> listOfEventsInMatch = new LinkedList<>();
            String listOfEventsInMatch = new String();
            Referee currentReferee = this.getRefereeByUserName(nameOfReferee);
            Match m = this.matchObjectFromString(match, nameOfReferee);

            HashSet<Event> allEvents = currentReferee.createReport(m);
            for (Event e : allEvents) {
                //listOfEventsInMatch.add(e.toString());
                listOfEventsInMatch += e.toString() + ",";
            }
            return listOfEventsInMatch;
        } catch(Exception e){
            return "";
        }
        //return null;
    }

    /**
     * This function convert string of match to Match object
     * @param match
     * @param nameOfReferee
     * @return Match object
     * @throws ParseException
     */
    public Match matchObjectFromString(String match, String nameOfReferee) throws ParseException {

        String[] arrayOfTeamsAndDate = match.split(",");
        String date = arrayOfTeamsAndDate[1];

        String TeamsString = arrayOfTeamsAndDate[0];
        String[] arrayOfTeams = TeamsString.split("-");

        Team homeTeam = this.systemOperationsController.getTeambyTeamName(arrayOfTeams[0]);

        Team awayTeam = this.systemOperationsController.getTeambyTeamName(arrayOfTeams[1]);


        Date matchDate = MainSystem.simpleDateFormat.parse(date);

        Referee ref = this.getRefereeByUserName(nameOfReferee);

        for (Match m: ref.getMatches()) {
            if(m.getAwayTeam().equals(awayTeam) && m.getHomeTeam().equals(homeTeam) && m.getStartDate().equals(matchDate)){
                return m;
            }
        }

        return null;
    }


    public String createGoalEvent(String referee, String match, String player) {
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
            Event e = currentReferee.createGoalEvent(playerMakeEvent, currentMatch);
            currentReferee.addEventsDuringMatch(currentMatch, e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createInjuryEvent(String referee, String match, String player)  {
        try{
        Referee currentReferee = this.getRefereeByUserName(referee);
        Match currentMatch = this.matchObjectFromString(match, referee);
        Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
        Event e = currentReferee.createInjuryEvent(playerMakeEvent,currentMatch);
        currentReferee.addEventsDuringMatch(currentMatch,e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createOffenseEvent(String referee, String match, String player){
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
            Event e = currentReferee.createOffenseEvent(playerMakeEvent, currentMatch);
            currentReferee.addEventsDuringMatch(currentMatch, e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createOffSideEvent(String referee, String match, String player) {
        try{
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
            Event e = currentReferee.createOffSideCardEvent(playerMakeEvent,currentMatch);
            currentReferee.addEventsDuringMatch(currentMatch,e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createRedCardEvent(String referee, String match, String player) {
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
            Event e = currentReferee.createRedCardEvent(playerMakeEvent, currentMatch);
            currentReferee.addEventsDuringMatch(currentMatch, e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createYellowCardEvent(String referee, String match, String player) {
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player playerMakeEvent = this.systemOperationsController.getPlayerByUserName(player);
            Event e = currentReferee.createYellowCardEvent(playerMakeEvent, currentMatch);
            currentReferee.addEventsDuringMatch(currentMatch, e);
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createReplaceEvent(String referee, String match, String firstPlayer, String secondPlayer) {
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            Player player1 = this.systemOperationsController.getPlayerByUserName(firstPlayer);
            Player player2 = this.systemOperationsController.getPlayerByUserName(secondPlayer);
            //choose that player1 and player2 different and exist in the same team
            if (checkIfAtTheSameTeam(firstPlayer, secondPlayer, referee, match)) {
                Event e = currentReferee.createReplacementEvent(player1, player2, currentMatch);
                currentReferee.addEventsDuringMatch(currentMatch, e);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Notice that the players must be from the same team");
                alert.show();
            }
        } catch(Exception e){
            return "";
        }
        return "";
    }

    public String createExtraTimeEvent(String referee, String match, String time) {
        try {
            Referee currentReferee = this.getRefereeByUserName(referee);
            Match currentMatch = this.matchObjectFromString(match, referee);
            int minutes = Integer.parseInt(time);
            Event e = currentReferee.createExtraTimeEvent(currentMatch, minutes);
            currentReferee.addEventsDuringMatch(currentMatch, e);
        } catch(Exception e){
            return "";
        }
        return "";
    }



}
