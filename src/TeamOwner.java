import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TeamOwner implements Observer {
    private TeamRole teamRole;

    private HashMap<TeamRole, Team> mySubscriptions;
    private LinkedList<Team> teams;
    private LinkedList<Team> requestedTeams;
    private LinkedList<Team> deletedTeams;
    private LinkedList<Team> approvedTeams;
    private static final Logger LOG = LogManager.getLogger();

    //team owner founder- with no team.
    public TeamOwner(TeamRole teamRole) {
        this.teams = new LinkedList<>();
        this.requestedTeams=new LinkedList<>();
        this.deletedTeams= new LinkedList<>();
        this.approvedTeams= new LinkedList<>();
        mySubscriptions = new HashMap<>();
        this.teamRole= teamRole;
    }


    //or
    public void requestNewTeam(String name){
        Team t= new Team(name,this);
        //the request is sent in the Constructor
        requestedTeams.add(t);
    }
    //or
    public void makeTeamActive(Team team, HashSet<Player> players , Coach coach, Field field) throws Exception{
        if(team == null || players == null || coach == null){
            throw new NullPointerException();
        }
        if(! approvedTeams.contains(team)){
            throw  new Exception("this team is not approved by RFA");
        }
        team.becomeActive(players,coach,field);
        this.teams.add(team);
        this.approvedTeams.remove(team);
    }

    /**OR**/
    //delete Team
    public void deleteTeam(Team team) throws Exception {
        if(team==null){
            throw new NullPointerException();
        }
        if(team.getLeaguePerSeason().containsKey(teamRole.system.getCurrSeason())){
            throw new Exception("team is play in the current season ,you cannot delete the team untill the end of the season");
        }
        Date currDate= new Date(System.currentTimeMillis());
        for (Match m:team.getHome()) {
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }
        for( Match m:team.getAway()){
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }

        team.deleteTeamByTeamOwner();
        teams.remove(team);
        deletedTeams.add(team);

        for (SystemManager sm:teamRole.system.getSystemManagers()) {
            team.addObserver(sm);
        }
        team.notifyObservers("team deleted by team owner");

        //TODO: founder =null
        // for each teamowner go over the subscriptions and delete the sub of the team
        //move the team to deleted for all the team owners


    }

    /**Or**/
    public void reopenTeam(Team team,HashSet<Player> players, Coach coach, Field field) throws Exception {
        //TODO: move the team to the active team list for all the team owners
        if(!deletedTeams.contains(team)){
            throw new Exception("the team was not deleted");
        }
        if(team ==null){
            throw new NullPointerException();
        }
        team.becomeActive(players,coach,field);
        deletedTeams.remove(team);
        teams.add(team);

        //notify system managers
        for (SystemManager sm:teamRole.system.getSystemManagers()) {
            team.addObserver(sm);
        }
        team.notifyObservers("team reopened by team owner");

        //TODO: i AM THE ONLY FOUNDER- only team owner
        //delete all the other team owners
        //delete the team from the deletedteam list
        // move the team to the active team list for all the team owners
    }


    //<editor-fold desc="add remove and edit">



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

    public TeamRole getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }

    public void setTeams(LinkedList<Team> teams) {
        this.teams = teams;
    }

    public LinkedList<Team> getRequestedTeams() {
        return requestedTeams;
    }

    public void setRequestedTeams(LinkedList<Team> requestedTeams) {
        this.requestedTeams = requestedTeams;
    }

    public LinkedList<Team> getDeletedTeams() {
        return deletedTeams;
    }

    public void setDeletedTeams(LinkedList<Team> deletedTeams) {
        this.deletedTeams = deletedTeams;
    }

    public LinkedList<Team> getApprovedTeams() {
        return approvedTeams;
    }

    public void setApprovedTeams(LinkedList<Team> approvedTeams) {
        this.approvedTeams = approvedTeams;
    }

    //</editor-fold>

    /**or**/
    public void addIncomeToTeam(Team team,String typeOfIncome, long amount) throws Exception {
        if(team==null || typeOfIncome==null){
            throw  new NullPointerException();
        }
        team.addIncome(typeOfIncome,amount);
    }
    /**or**/
    public void addExpenseToTeam(Team team,String typeOfExpense, long amount) throws Exception {
        if(team==null || typeOfExpense==null){
            throw  new NullPointerException();
        }
        team.addExpense(typeOfExpense,amount);
    }

    /**OR**/
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Team){
            if(arg.equals(true)){// the team can be open
               requestedTeams.remove(o);
               approvedTeams.add((Team)o);
            }
            else if(arg.equals(false)){// the team cant be open
                ((Team)o).deleteObservers();
                requestedTeams.remove((Team)o);
            }
        }
    }
}
