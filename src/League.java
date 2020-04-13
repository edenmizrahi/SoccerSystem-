import java.util.HashMap;
import java.util.HashSet;

public class League {

    private String name;
    private MainSystem mainSystem; //1
    private HashMap<Season,Policy> seasonsWithPolicy; // 1..*
//    private List<Team> teams;//aggregation (?)


    /**
     * hold the teams in this league and in specific Seasons
     */
    private HashMap<Season, HashSet<Team>> teamsInSeason;


    public League(String name, MainSystem mainSystem, HashMap<Season, Policy> seasonsWithPolicy) {
        this.name = name;
        teamsInSeason=new HashMap<>();
        this.mainSystem = mainSystem;
        this.seasonsWithPolicy = seasonsWithPolicy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainSystem getMainSystem() {
        return mainSystem;
    }

    public void setMainSystem(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
    }

    public HashMap<Season, Policy> getSeasonsWithPolicy() {
        return seasonsWithPolicy;
    }

    public void setSeasonsWithPolicy(HashMap<Season, Policy> seasonsWithPolicy) {
        this.seasonsWithPolicy = seasonsWithPolicy;
    }

    /**
     * Add teams by Season to this league.
     * also add to input season the input league with the input teams.
     * @param season
     * @param teams teams to add to the current league with the league
     * @codeBy Eden
     */
    public void addSeasonWithTeams(Season season, HashSet<Team> teams) {
        /**check if season already exist**/
        if(!teamsInSeason.containsKey(season)) {
            teamsInSeason.put(season, teams);
            season.addLeagueWithTeams(this,teams);

        }
        else{
            /**if season already exist- check is it already hold the input teams*/
            if(!teamsInSeason.get(season).equals(teams)) {
                teams.addAll(teamsInSeason.get(season));
                teamsInSeason.put(season, teams);
                season.addLeagueWithTeams(this,teams);

            }
        }

    }
    public HashMap<Season, HashSet<Team>> getTeamsInSeason() {
        return teamsInSeason;
    }

}
