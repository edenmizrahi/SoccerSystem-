package Domain.LeagueManagment;

import Domain.MainSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;

public class League {

    private String name;
    private MainSystem mainSystem; //1

    private static final Logger LOG = LogManager.getLogger();
    /**
     * hold the teams in this league and in specific Seasons
     */
    private HashMap<Season, HashSet<Team>> teamsInSeason;

    public League(String name, MainSystem mainSystem, Season currSeason) throws Exception {
        this.name = name;
        teamsInSeason=new HashMap<>();
        this.mainSystem = mainSystem;
        if(! mainSystem.addLeague(this)){
            throw new Exception("There is already league with the same name");
        }
    }

    public League(String name, MainSystem mainSystem) throws Exception {
        if (name != null && mainSystem != null) {
            this.name = name;
            this.mainSystem = mainSystem;
            teamsInSeason = new HashMap<>();
            //if cannot ad the league, there is league with the same name
            if(! mainSystem.addLeague(this)){
                throw new Exception("There is already league with the same name");
            }
        }
        else{
            throw new Exception("Please insert valid details in order to create the new Domain.LeagueManagment.League properly");
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

    public void setTeamsInSeason(HashMap<Season, HashSet<Team>> teamsInSeason) {
        this.teamsInSeason = teamsInSeason;
    }

    //</editor-fold>

    /**
     * Add teams by Domain.LeagueManagment.Season to this league.
     * also add to input season the input league with the input teams.
     * @param season
     * @param teams teams to add to the current league with the league
     * @codeBy Eden
     */

    //TODO test
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