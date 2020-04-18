import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Team extends Observable implements PageOwner{
    private static final Logger LOG = LogManager.getLogger();
    private String name;
    private HashSet<Match> home;
    private HashSet<Match> away;
    private TeamManager teamManager;
    private HashMap<Season,League> leaguePerSeason;
    private TeamOwner founder;
    /**I think between 1..* there is no team without players.. **/
    private HashSet<Player> players;
    private Coach coach;
    private HashSet<TeamOwner> teamOwners;
    private Field field;
    private PrivatePage privatePage;//added
    protected BudgetControl budgetControl;
    private boolean isActive;
    private MainSystem mainSystem;


//Open team and wait for approval
    public Team(String name, TeamOwner teamOwner){
        this.name = name;
        this.teamOwners = new HashSet<>();
        teamOwners.add(teamOwner);
        addObserver(teamOwner);
        this.founder=teamOwner;
        this.isActive=false;
        this.mainSystem= MainSystem.getInstance();
        this.mainSystem.addNotActiveTeam(this);

        this.leaguePerSeason = new HashMap<>();
        this.players = new LinkedHashSet<>();
        this.coach = null;
        this.teamManager = null;
        this.field = null;
        this.budgetControl= new BudgetControl(this);

        //send request
        for (Rfa rfa:mainSystem.getRfas()) {
            addObserver(rfa);
        }
        notifyObservers("request to open new team");

    }

    //just for tests!!!!!1
    public Team(){
        System.out.println("THIS TEAM CONSTRUCTOR IS ONLY FOR TESTS");
        this.name = null;
        this.teamOwners = new HashSet<>();
        this.isActive=false;
        this.mainSystem= MainSystem.getInstance();
        this.mainSystem.addNotActiveTeam(this);
        this.leaguePerSeason = new HashMap<>();
        this.players = new LinkedHashSet<>();
        this.coach = null;
        this.teamManager = null;
        this.field = null;
        this.budgetControl= new BudgetControl(this);


    }


    public void becomeActive(HashSet<Player> players, Coach coach, Field field) throws Exception {
        if(players.size() < 11){
            throw new Exception("The number of players are less than 11");
        }
        this.players= players;
        for (Player player:players) {
            if(player.getTeam() == null){
                player.setPlayerTeam(this);
            }
            else{
                throw new Exception("one of the players team is not null");
            }
        }
        this.coach = coach;
        this.coach.setCoachTeam(this);
        this.field = field;
        this.field.addTeam(this);

        //add team to active list in system
        this.isActive=true;
        this.mainSystem.removeNotActiveTeam(this);
        this.mainSystem.addActiveTeam(this);

        LOG.info(String.format("%s - %s", name, "team was activated"));

    }

    //<editor-fold desc="getters and setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setHome(HashSet<Match> home) {
        this.home = home;
    }

    public void setAway(HashSet<Match> away) {
        this.away = away;
    }

    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void setPlayers(HashSet<Player> players) {
        this.players = players;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void setTeamOwners(HashSet<TeamOwner> teamOwners) {
        this.teamOwners = teamOwners;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public HashSet<Match> getHome() {
        return home;
    }

    public HashSet<Match> getAway() {
        return away;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }


    public HashSet<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }

    public HashSet<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public Field getField() {
        return field;
    }

    public void setLeaguePerSeason(HashMap<Season, League> leaguePerSeason) {
        this.leaguePerSeason = leaguePerSeason;
    }

    public PrivatePage getPrivatePage() {
        return privatePage;
    }

    public void setPrivatePage(PrivatePage privatePage) {
        this.privatePage = privatePage;
    }

    public HashMap<Season, League> getLeaguePerSeason() {
        return leaguePerSeason;
    }

    public TeamOwner getFounder() {
        return founder;
    }

    public void setFounder(TeamOwner founder) {
        this.founder = founder;
    }

    public BudgetControl getBudgetControl() {
        return budgetControl;
    }

    public void setBudgetControl(BudgetControl budgetControl) {
        this.budgetControl = budgetControl;
    }

    //</editor-fold>

    //<editor-fold desc="add and remove functions">
    // adi
    public void addTeamOwner(TeamOwner tO){
        teamOwners.add(tO);
        addObserver(tO);
    }
    // adi
    public void removeTeamOwner(TeamOwner tO)throws Exception{
        if (teamOwners.contains(tO)){
            teamOwners.remove(tO);
        }
        else{
            throw new Exception("TeamOwner doesn't exist in this team");
        }
    }
    // adi
    public void addTeamManager(TeamManager tM){
        teamManager = tM;
    }
    // adi
    public void removeTeamManager(TeamManager tM)throws Exception{

        if (tM.equals(teamManager)) {
            teamManager = null;
        }
        else {
            throw new Exception("This TeamManager doesn't exist in this team");
        }
    }
    // adi
    public void removeCoach(Coach coachToRemove, Coach coachToAdd)throws Exception{
        if (this.coach.equals(coachToRemove)){
            coach = coachToAdd;
        }
        else {
            throw new Exception("This Coach doesn't exist in this team");
        }
    }
    //adi
    public void addPlayer(Player p){
        players.add(p);
    }
    //adi
    public void removePlayer(Player p)throws Exception{
        if(players.contains(p)){
            if(players.size() > 11) {
                players.remove(p);
            }
            else{
                throw new Exception("The team has only 11 or less players");
            }
        }
        else {
            throw new Exception("This Player doesn't exist in this team");
        }
    }
    //adi
    public void removeField(Field f)throws Exception{
        if(field.equals(f)){
            field = null;
        }
        else{
            throw new Exception("This field doesn't exist in this team");
        }
    }
    //</editor-fold>

    //<editor-fold desc="Page Owner Functions">
    /**Or**/
    @Override
    public PrivatePage getPage() {
        return privatePage;
    }

    /**Or**/
    @Override
    public void addRecordToPage(String record) throws Exception {
        this.privatePage.addRecords(record);
    }

    /**Or**/
    @Override
    public void removeRecordFromPage(String record) throws Exception {
        this.privatePage.removeRecord(record);
    }

    /**Or**/
    @Override
    public boolean createPrivatePage() {
        PrivatePage p = new PrivatePage();
        if(this.privatePage==null){// you can have only one page
            this.privatePage=p;
            p.setPageOwner(this);
            return true;
        }
        return false;
    }

    //</editor-fold>

    /**
     * this function connect the team to the current season and current league.
     * The function called only!!!! from Season\League while adding the team
     * and connect them to the teams at current season and current league.
     * @param s- season
     * @param l- league
     */
    public void addLeagueAndSeason(Season s,League l){
        leaguePerSeason.put(s,l);
    }

    /**OR
     * this function checks if the team has this season in the hash map
     * @param seasonYear
     * @return
     */
    public boolean playedInSeason(int seasonYear){
        for (Season s: getLeaguePerSeason().keySet()) {
            if(s.getYear()==seasonYear){
                return true;
            }
        }
        return false;
    }

    /**OR**/
    public void addIncome(String typeOfIncome, long amount) throws Exception {
        this.budgetControl.addIncome(typeOfIncome,amount);
    }

    /**OR**/
    public void addExpense(String typeOfExpense, long amount) throws Exception {
        this.budgetControl.addExpense(typeOfExpense,amount);
    }

    /**Or**/
    public boolean isActive(){
        return isActive;
    }

    /**Or**/
    public void deleteTeamByTeamOwner() {
        for (Player p:players) {
            p.setPlayerTeam(null);
        }
        coach.setCoachTeam(null);
        field.removeTeam(this);
        if(privatePage!=null){
            privatePage.setPageOwner(null);
            privatePage=null;
        }
        if(teamManager != null){
            teamManager.setTeam(null);
            teamManager.getTeamRole().deleteTeamManager();
        }
        isActive=false;
        mainSystem.removeActiveTeam(this);
        LOG.info(String.format("%s - %s", name, "team was deleted by team owner"));
    }
}
