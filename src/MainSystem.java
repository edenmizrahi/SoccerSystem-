import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MainSystem {

    private LinkedList<Complaint> complaints;// *
    private LinkedList<League> leagues;//*
    private LinkedList<User> users;//*
    private LinkedList<Season> seasons;//*
    private Season currSeason;

    private static final Logger LOG = LogManager.getLogger();


    public MainSystem(SystemManager sm) {
        this.complaints = new LinkedList<>();
        users.add(sm);
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
        this.seasons= new LinkedList<>();
        currSeason=null;
    }
    public MainSystem() {
        this.complaints = new LinkedList<>();
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
        this.seasons= new LinkedList<>();
        this.currSeason=null;
    }

    /**OR**/
    public LinkedList<Subscription> getSubscriptions(){
        LinkedList<Subscription> ans= new LinkedList<>();
        for (User user:users) {
            if(user instanceof  Subscription){
                ans.add((Subscription)user);
            }
        }
        return ans;
    }

    public List<SystemManager> getSystemManagers() {
        List<SystemManager> res=new LinkedList<>();
        for(User u: users){
            if(u instanceof SystemManager){
                res.add(((SystemManager)u));
            }
        }
        return res;
    }

    public List<Rfa> getRfas() {
        List<Rfa> res=new LinkedList<>();
        for(User u: users){
            if(u instanceof Rfa){
                res.add(((Rfa)u));
            }
        }
        return res;
    }

    public HashSet<Team> getAllTeams(){
        HashSet<Team> allTeams= new HashSet<>();
        for (League l:leagues) {
            for (HashSet<Team> teamsInSeason:l.getTeamsInSeason().values()) {
                allTeams.addAll(teamsInSeason);
            }
        }
        return allTeams;
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
    public boolean removeLeague(League l){
        if (leagues.contains(l)){
            leagues.remove(l);
            return true;
        }
        return false;
    }
    // or
    public boolean addLeague(League l){
        if (leagues.contains(l)){
            return false;
        }
        leagues.add(l);
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



    public Season getCurrSeason() {
        return currSeason;
    }

    public void setCurrSeason(Season currSeason) {
        this.currSeason = currSeason;
    }

    //</editor-fold>

    //or- not done
    public void startSystem(){
        //create user for system manager
        SystemManager defultSM= new SystemManager(this,"Defult system Manager","0541234567","defult@google.com","systemManager","systemManager101");
        users.add(defultSM);
        //link external systems....
        //read from the external DB
        LOG.info(String.format("%s - %s", this.getClass(), "system was started"));
    }

    /**
     * check how many RFA users at system
     * @return number of RFA
     * @codeBy Eden
     */
    public int numOfRfa(){
        int sum=0;
        for(User u :users){
            if(u instanceof Rfa){
               sum++;
            }
        }
        return sum;
    }




}
