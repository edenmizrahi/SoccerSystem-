import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private StubExternalSystem extSystem;
    private HashSet<Team> activeTeams;

//    public static final String pattern = "dd-M-yyyy hh:mm:ss";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    public static final SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final Logger LOG = LogManager.getLogger();
    private static MainSystem mainSystem_instance= null;


    private MainSystem() {
        this.complaints = new LinkedList<>();
        this.leagues = new LinkedList<>();
        this.users = new LinkedList<>();
        this.seasons= new LinkedList<>();
        this.currSeason=null;
        this.extSystem=new StubExternalSystem();
        activeTeams=new HashSet<>();

    }

    public static MainSystem getInstance(){
        if(mainSystem_instance==null){
            mainSystem_instance= new MainSystem();
        }
        return mainSystem_instance;
    }

    /**OR**/
    public LinkedList<Fan> getAllFans(){
        LinkedList<Fan> ans= new LinkedList<>();
        for (User user:users) {
            if(user instanceof  Fan){
                ans.add((Fan)user);
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

    /**OR**/
    public List<TeamRole> getTeamRoles(){
        List<TeamRole> teamroles= new LinkedList<>();
        for (User user:users) {
            if(user instanceof  TeamRole){
                teamroles.add((TeamRole)user);
            }
        }
        return teamroles;
    }

    /**OR**/
    public List<Player> getAllPlayer(){
        List<Player> players= new LinkedList<>();
        for(TeamRole teamRole: getTeamRoles()){
            if(teamRole.isPlayer()){
                players.add(teamRole.getPlayer());
            }
        }
        return players;
    }

    /**OR**/
    public List<Coach> getAllCoach(){
        List<Coach> coaches= new LinkedList<>();
        for(TeamRole teamRole: getTeamRoles()){
            if(teamRole.isCoach()){
                coaches.add(teamRole.getCoach());
            }
        }
        return coaches;
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

    //or
    public boolean addActiveTeam(Team team){
        if(! team.isActive() || activeTeams.contains(team)){
            return false;
        }
        activeTeams.add(team);
        return true;
    }


    //or
    public boolean removeActiveTeam(Team team){
        if(! activeTeams.contains(team)){
            return false;
        }
        activeTeams.remove(team);
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

    public StubExternalSystem getExtSystem() {
        return extSystem;
    }

    public void setExtSystem(StubExternalSystem extSystem) {
        this.extSystem = extSystem;
    }

    public HashSet<Team> getActiveTeams() {
        return activeTeams;
    }

    public void setActiveTeams(HashSet<Team> activeTeams) {
        this.activeTeams = activeTeams;
    }


    //</editor-fold>

    /**OR**/
    public void firstStartSystem() throws ParseException {

        //create user for system manager
        SystemManager defultSM= new SystemManager(this,"Defult system Manager","0541234567","defult@google.com","systemManager","systemManager101",MainSystem.birthDateFormat.parse("01-01-2000"));
        //link external systems....
        extSystem.connectToSystem(this);
        LOG.info(String.format("%s - %s", this.getClass(), "system was started for the first time"));
    }

    /**OR**/
    public void startSystem() throws ParseException {
        //read things from DB.....
        if(users.size()==0){
            firstStartSystem();
        }else{
            //link external systems....
            extSystem.connectToSystem(this);
            LOG.info(String.format("%s - %s", this.getClass(), "system was started"));
        }

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
