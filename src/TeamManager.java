import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

public class TeamManager extends Subscription{

    private Team team;
    private HashMap<Subscription, Team> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();

    public TeamManager(Subscription sub, MainSystem ms, Team team, HashSet<Permission> pers) {
        super(ms, sub.getName(), sub.getPhoneNumber(), sub.getEmail(), sub.getUserName(), sub.getPassword());
        ms.removeUser(sub);
        this.team = team;
        this.team.setTeamManager(this);
        mySubscriptions = new HashMap<>();
        this.permissions.addAll(pers);
    }

    //<editor-fold desc="getters and setters">

    public HashMap<Subscription, Team> getMySubscriptions() {
        return mySubscriptions;
    }

    public void setMySubscriptions(HashMap<Subscription, Team> mySubscriptions) {
        this.mySubscriptions = mySubscriptions;
    }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }
    //</editor-fold>

    //<editor-fold desc="add remove and edit">
    // adi
    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team) throws Exception{
        if (sub == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamOwner)) {
            if (sub instanceof TeamOwner && team.getTeamOwners().contains(sub)) {
                throw new Exception("Already Team Owner in this team");
            }
            TeamOwner tO = new TeamOwner(sub, ms, team);
            mySubscriptions.put(tO, team);
            return tO;
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
            if (mySubscriptions.containsKey(tO)) {
                team.removeTeamOwner(tO);
                mySubscriptions.remove(tO);
                for (Map.Entry<Subscription, Team> entry : tO.getMySubscriptions().entrySet()) {
                    if (entry.getValue().equals(team)) {
                        if (entry.getKey() instanceof TeamOwner) {
                            tO.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                        } else {
                            tO.removeTeamManager((TeamManager) entry.getKey(), ms, entry.getValue());
                        }
                    }
                }
                tO.removeTeam(team);
                if (tO.getTeams().size() == 0) {
                    ms.removeUser(tO);
                    Subscription newSub = new Subscription(ms, tO.getName(), tO.getPhoneNumber(), tO.getEmail(), tO.getUserName(), tO.getPassword());
                }
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void addCoach(Coach coachToAdd, Team team) throws Exception {
        if (coachToAdd == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            team.setCoach(coachToAdd);
            coachToAdd.setCoachTeam(team);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removeCoach(Coach coachToRemove, Coach coachToAdd, Team team) throws Exception {
        if (coachToRemove == null || team == null){
            throw new NullPointerException();
        }
        if (team.getCoach().equals(coachToRemove)) {
            team.removeCoach(coachToRemove, coachToAdd);
            coachToRemove.setCoachTeam(null);
            coachToAdd.setCoachTeam(team);
        }
        else {
            throw new Exception("This Coach doesn't exist in this team");
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
    //adi
    public void addPlayer(Player player, Team team)throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            team.addPlayer(player);
            player.setPlayerTeam(team);
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
    public void addField(Field field, Team team) throws Exception {
        if (field == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            team.setField(field);
            field.addTeam(team);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removeField (Field field, Team team) throws Exception {
        if (field == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditField)) {
            team.removeField(field);
            field.removeTeam(team);
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
