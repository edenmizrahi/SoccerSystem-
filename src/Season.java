

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class Season {

    private HashMap<League, HashSet<Team >> teamsInCurrentSeasonLeagues;
    private SchedulingPolicy schedulingPolicy;
    private CalculationPolicy calculationPolicy;
    private MainSystem mainSystem;
    private int year;
    private static final Logger LOG = LogManager.getLogger();

    public Season( MainSystem ms, SchedulingPolicy schedule, CalculationPolicy calculate, int year) {
        this.schedulingPolicy = schedule;
        this.calculationPolicy = calculate;
        this.year = year;
        this.teamsInCurrentSeasonLeagues=new HashMap<>();
        this.mainSystem=ms;
        mainSystem.addSeason(this);
    }

    /**
     * Add teams by league to this season.
     * also add to the input league this season with the input teams.
     * also connect the teams .
     * @param l league
     * @param teams teams to add to the current season with the league
     * @codeBy Eden
     */
    public void addLeagueWithTeams(League l, HashSet <Team> teams){
        boolean teamsAdded=false;
        /**check if league already exist**/
        if(!teamsInCurrentSeasonLeagues.containsKey(l)) {
            teamsInCurrentSeasonLeagues.put(l, teams);
            l.addSeasonWithTeams(this,teams);
            teamsAdded=true;
        }
        else{
            /**if league already exist- check is it already hold the input teams*/
            if(!teamsInCurrentSeasonLeagues.get(l).equals(teams))
                teams.addAll(teamsInCurrentSeasonLeagues.get(l));
                teamsInCurrentSeasonLeagues.put(l, teams);
                l.addSeasonWithTeams(this,teams);
                teamsAdded=true;
        }
        /**connect the teams to the current league and season*/
        if(teamsAdded) {
            Iterator<Team> iter = teams.iterator();
            while (iter.hasNext())
                ((Team) iter.next()).addLeagueAndSeason(this, l);
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MainSystem getMainSystem() {
        return mainSystem;
    }

    public void setMainSystem(MainSystem mainSystem) {
        this.mainSystem = mainSystem;
    }

    public HashMap<League, HashSet<Team>> getTeamsInCurrentSeasonLeagues() {
        return teamsInCurrentSeasonLeagues;
    }

    public void setTeamsInCurrentSeesonleagus(HashMap<League, HashSet<Team>> teamsInCurrentSeasonLeagues) {
        this.teamsInCurrentSeasonLeagues = teamsInCurrentSeasonLeagues;
    }

    public SchedulingPolicy getSchedulingPolicy() { return schedulingPolicy; }

    public void setSchedulingPolicy(SchedulingPolicy schedulingPolicy) { this.schedulingPolicy = schedulingPolicy; }

    public CalculationPolicy getCalculationPolicy() { return calculationPolicy; }

    public void setCalculationPolicy(CalculationPolicy calculationPolicy) { this.calculationPolicy = calculationPolicy; }
}



