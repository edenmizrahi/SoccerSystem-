import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainSystem {

    private LinkedList<Complaint> complaints;// *
    private LinkedList<SystemManager> systemManagers; // 1..*
    private LinkedList<League> leagues;//*
    private LinkedList<User> users;//*
    private LinkedList<Season> seasons;//*
    private LinkedList<Team> teams; // *
    private static final Logger LOG = LogManager.getLogger();


    public MainSystem(SystemManager sm) {
        this.complaints = new LinkedList<>();
        this.systemManagers = new LinkedList<>();
        systemManagers.add(sm);
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
        this.seasons= new LinkedList<>();
        this.teams= new LinkedList<>();
    }
    public MainSystem() {
        this.complaints = new LinkedList<>();
        this.systemManagers = new LinkedList<>();
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
        this.seasons= new LinkedList<>();
        this.teams= new LinkedList<>();
    }

    //<editor-fold desc="add and remove from lists">

    // adi
    public boolean removeUser(User user){
        if (users.contains(user)){
            users.remove(user);
            return true;
        }
        return false;
    }
    // adi
    public boolean addUser(User user){
        if (users.contains(user)){
            return false;
        }
        users.add(user);
        return true;
    }

    // or
    public boolean removeTeam(Team team){
        if (teams.contains(team)){
            teams.remove(team);
            return true;
        }
        return false;
    }
    // or
    public boolean addTeam(Team team){
        if (teams.contains(team)){
            return false;
        }
        teams.add(team);
        return true;
    }

    // adi
    public boolean removeSeason(Season s){
        if (seasons.contains(s)){
            seasons.remove(s);
            return true;
        }
        return false;
    }
    // adi
    public boolean addSeason(Season s){
        if (seasons.contains(s)){
            return false;
        }
        seasons.add(s);
        return true;
    }

    //</editor-fold>


    //<editor-fold desc="getters and setters">
    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(LinkedList<Complaint> complaints) {
        this.complaints = complaints;
    }

    public List<SystemManager> getSystemManagers() {
        return systemManagers;
    }

    public void setSystemManagers(LinkedList<SystemManager> systemManagers) {
        this.systemManagers = systemManagers;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(LinkedList<League> leagues) {
        this.leagues = leagues;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public LinkedList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(LinkedList<Season> seasons) {
        this.seasons = seasons;
    }

    public LinkedList<Team> getTeams() {
        return teams;
    }

    public void setTeams(LinkedList<Team> teams) {
        this.teams = teams;
    }

    //</editor-fold>

    //or- not done
    public void startSystem(SystemManager systemManager){
        //sign in to system????
        this.systemManagers.add(systemManager);
        //link external systems
        //read from the external DB
        System.out.println("The system was started correctly!");
    }




}
