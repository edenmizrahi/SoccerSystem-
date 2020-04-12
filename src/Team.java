import java.util.HashSet;

public class Team implements PageOwner{

    private String name;
    private int budget;
    private HashSet<Match> home;
    private HashSet<Match> away;
    private TeamManager teamManager;
    private League league;

    /**I think between 1..* there is no team without players.. **/
    private HashSet<Player> players;
    private Coach coach;
    private HashSet<TeamOwner> teamOwners;
    private Field field;

    private PrivatePage privatePage;//added

    public Team(String name, int budget, League league, HashSet<Player> players, Coach coach, Field field) throws Exception {
       if(players.size() < 11){
           throw new Exception();
       }
        this.name = name;
        this.budget = budget;
        this.league = league;
        this.players = players;
        this.coach = coach;
        this.teamManager = null;
        this.teamOwners = new HashSet<>();
        this.field = field;
    }
    //added just for unitTests, adi
    public Team(){
        teamOwners = new HashSet<>();
    }


    //<editor-fold desc="getters and setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

    public void setLeague(League league) {
        this.league = league;
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

    public int getBudget() {
        return budget;
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

    public League getLeague() {
        return league;
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
    //</editor-fold>
    // adi
    public void addTeamOwner(TeamOwner tO){
        teamOwners.add(tO);
    }
    // adi
    public void removeTeamOwner(TeamOwner tO){
        teamOwners.remove(tO);
    }
    // adi
    public void addTeamManager(TeamManager tM){
        teamManager = tM;
    }
    // adi
    public void removeTeamManager(TeamManager tM){
        teamManager = null;
    }
    @Override
    public void openPage() {

    }

    @Override
    public void managePage() {

    }

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

    @Override
    public PrivatePage getPage() {
        return privatePage;
    }

}
