package Domain.Controllers;

import DataAccess.*;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.League;
import Domain.LeagueManagment.Season;
import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SystemManagerController {

    DaoApprovedTeamReq daoApprovedTeamReq = new DaoApprovedTeamReq();
    DaoCalculationPolicy daoCalculationPolicy = new DaoCalculationPolicy();
    DaoCoaches daoCoaches = new DaoCoaches();
    DaoEvent daoEvent = new DaoEvent();
    DaoExtraTimeEvent daoExtraTimeEvent = new DaoExtraTimeEvent();
    DaoFanMatchesFollow daoFanMatchesFollow = new DaoFanMatchesFollow();
    DaoFans daoFans = new DaoFans();
    DaoFields daoFields = new DaoFields();
    DaoLeagues daoLeagues = new DaoLeagues();
    DaoLeagueSeasonReferees daoLeagueSeasonReferees = new DaoLeagueSeasonReferees();
    DaoLeagueSeasonTeams daoLeagueSeasonTeams = new DaoLeagueSeasonTeams();
    DaoMatch daoMatch = new DaoMatch();
    DaoNotificaionsReferee daoNotificaionsReferee = new DaoNotificaionsReferee();
    DaoNotificationFan daoNotificationFan = new DaoNotificationFan();
    DaoNotificationsRfa daoNotificationsRfa = new DaoNotificationsRfa();
    DaoOnePlayerEvents daoOnePlayerEvents = new DaoOnePlayerEvents();
    DaoPlayer daoPlayer = new DaoPlayer();
    DaoReferee daoReferee = new DaoReferee();
    DaoRefereesMatchs daoRefereesMatchs = new DaoRefereesMatchs();
    DaoRfa daoRfa = new DaoRfa();
    DaoSchedulingPolicy daoSchedulingPolicy = new DaoSchedulingPolicy();
    DaoSeasons daoSeasons = new DaoSeasons();
    DaoTeamOwnersTeams daoTeamOwnersTeams = new DaoTeamOwnersTeams();
    DaoTeamRequests daoTeamRequests = new DaoTeamRequests();
    DaoTeamRole daoTeamRole = new DaoTeamRole();
    DaoTeams daoTeams = new DaoTeams();
    DaoTwoPlayersEvents daoTwoPlayersEvents = new DaoTwoPlayersEvents();

    /**
     * close team  forever
     *
     * @param teamToRemove
     * @param user
     * @throws Exception
     * @codeBy Eden
     */
    public void closeTeamForever(Team teamToRemove, SystemManager user) throws Exception {
        user.removeTeamFromSystem(teamToRemove);
    }

    /**
     * Delete user from System
     * @param userToDelete user to delete
     * @param user
     * @throws Exception if delete is not valid.
     * @codeBy Eden
     */
    public void deleteUserForever(Fan userToDelete, SystemManager user) throws Exception {
        user.removeUser(userToDelete);
    }


    /**
     * replace team's founder .
     * @param user
     * @param toAdd
     * @param toRemove
     * @param fromTeam
     * @throws Exception if team is not in the team owner's teams list.
     *@codeBy Eden
     */
    public void replaceTeamFounder(SystemManager user, TeamOwner toAdd, TeamOwner toRemove , Team fromTeam) throws Exception {
        user.replaceTeamOwnerFounder(toAdd,toRemove,fromTeam);
    }


    /**
     * get coaches without team in order to replace the coach of team
     * @return
     * @codeBy Eden
     */
    public LinkedList<Coach> getCoachesWithoutTeam( ){
        LinkedList<Coach> allCoaches = MainSystem.getInstance().getAllCoach();
        LinkedList<Coach> coachesWithoutTeam = new LinkedList<>();
        for(Coach coach : allCoaches){
            if(coach.getCoachTeam() == null){
                coachesWithoutTeam.add(coach);
            }
        }
        return coachesWithoutTeam;
    }
    public void replaceCoachAtTeam(Coach coachToReplace, Team t, SystemManager sm) throws Exception {

        if(!sm.replaceCoachAtTeam(coachToReplace,t)){
            throw new Exception("cannot replace those coaches");
        }
    }
    /**
     * get players without team in order to add the player to team
     * @return
     * @codeBy Eden
     */
    public LinkedList<Player> getPlayersWithoutTeam( ){
        LinkedList<Player> allPlayers = MainSystem.getInstance().getAllPlayer();
        LinkedList<Player> playersToReturn = new LinkedList<>();
        for(Player p : allPlayers){
            if(p.getTeam() == null){
                playersToReturn.add(p);
            }
        }
        return playersToReturn;
    }

    /**
     * add player to team in order to delete one of them
     * @param p
     * @param t
     * @param user
     * @codeBy Eden
     */
    public void addPlayerToTeam(Player p,Team t, SystemManager user) throws Exception {
        if(!user.addPlayerToTeam(p,t)){
            throw new Exception("cannot add "+p.getTeamRole().getUserName()+" to Team:"+t.getName());
        }
    }
    /**
     * @return FileReader pointer to Log file
     * @codeBy Eden
     */
    public FileReader showSystemDetails (SystemManager user) throws FileNotFoundException {
        return user.showSystemInfo();
    }

    /***
     * mark list of notifications as read.
     * @param sM
     * @param read
     * @codeBy Eden
     */
    public void markAsReadNotification (SystemManager sM,HashSet<Notification> read){
        for(Notification n: read){
            if(sM.getNotificationsList().contains(n)) {
                sM.MarkAsReadNotification(n);
            }
        }
    }


    public void initSystemFromDB (){

        List<User> loginUsers;
        LinkedList<League> leagues;
        LinkedList<User> users;
        LinkedList<Season> seasons;
        Season currSeason;
        HashSet<Team> activeTeams;
        HashSet<String> userNames;
        HashSet<String> teamNames;
        HashSet<Field> fields;
        HashMap<Integer, Season> yearPerSeason;
        HashMap<String, League> nameOfLeaguePerLeague;

    }



    // TODO: 19/04/2020  watching and answers complaints - next iteration



}



