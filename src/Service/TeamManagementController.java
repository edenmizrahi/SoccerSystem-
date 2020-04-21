package Service;
import Domain.*;
import Domain.Enums.TeamManagerPermissions;
import Domain.LeagueManagment.Field;
import Domain.LeagueManagment.Team;
import Domain.Users.*;
import java.security.acl.Permission;
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
    public TeamRole subscribeTeamManager(TeamOwner user, Fan fanToSubscribe, Team team, HashSet<TeamManagerPermissions> per) throws Exception {
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

    /**
     * adi
     * @param user
     * @param coachToRemove
     * @param coachToAdd
     * @param newCoachRoleAtTeam
     * @param team
     * @throws Exception
     */
    public void removeAndReplaceCoach(TeamRole user, Coach coachToRemove, TeamRole coachToAdd, String newCoachRoleAtTeam, Team team) throws Exception {
        if (user.isTeamOwner()){
            user.getTeamOwner().removeAndReplaceCoach(coachToRemove, coachToAdd, newCoachRoleAtTeam, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().removeAndReplaceCoach(coachToRemove, coachToAdd, newCoachRoleAtTeam, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     *
     * @param user
     * @param coach
     * @param role
     * @throws Exception
     */
    public void editCoachRole(TeamRole user, Coach coach, String role) throws Exception{
        if (user.isTeamOwner()){
            user.getTeamOwner().editCoachRole(coach, role);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().editCoachRole(coach, role);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @param playerToAdd
     * @param role
     * @param team
     * @throws Exception
     */
    public void addPlayer(TeamRole user, TeamRole playerToAdd, String role, Team team) throws Exception {
        if (user.isTeamOwner()){
            user.getTeamOwner().addPlayer(playerToAdd, role, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().addPlayer(playerToAdd, role, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @param player
     * @param team
     * @throws Exception
     */
    public void removePlayer (TeamRole user, Player player, Team team) throws Exception {
        if (user.isTeamOwner()){
            user.getTeamOwner().removePlayer(player, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().removePlayer(player, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @param player
     * @param role
     * @throws Exception
     */
    public void editPlayerRole(TeamRole user, Player player, String role) throws Exception{
        if (user.isTeamOwner()){
            user.getTeamOwner().editPlayerRole(player, role);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().editPlayerRole(player, role);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param user
     * @param fieldtoRemove
     * @param fieldToAdd
     * @param team
     * @throws Exception
     */
    public void removeAndReplaceField (TeamRole user, Field fieldtoRemove, Field fieldToAdd, Team team) throws Exception {
        if (user.isTeamOwner()){
            user.getTeamOwner().removeAndReplaceField(fieldtoRemove, fieldToAdd, team);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().removeAndReplaceField(fieldtoRemove, fieldToAdd, team);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

    /**
     * adi
     * @param field
     * @param name
     */
    public void editFieldName(TeamRole user, Field field, String name) throws Exception{
        if (user.isTeamOwner()){
            user.getTeamOwner().editFieldName(field, name);
        }
        // the function in team manager checks if has permission
        else if (user.isTeamManager()){
            user.getTeamManager().editFieldName(field, name);
        }
        // user isn't teamOwner or teamManager
        else{
            throw new Exception("user doesn't have the permission to do this action");
        }
    }

        //<editor-fold desc="getters">
    /**
     * adi
     * get all the possible users i can subscribe to become team owner or manager
     * @param user
     * @return
     */
    public LinkedList<Fan> getAllPossibleSubscribeTeamOwnerOrManager(TeamRole user){
        LinkedList<Fan> allFans = user.getSystem().getAllFans();
        LinkedList<Fan> ans = new LinkedList<>();
        for(Fan fan : allFans){
            if (!(fan instanceof SystemManager) && !(fan instanceof Rfa) && !(fan instanceof Referee)){
                if (!(fan instanceof TeamRole)){
                    ans.add(fan);
                }
                else if(fan instanceof TeamRole && !((TeamRole) fan).isTeamManager()) {
                    ans.add(fan);
                }
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
    /**
     * adi
     * @param team
     * @return
     */
    public Coach getCoach (Team team){
        return team.getCoach();
    }
    /**
     * adi
     * @param user
     * @return
     */
    public LinkedList<Coach> getAllCoachWithoutTeam(TeamRole user){
        LinkedList<Coach> allCoaches = user.getSystem().getAllCoach();
        LinkedList<Coach> ans = new LinkedList<>();
        for(Coach coach : allCoaches){
            if(coach.getCoachTeam() == null){
                ans.add(coach);
            }
        }
        return ans;
    }
    /**
     *
     * @param user
     * @return
     */
    public LinkedList<Player> getAllPlayerWithoutTeam(TeamRole user){
        LinkedList<Player> allPlayers = user.getSystem().getAllPlayer();
        LinkedList<Player> ans = new LinkedList<>();
        for(Player player : allPlayers){
            if(player.getTeam() == null){
                ans.add(player);
            }
        }
        return ans;
    }
    /**
     *
     * @param team
     * @return
     */
    public HashSet<Player> getAllPlayersOfTeam (Team team){
        return team.getPlayers();
    }
    /**
     *
     * @param team
     * @return
     */
    public Field getField (Team team){
        return team.getField();
    }
    //</editor-fold>

}
