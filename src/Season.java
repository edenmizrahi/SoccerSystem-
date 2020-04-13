

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Season {
    private HashMap<League, HashSet<Team >> teamsInCurrentSeesonleagus;
    private Policy policy;

    private MainSystem mainSystem;
    private int year;

    public Season( Policy p, int year) {
        this.policy = p;
        this.year = year;
        teamsInCurrentSeesonleagus=new HashMap<>();
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
        if(!teamsInCurrentSeesonleagus.containsKey(l)) {
            teamsInCurrentSeesonleagus.put(l, teams);
            l.addSeasonWithTeams(this,teams);
            teamsAdded=true;
        }
        else{
            /**if league already exist- check is it already hold the input teams*/
            if(!teamsInCurrentSeesonleagus.get(l).equals(teams))
             teams.addAll(teamsInCurrentSeesonleagus.get(l));
             teamsInCurrentSeesonleagus.put(l, teams);
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

    public HashMap<League, HashSet<Team>> getTeamsInCurrentSeesonleagus() {
        return teamsInCurrentSeesonleagus;
    }

    public void setTeamsInCurrentSeesonleagus(HashMap<League, HashSet<Team>> teamsInCurrentSeesonleagus) {
        this.teamsInCurrentSeesonleagus = teamsInCurrentSeesonleagus;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }


}
