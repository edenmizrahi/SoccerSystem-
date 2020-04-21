package Service;

import Domain.LeagueManagment.Team;
import Domain.MainSystem;
import Domain.Notifications.Notification;
import Domain.Users.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SystemManagerController {

    /**
     * close team  forever
     *
     * @param teamToRemove
     * @param user
     * @throws Exception
     */
    public void closeTeamForever(Team teamToRemove, SystemManager user) throws Exception {
        user.removeTeamFromSystem(teamToRemove);
    }

    /**
     * for input to delete user function
     * get user by name is not an unique field so return a list of
     * matches users.
     *
     * @param name
     * @return list of match users.
     */
    public LinkedList<User> getUserByName(String name, SystemManager user) {
        List<User> users = user.getSystem().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            if (user.getName().equals(name)) ;
            matches.add(cur);
        }
        return matches;
    }

    /**
     * for input to delete user function
     *User name is an unique field so this function return one user if there is user with
     *
     * @param userName
     * @param user
     * @return
     */
    public User getUserByUserName(String userName, SystemManager user) {
        List<User> users = user.getSystem().getUsers();
        LinkedList<User> matches = new LinkedList<User>();
        for (User cur : users) {
            if (user.getUserName().equals(userName)) {
                return cur;
            }
        }
        return null;
    }

    /**
     * Delete user from System
     * @param u user to delete
     * @param systemManager
     * @throws Exception if delete is not valid.
     */
    public void deleteUserForever(Fan u, SystemManager systemManager) throws Exception {
        systemManager.removeUser(u);
    }


    /**
     * replace team's founder .
     * @param user
     * @param toAdd
     * @param toRemove
     * @param fromTeam
     * @throws Exception if team is not in the team owner's teams list.
     */
    public void replaceTeamFounder(SystemManager user, TeamOwner toAdd, TeamOwner toRemove , Team fromTeam) throws Exception {
        user.replaceTeamOwnerFounder(toAdd,toRemove,fromTeam);
    }


    /**
     * get coaches without team in order to replace the coach of team
     * @return
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
            throw new Exception("cannot replace coach "+t.getCoach().getTeamRole().getUserName()+"with "+coachToReplace.getTeamRole().getUserName()+"at team"+t.getName());
        }
    }
    /**
     * get players without team in order to add the player to team
     * @return
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
     */
    public void addPlayerToTeam(Player p,Team t, SystemManager user) throws Exception {
        if(user.addPlayerToTeam(p,t)){
            throw new Exception("cannot add "+p.getTeamRole().getUserName()+" to Team:"+t.getName());
        }
    }
    /**
     * @return FileReader pointer to Log file
     */
    public FileReader showSystemDetails (SystemManager user) throws FileNotFoundException {
        return user.showSystemInfo();
    }

    /***
     * mark list of notifications as read.
     * @param sM
     * @param read
     */
    public void markAsReadNotification (SystemManager sM,HashSet<Notification> read){
        for(Notification n: read){
            if(sM.getNotificationsList().contains(n)) {
                sM.MarkAsReadNotification(n);
            }
        }
    }


    // TODO: 19/04/2020  watching and answers complaints - next iteration



}


