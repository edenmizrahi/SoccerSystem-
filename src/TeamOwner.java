import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TeamOwner{
    private TeamRole teamRole;
    private LinkedList<Team> teams;
    private HashSet<TeamSubscription> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();

    //team owner founder- with no team.
    public TeamOwner(TeamRole teamRole) {
        this.teams = new LinkedList<>();
        mySubscriptions = new HashSet<>();
        this.teamRole = teamRole;
    }

    //<editor-fold desc="add remove and edit">
    //adi TODO CHANGE FUNCTION
    public void createTeam (String name, HashSet<Player> players ,Coach coach, Field field) throws Exception{
        if(name == null || players == null || coach == null){
            throw new NullPointerException();
        }
        Team team = new Team(name, players, coach, field, this);
        this.teams.add(team);
    }


    // adi
    public TeamOwner subscribeTeamOwner(Fan fan, MainSystem ms, Team team) throws Exception{
        if (fan == null || ms == null || team == null){
            throw new NullPointerException();
        }
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
        TeamSubscription sub = new TeamSubscription(teamRole.getTeamOwner(), team, teamRole);
        mySubscriptions.add(sub);
        return teamRole.getTeamOwner();
    }

    // adi
    public void removeTeamOwner (TeamOwner tO, MainSystem ms, Team team)throws Exception{
        if (tO == null || ms == null || team == null){
            throw new NullPointerException();
        }
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
    // adi
    public TeamManager subscribeTeamManager(Fan fan, MainSystem ms, Team team, HashSet<Permission> per) throws Exception{
        if (fan == null || ms == null || team == null || per == null){
            throw new NullPointerException();
        }
        // check if already team manager of this team
        if (fan instanceof TeamRole && ((TeamRole) fan).isTeamManager() && team.getTeamManager().equals(((TeamRole) fan).getTeamManager())){
            throw new Exception("Already Team Manager of this team");
        }
        TeamRole teamRole;
        // check if already teamRole
        if (fan instanceof TeamRole){
            ((TeamRole) fan).becomeTeamManager(team, per);
            ((TeamRole) fan).getTeamManager().setTeam(team);
            team.addTeamManager(((TeamRole) fan).getTeamManager());
            teamRole = ((TeamRole) fan);
        }
        // else just a fan
        else{
            teamRole = new TeamRole(fan);
            teamRole.becomeTeamManager(team, per);
            teamRole.getTeamManager().setTeam(team);
            team.addTeamManager(teamRole.getTeamManager());
        }
        mySubscriptions.put(teamRole, team);
        return teamRole.getTeamManager();

    }
    // adi
    public void removeTeamManager (TeamManager tM, MainSystem ms, Team team) throws Exception{
        if (tM == null || ms == null || team == null || team == null){
            throw new NullPointerException();
        }
        if (mySubscriptions.containsKey(tM.getTeamRole())){
            team.removeTeamManager(tM);
            mySubscriptions.remove(tM.getTeamRole());
            for (Map.Entry<TeamRole, Team> entry : tM.getMySubscriptions().entrySet()) {
                if (entry.getValue().equals(team)) {
                    tM.removeTeamOwner(entry.getKey().getTeamOwner(), ms, entry.getValue());
                }
            }
            tM.setTeam(null);
            tM.getTeamRole().deleteTeamManager();
        }
    }
    //adi
    public void removeAndReplaceCoach(Coach coachToRemove, TeamRole coachToAdd, String newCoachRoleAtTeam, Team team) throws Exception {
        if (coachToRemove == null || coachToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (team.getCoach().equals(coachToRemove)) {
            team.removeCoach(coachToRemove);
            coachToRemove.setCoachTeam(null);
            if (!coachToAdd.isCoach()){
                coachToAdd.becomeCoach();
            }
            team.addCoach(coachToAdd.getCoach());
            coachToAdd.getCoach().setCoachTeam(team);
            coachToAdd.getCoach().setRoleAtTeam(newCoachRoleAtTeam);
        }
        else {
            throw new Exception("This Coach doesn't exist in this team");
        }
    }
    //adi
    public void editCoachRole(Coach coach, String role){
        if (coach == null || role == null){
            throw new NullPointerException();
        }
        coach.setRoleAtTeam(role);
    }
    //adi TODO CHANGE THAT DOESNT RECEIVE DATEOFBIRTH
    public void addPlayer(TeamRole player, String role, Date dateofBirth, Team team){
        if (player == null || role == null || team == null){
            throw new NullPointerException();
        }
        if (!player.isPlayer()){
            player.becomePlayer(dateofBirth);
        }
        team.addPlayer(player.getPlayer());
        player.getPlayer().setPlayerTeam(team);
        player.getPlayer().setRoleAtField(role);
    }
    //adi
    public void removePlayer (Player player, Team team) throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        team.removePlayer(player);
        player.setPlayerTeam(null);
    }
    //adi
    public void editPlayerRole(Player player, String role){
        if (player == null || role == null){
            throw new NullPointerException();
        }
        player.setRoleAtField(role);
    }
    //adi
    public void removeAndReplaceField (Field fieldtoRemove, Field fieldToAdd, Team team) throws Exception {
        if (fieldtoRemove == null || fieldToAdd == null || team == null){
            throw new NullPointerException();
        }
        team.removeField(fieldtoRemove);
        fieldtoRemove.removeTeam(team);
        team.setField(fieldToAdd);
        fieldToAdd.addTeam(team);
    }
    //adi
    public void editFieldName(Field field, String name){
        if (field == null || name == null){
            throw new NullPointerException();
        }
        field.setNameOfField(name);
    }
    //</editor-fold>

    //<editor-fold desc="setters and getters">
    public void setTeam(Team team) {
        this.teams.add(team);
    }
    public void removeTeam(Team team){
        if (teams.contains(team)){
            teams.remove(team);
        }
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public HashSet<TeamSubscription> getMySubscriptions() {
        return mySubscriptions;
    }

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }

    //</editor-fold>
}
