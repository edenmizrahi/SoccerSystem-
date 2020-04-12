import java.util.HashMap;
import java.util.List;

public class League {

    private String name;
    private MainSystem mainSystem; //1
    private HashMap<Season,Policy> seasonsWithPolicy; // 1..*
    private List<Team> teams;//aggregation (?)

    public League(String name, MainSystem mainSystem, HashMap<Season, Policy> seasonsWithPolicy, List<Team> teams) {
        this.name = name;
        this.mainSystem = mainSystem;
        this.seasonsWithPolicy = seasonsWithPolicy;
        this.teams = teams;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


}
