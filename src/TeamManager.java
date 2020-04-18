import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import org.apache.logging.log4j.LogManager;

public class TeamManager {
    private TeamRole teamRole;
    private Team team;
    private HashMap<TeamRole, Team> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();
    private HashSet<Permission> permissions;

    public TeamManager(TeamRole teamRole,Team team, HashSet<Permission> pers) {
        this.team = team;
        this.team.setTeamManager(this);
        mySubscriptions = new HashMap<>();
        permissions = new HashSet<>();
        this.permissions.addAll(pers);
        this.teamRole= teamRole;
    }

    //<editor-fold desc="getters and setters">

    public HashMap<TeamRole, Team> getMySubscriptions() {
        return mySubscriptions;
    }

    public void setMySubscriptions(HashMap<TeamRole, Team> mySubscriptions) {
        this.mySubscriptions = mySubscriptions;
    }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
    //</editor-fold>

    //<editor-fold desc="add remove and edit">
    // adi
    public TeamOwner subscribeTeamOwner(Fan fan, MainSystem ms, Team team) throws Exception{
        if (fan == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamOwner)) {
            // check if already team owner of this team
            if (fan instanceof TeamRole && ((TeamRole) fan).isTeamOwner() && team.getTeamOwners().contains(((TeamRole) fan).getTeamOwner())){
                throw new Exception("Already Team Owner of this team");
            }
            TeamRole teamRole;
            // check if already team owner of different team
            if (fan instanceof TeamRole && ((TeamRole) fan).isTeamOwner()){
                ((TeamRole) fan).getTeamOwner().setTeam(team);
                team.addTeamOwner(((TeamRole) fan).getTeamOwner());
                teamRole = ((TeamRole) fan);
            }
            // check if already teamRole
            else if (fan instanceof TeamRole){
                ((TeamRole) fan).becomeTeamOwner();
                ((TeamRole) fan).getTeamOwner().setTeam(team);
                team.addTeamOwner(((TeamRole) fan).getTeamOwner());
                teamRole = ((TeamRole) fan);
            }
            // else just a fan
            else{
                teamRole = new TeamRole(fan);
                teamRole.becomeTeamOwner();
                teamRole.getTeamOwner().setTeam(team);
                team.addTeamOwner(teamRole.getTeamOwner());
            }
            mySubscriptions.put(teamRole, team);
            return teamRole.getTeamOwner();
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    // adi
    public void removeTeamOwner (TeamOwner tO, MainSystem ms, Team team)throws Exception{
        if (tO == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamOwner)) {
            if (mySubscriptions.containsKey(tO.getTeamRole())) {
                team.removeTeamOwner(tO);
                mySubscriptions.remove(tO.getTeamRole());
                for (Map.Entry<TeamRole, Team> entry : tO.getMySubscriptions().entrySet()) {
                    if (entry.getValue().equals(team)) {
                        if (entry.getKey().isTeamOwner()){
                            tO.removeTeamOwner(entry.getKey().getTeamOwner(), ms, entry.getValue());
                        }
                        else{
                            tO.removeTeamManager(entry.getKey().getTeamManager(), ms, entry.getValue());
                        }
                    }
                }
                tO.removeTeam(team);
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removeAndReplaceCoach(Coach coachToRemove, TeamRole coachToAdd, String newCoachRoleAtTeam, Team team) throws Exception {
        if (coachToRemove == null || coachToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            if (team.getCoach().equals(coachToRemove)) {
                team.removeCoach(coachToRemove);
                coachToRemove.setCoachTeam(null);
                if (!coachToAdd.isCoach()) {
                    coachToAdd.becomeCoach();
                }
                team.addCoach(coachToAdd.getCoach());
                coachToAdd.getCoach().setCoachTeam(team);
                coachToAdd.getCoach().setRoleAtTeam(newCoachRoleAtTeam);
            } else {
                throw new Exception("This Coach doesn't exist in this team");
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void editCoachRole(Coach coach, String role)throws Exception {
        if (coach == null || role == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            coach.setRoleAtTeam(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi TODO CHANGE THAT DOESNT RECEIVE DATEOFBIRTH
    public void addPlayer(TeamRole player, String role, Date dateofBirth, Team team) throws Exception{
        if (player == null || role == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            if (!player.isPlayer()) {
                player.becomePlayer(dateofBirth);
            }
            team.addPlayer(player.getPlayer());
            player.getPlayer().setPlayerTeam(team);
            player.getPlayer().setRoleAtField(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removePlayer (Player player, Team team) throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            team.removePlayer(player);
            player.setPlayerTeam(null);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void editPlayerRole(Player player, String role)throws Exception {
        if (player == null || role == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            player.setRoleAtField(role);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removeAndReplaceField (Field fieldtoRemove, Field fieldToAdd, Team team) throws Exception {
        if (fieldtoRemove == null || fieldToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            team.removeField(fieldtoRemove);
            fieldtoRemove.removeTeam(team);
            team.setField(fieldToAdd);
            fieldToAdd.addTeam(team);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void editFieldName(Field field, String name) throws Exception {
        if (field == null || name == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            field.setNameOfField(name);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //</editor-fold>
}
