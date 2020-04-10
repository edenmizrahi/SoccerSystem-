import java.util.HashSet;

public class Field {

    private String nameOfField;
    private HashSet<Match> matches;
    private HashSet<Team> teams;
    /******possible attribute*********/
    //private boolean isFree;

    public Field(String nameOfField, HashSet<Match> matches, HashSet<Team> teams) {
        this.nameOfField = nameOfField;
        this.matches = matches;
        this.teams = teams;
    }

    public Field(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public String getNameOfField() { return nameOfField; }

    public HashSet<Match> getMatches() { return matches; }

    public HashSet<Team> getTeams() { return teams; }

    public void setNameOfField(String nameOfField) { this.nameOfField = nameOfField; }

    public void setMatches(HashSet<Match> matches) { this.matches = matches; }

    public void setTeams(HashSet<Team> teams) { this.teams = teams; }

}
