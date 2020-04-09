

import java.util.HashMap;

public class Season {

    private HashMap<League,Policy> leagueWithPolicy;
    private int year;

    public Season(HashMap<League, Policy> leagueWithPolicy, int year) {
        this.leagueWithPolicy = leagueWithPolicy;
        this.year = year;
    }

    public HashMap<League, Policy> getLeagueWithPolicy() {
        return leagueWithPolicy;
    }

    public void setLeagueWithPolicy(HashMap<League, Policy> leagueWithPolicy) {
        this.leagueWithPolicy = leagueWithPolicy;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
