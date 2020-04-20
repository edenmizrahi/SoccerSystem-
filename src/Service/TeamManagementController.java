package Service;
import Domain.*;
import Domain.LeagueManagment.Team;
import Domain.Users.*;

import java.util.HashSet;
import java.util.LinkedList;

public class TeamManagementController {

    /**
     * adi
     * @param user
     * @param fanToSubscribe
     * @param team
     * @return
     * @throws Exception
     */
    public TeamRole subscribeTeamOwner(TeamRole user, Fan fanToSubscribe, Team team) throws Exception {
        if (user.isTeamOwner()){
            return user.getTeamOwner().subscribeTeamOwner(fanToSubscribe, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            return user.getTeamManager().subscribeTeamOwner(fanToSubscribe, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }
    /**
     * adi
     * @param user
     * @param tOToRemove
     * @param team
     * @throws Exception
     */
    public void removeTeamOwner (TeamRole user, TeamOwner tOToRemove, Team team) throws Exception{
        if (user.isTeamOwner()){
            user.getTeamOwner().removeTeamOwner(tOToRemove, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().removeTeamOwner(tOToRemove, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @param fanToSubscribe
     * @param team
     * @param per
     * @return
     * @throws Exception
     */
    public TeamRole subscribeTeamManager(TeamOwner user, Fan fanToSubscribe, Team team, HashSet<Permission> per) throws Exception {
        return user.subscribeTeamManager(fanToSubscribe, team, per);
    }

    /**
     * adi
     * @param user
     * @param tMToRemove
     * @param team
     * @throws Exception
     */
    public void removeTeamManager (TeamOwner user, TeamManager tMToRemove, Team team) throws Exception{
        user.removeTeamManager(tMToRemove, team);
    }

    //<editor-fold desc="getters">
    /**
     * adi
     * get all the possible users i can subscribe to team owner
     * @param user
     * @return
     */
    public LinkedList<Fan> getAllPossibleSubscribeTeamOwner(TeamRole user){
        LinkedList<Fan> allFans = user.getSystem().getAllFans();
        LinkedList<TeamRole> teamRoles = user.getSystem().getTeamRoles();
        LinkedList<Fan> ans = new LinkedList<>();
        // add all team roles
        for (TeamRole teamRole : teamRoles){
            ans.add(teamRole);
        }
        // add if just a fan
        for(Fan fan : allFans){
            if (!(fan instanceof SystemManager) && !(fan instanceof Rfa) && !(fan instanceof Referee)){
                ans.add(fan);
            }
        }
        return ans;
    }
    /**
     * adi
     * get teams for team owner
     * @param user
     * @return
     */
    public LinkedList<Team> getAllMyTeams (TeamOwner user){
            return user.getTeams();
    }
    /**
     * adi
     * get teams for team manager
     * @param user
     * @return
     */
    public Team getAllMyTeams (TeamManager user){
        return user.getTeam();
    }
    /**
     * adi
     * get all team owners of specific team
     * @param team
     * @return
     */
    public HashSet<TeamOwner> getAllTeamOwners (Team team){
        return team.getTeamOwners();
    }
    //</editor-fold>




}
