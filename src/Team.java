import org.apache.logging.log4j.LogManager;

import java.util.*;

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
    private HashSet<Player> players;
    private Coach coach;
    private HashSet<TeamOwner> teamOwners;
    private Field field;
    private PrivatePage privatePage;//added
    protected BudgetControl budgetControl;
    private boolean isActive;
    private MainSystem mainSystem;
    private int score;


//Open team and wait for approval
    public Team(String name, TeamOwner teamOwner){
        this.name = name;
        this.teamOwners = new HashSet<>();
        teamOwners.add(teamOwner);
        addObserver(teamOwner);
        this.founder=teamOwner;
        this.isActive=false;
        this.mainSystem= MainSystem.getInstance();

        this.leaguePerSeason = new HashMap<>();
        this.players = new LinkedHashSet<>();
        this.coach = null;
        this.teamManager = null;
        this.field = null;
        this.budgetControl= new BudgetControl(this);
        this.score = 0;
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
        this.leaguePerSeason = new HashMap<>();
        this.players = new LinkedHashSet<>();
        this.coach = null;
        this.teamManager = null;
        this.field = null;
        this.budgetControl= new BudgetControl(this);


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
    //adi
    public void addCoach(Coach c){
        if(this.coach == null && c != null){
            coach = c;
        }
    }
    // adi
    public void removeCoach(Coach coachToRemove)throws Exception{
        if (this.coach.equals(coachToRemove)){
            coach = null;
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

    /**OR
     * add income to budget control
     * @param typeOfIncome
     * @param amount
     * @throws Exception
     */
    public void addIncome(String typeOfIncome, long amount) throws Exception {
        this.budgetControl.addIncome(typeOfIncome,amount);
    }

    /**Or
     * add expense in budget control
     * @param typeOfExpense
     * @param amount
     * @throws Exception
     */
    public void addExpense(String typeOfExpense, long amount) throws Exception {
        this.budgetControl.addExpense(typeOfExpense,amount);
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**Or**/
    public boolean isActive(){
        return isActive;
    }

    /**Or
     * when team is deleted by team owner
     * delete the connections between player to team.
     */
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
        founder=null;

        //remove the subscriptions of all team owners
        for (TeamOwner teamOwner:teamOwners) {
            Iterator<TeamSubscription> iter= teamOwner.getMySubscriptions().iterator();
            TeamSubscription sub;
            while (iter.hasNext()){
                sub=iter.next();
                if(sub.team.equals(this)){
                    teamOwner.getMySubscriptions().remove(sub);
                }
            }
            //remove the team from activeTeams and move to deletedTeams
            teamOwner.getTeams().remove(this);
            teamOwner.getDeletedTeams().add(this);
        }

        LOG.info(String.format("%s - %s", name, "team was deleted by team owner"));
    }

    /**OR
     * team becomes active
     * @param players
     * @param coach
     * @param field
     * @throws Exception
     */
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
        this.mainSystem.addActiveTeam(this);

        LOG.info(String.format("%s - %s", name, "team was activated"));

    }

    /**OR
     * the team owner calls this function whem he want to reOpen the team
     * he is the only team owner now
     * @param players- at least 11
     * @param coach
     * @param field
     * @param newFounder- send himself
     * @throws Exception
     */
    public void reopenTeam(HashSet<Player> players, Coach coach, Field field, TeamOwner newFounder) throws Exception {
        this.founder=newFounder;

        becomeActive(players,coach,field);


        //remove all the other team owners
        Iterator<TeamOwner> iter= teamOwners.iterator();
        TeamOwner teamOwner;
        while (iter.hasNext()){
            teamOwner= iter.next();
            if(! teamOwner.equals(newFounder)){
                teamOwner.getDeletedTeams().remove(this);
                teamOwners.remove(teamOwner);
            }

        }
        LOG.info(String.format("%s - %s", name, "team was re-opened"));
    }


    public void addMatchToHomeMatch(Match match){
        this.getHome().add(match);
    }

    public void addMatchToAwayMatch(Match match){
        this.getAway().add(match);
    }
}
