import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class League {

    private String name;
    private MainSystem mainSystem; //1
    private HashMap<Season,Policy> seasonsWithPolicy; // 1..*
    private static final Logger LOG = LogManager.getLogger();
    /**
     * hold the teams in this league and in specific Seasons
     */
    private HashMap<Season, HashSet<Team>> teamsInSeason;

    public League(String name, MainSystem mainSystem, HashMap<Season, Policy> seasonsWithPolicy, Season currSeason) {
        this.name = name;
        teamsInSeason=new HashMap<>();
        this.mainSystem = mainSystem;
        this.seasonsWithPolicy = seasonsWithPolicy;

        mainSystem.addLeague(this);

    }

    public League(String name, MainSystem mainSystem) throws Exception {
        if (name != null && mainSystem != null) {
            this.name = name;
            this.mainSystem = mainSystem;
            teamsInSeason = new HashMap<>();
            this.seasonsWithPolicy = seasonsWithPolicy;

            mainSystem.addLeague(this);
        }
        else{
            throw new Exception("Please insert valid details in order to create the new League properly");
        }
    }

    //<editor-fold desc="getter ans setters">
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

    public void setTeamsInSeason(HashMap<Season, HashSet<Team>> teamsInSeason) {
        this.teamsInSeason = teamsInSeason;
    }

    //</editor-fold>

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