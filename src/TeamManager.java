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
                        if (entry.getKey() instanceof TeamOwner){
                            tO.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                        }
                        else{
                            tO.removeTeamManager((TeamManager) entry.getKey(), ms, entry.getValue());
                        }
                    }
                }
                ms.removeUser(tO);
                Subscription newSub = new Subscription(ms, tO.getName(), tO.getPhoneNumber(), tO.getEmail(), tO.getUserName(), tO.getPassword());
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    // adi
    public TeamManager subscribeTeamManager(Subscription sub, MainSystem ms, Team team, HashSet<Permission> per) throws Exception{
        if (sub == null || ms == null || team == null || per == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamManager)) {
            if (sub instanceof TeamManager && team.getTeamManager().equals(sub)) {
                throw new Exception("Already Team Manager of this team");
            }
            TeamManager tM = new TeamManager(sub, ms, team, per);
            mySubscriptions.put(tM, team);
            return tM;
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    // adi
    public void removeTeamManager (TeamManager tM, MainSystem ms, Team team) throws Exception{
        if (tM == null || ms == null || team == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditTeamManager)) {
            if (mySubscriptions.containsKey(tM)) {
                team.removeTeamManager(tM);
                mySubscriptions.remove(tM);
                for (Map.Entry<Subscription, Team> entry : tM.getMySubscriptions().entrySet()) {
                    if (entry.getValue().equals(team)) {
                        if (entry.getKey() instanceof TeamOwner){
                            tM.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                        }
                        else{
                            tM.removeTeamManager((TeamManager) entry.getKey(), ms, entry.getValue());
                        }
                    }
                }
                ms.removeUser(tM);
                Subscription newSub = new Subscription(ms, tM.getName(), tM.getPhoneNumber(), tM.getEmail(), tM.getUserName(), tM.getPassword());
            }
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }

    //adi
    public void addCoach(Coach coach, Team team) throws Exception {
        if (coach == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            team.setCoach(coach);
        }
        else{
            throw new Exception("This user doesn't have the permission to do this action");
        }
    }
    //adi
    public void removeCoach(Coach coach, Team team) throws Exception {
        if (coach == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditCoach)) {
            team.removeCoach(coach);
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
    //adi
    public void addPlayer(Player player, Team team)throws Exception {
        if (player == null || team == null){
            throw new NullPointerException();
        }
        if (this.permissions.contains(Permission.addRemoveEditPlayer)) {
            team.addPlayer(player);
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
