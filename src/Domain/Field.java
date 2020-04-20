package Domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class Field {

    private String nameOfField;
    private HashSet<Match> matches;
    private HashSet<Team> teams;
    private static final Logger LOG = LogManager.getLogger();
    /******possible attribute*********/
    //private boolean isFree;

    public Field(String nameOfField, HashSet<Match> matches, HashSet<Team> teams) {
        this.nameOfField = nameOfField;
        this.matches = matches;
        this.teams = teams;
    }

    public Field(String nameOfField) {
        this.nameOfField = nameOfField;
        matches = new HashSet<>();
        teams = new HashSet<>();
    }

    //<editor-fold desc="getters and setters">
    public String getNameOfField() { return nameOfField; }

    public HashSet<Match> getMatches() { return matches; }

    public HashSet<Team> getTeams() { return teams; }

    public void setNameOfField(String nameOfField) { this.nameOfField = nameOfField; }

    public void setMatches(HashSet<Match> matches) { this.matches = matches; }

    public void setTeams(HashSet<Team> teams) { this.teams = teams; }

    public void addTeam(Team team){
        teams.add(team);
    }
    public void removeTeam(Team team){
        if(teams.contains(team)){
            teams.remove(team);
        }
    }
    //</editor-fold>

}
