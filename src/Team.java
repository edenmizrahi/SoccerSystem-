import java.util.HashSet;

public class Team {

    private String name;
    private int budget;
    private HashSet<Match> home;
    private HashSet<Match> away;
    private TeamManager teamManager;
    private League league;
    /**at diagram must hold private page , is it ?I dont think so.. **/
    private PrivatePage privatePage;
    /**I think between 1..* there is no team without players.. **/
    private HashSet<Player> players;
    private  Coach coach;
    private  HashSet<TeamOwner> teamOwners;
    private Field field;


    public Team(String name, int budget, TeamManager teamManager, League league, HashSet<Player> players, Coach coach, HashSet<TeamOwner> teamOwners, Field field) {
        this.name = name;
        this.budget = budget;
        this.teamManager = teamManager;
        this.league = league;
        this.players = players;
        this.coach = coach;
        this.teamOwners = teamOwners;
        this.field = field;
    }


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

    public void setPrivatePage(PrivatePage privatePage) {
        this.privatePage = privatePage;
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

    public PrivatePage getPrivatePage() {
        return privatePage;
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
}
