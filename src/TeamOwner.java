import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class TeamOwner{
    private TeamRole teamRole;
    private LinkedList<Team> teams;
    private HashMap<TeamRole, Team> mySubscriptions;
    private static final Logger LOG = LogManager.getLogger();

    //team owner founder- with no team.
    public TeamOwner(TeamRole teamRole) {
        this.teams = new LinkedList<>();
        mySubscriptions = new HashMap<>();
        //TODO add permissions
        //this.permissions.add();
        this.teamRole= teamRole;
    }




    //<editor-fold desc="add remove and edit">
    //adi
    public void createTeam (String name, HashSet<Player> players ,Coach coach, Field field) throws Exception{
        if(name == null || players == null || coach == null){
            throw new NullPointerException();
        }
        Team team = new Team(name, players, coach, field, this);
        this.teams.add(team);
    }


    // adi
    public TeamOwner subscribeTeamOwner(Subscription sub, MainSystem ms, Team team) throws Exception{
        if (sub == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (sub instanceof TeamOwner && team.getTeamOwners().contains(sub)){
            throw new Exception("Already Team Owner in this team");
        }
        //TeamOwner tO = new TeamOwner(sub, ms, team);
       // mySubscriptions.put(tO, team);
       // return tO;
        return null;// just for compilation
    }
    // adi
    public void removeTeamOwner (TeamOwner tO, MainSystem ms, Team team)throws Exception{
        if (tO == null || ms == null || team == null){
            throw new NullPointerException();
        }
        if (mySubscriptions.containsKey(tO)){
            team.removeTeamOwner(tO);
            mySubscriptions.remove(tO);
            for (Map.Entry<Subscription, Team> entry : tO.mySubscriptions.entrySet()) {
                if (entry.getValue().equals(team)) {
                    if (entry.getKey() instanceof TeamOwner){
                        tO.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                    }
                    else{
                        tO.removeTeamManager((TeamManager) entry.getKey(), ms, entry.getValue());
                    }
                }
            }
            tO.removeTeam(team);
            if (tO.getTeams().size() == 0){
                ms.removeUser(tO);
                Subscription newSub = new Subscription(ms, tO.getName(), tO.getPhoneNumber(), tO.getEmail(), tO.getUserName(), tO.getPassword());
            }
        }
    }
    // adi
    public TeamManager subscribeTeamManager(Subscription sub, MainSystem ms, Team team, HashSet<Permission> per) throws Exception{
        if (sub == null || ms == null || team == null || per == null){
            throw new NullPointerException();
        }
        if (sub instanceof TeamManager && team.getTeamManager().equals(sub)){
            throw new Exception("Already Team Manager of this team");
        }
        TeamManager tM = new TeamManager(sub, ms, team, per);
        mySubscriptions.put(tM, team);
        return tM;
    }
    // adi
    public void removeTeamManager (TeamManager tM, MainSystem ms, Team team) throws Exception{
        if (tM == null || ms == null || team == null || team == null){
            throw new NullPointerException();
        }
        if (mySubscriptions.containsKey(tM)){
            team.removeTeamManager(tM);
            mySubscriptions.remove(tM);
            for (Map.Entry<Subscription, Team> entry : tM.getMySubscriptions().entrySet()) {
                if (entry.getValue().equals(team)) {
                    if (entry.getKey() instanceof TeamOwner){
                        tM.removeTeamOwner((TeamOwner) entry.getKey(), ms, entry.getValue());
                    }
                }
            }
            ms.removeUser(tM);
            tM.setTeam(null);
            Subscription newSub = new Subscription(ms, tM.getName(), tM.getPhoneNumber(), tM.getEmail(), tM.getUserName(), tM.getPassword());
        }
    }
    //adi
    public void addTeamManager(TeamManager tM, Team team){
        if (tM == null || team == null){
            throw new NullPointerException();
        }
        team.addTeamManager(tM);
        tM.setTeam(team);
    }
    //adi
    public void addCoach(Coach coachToAdd, Team team){
        if (coachToAdd == null || team == null){
            throw new NullPointerException();
        }
        team.setCoach(coachToAdd);
        coachToAdd.setCoachTeam(team);
    }
    //adi
    public void removeCoach(Coach coachToRemove, Coach coachToAdd, Team team) throws Exception {
        if (coachToRemove == null || coachToAdd == null || team == null){
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
    public void editCoachRole(Coach coach, String role){
        if (coach == null || role == null){
            throw new NullPointerException();
        }
        coach.setRoleAtTeam(role);
    }
    //adi
    public void addPlayer(Player player, Team team){
        if (player == null || team == null){
            throw new NullPointerException();
        }
        team.addPlayer(player);
        player.setPlayerTeam(team);
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
    public void addField(Field field, Team team) {
        if (field == null || team == null){
            throw new NullPointerException();
        }
        team.setField(field);
        field.addTeam(team);
    }
    //adi
    public void removeField (Field field, Team team) throws Exception {
        if (field == null || team == null){
            throw new NullPointerException();
        }
        team.removeField(field);
        field.removeTeam(team);
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

    public HashMap<Subscription, Team> getMySubscriptions() {
        return mySubscriptions;
    }

    //</editor-fold>
}
