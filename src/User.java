import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User {
    protected MainSystem system;
    protected HashSet<Permission> permissions;
    private static final Logger LOG = LogManager.getLogger();


    public User(MainSystem ms){
        system = ms;
        permissions = new HashSet<>();
        system.addUser(this);
    }

    public void addPermission(Permission per){
        permissions.add(per);
    }
    public void addPermissions(HashSet<Permission> pers){
        permissions.addAll(pers);
    }

    //<editor-fold desc="getters and setters">
    public MainSystem getSystem() {
        return system;
    }

    public void setSystem(MainSystem system) {
        this.system = system;
    }

    public HashSet<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }
    //</editor-fold>

    //<editor-fold desc="Search Functions">
    //or
    //the user chooses to search by name and enters the name in the text box
    public LinkedHashSet<PrivatePage> searchByName(String name){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (User user:system.getUsers()) {
            if(user instanceof PageOwner){
                if(((PageOwner)user).getPage() != null){
                    if(user instanceof Subscription && ((Subscription)user).getName().contains(name)){
                            ans.add(((PageOwner)user).getPage());
                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by key word and enters the words in the text box
    public LinkedHashSet<PrivatePage> searchByKeyWord(String keyWord){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        PageOwner curr;
        for (User user:system.getUsers()) {
            if(user instanceof PageOwner){// get only the page owners
                curr=(PageOwner)user;
                if( curr.getPage() != null){// check if they have a page
                    if(curr.getPage().getRecordsAsString().contains(keyWord)){
                            ans.add(curr.getPage());
                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by league and chooses the league name from the options
    public LinkedHashSet<PrivatePage> searchByLeague(String leagueName){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (League l:system.getLeagues()) {
            if(l.getName().equals(leagueName)){
                for (Season s:l.getTeamsInSeason().keySet()) {
                    if (s.equals(l.getCurrSeason())) {// get only the current season
                        for (Team t : l.getTeamsInSeason().get(s)) {
                            ans.addAll(getPrivatePagefromTeam(t));
                        }
                    }
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search by season and chooses the season year from the options
    public LinkedHashSet<PrivatePage> searchBySeason(int seasonYear){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (Season s:system.getSeasons()) {
            if(s.getYear()== seasonYear){
                for (HashSet<Team> hashT:s.getTeamsInCurrentSeasonleagues().values()) {
                    for (Team t:hashT) {
                        ans.addAll(getPrivatePagefromTeam(t));
                    }
                }
            }

        }
        return ans;
    }

    //or
    //the user chooses to search by team and chooses the team name from the options
    public LinkedHashSet<PrivatePage> searchByTeamName(String teamName){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            for (Team t:system.getTeams()) {
               if(t.getName().equals(teamName)){
                   ans.addAll(getPrivatePagefromTeam(t));
               }
            }
        return ans;
    }


    //or - private func helps with search
    private LinkedHashSet<PrivatePage> getPrivatePagefromTeam(Team t){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            //go through all the players
            for(Player p:t.getPlayers()){
                if(p.getPage() != null){
                        ans.add(p.getPage());
                }
            }
            // coach page
            if(t.getCoach().getPage() != null){
                ans.add(t.getCoach().getPage());
            }
            //team's page
            if(t.getPage() != null){
                ans.add(t.getPage());
            }
        return ans;
    }

    //or
    //the user chooses to search all players , returns all the player that have pages
    public LinkedHashSet<PrivatePage> searchByPlayer(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (User user:system.getUsers()) {
            if(user instanceof  Player){
                if(((Player)user).getPage() != null){
                    ans.add(((Player)user).getPage());
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search all coaches , returns all the coaches who have pages
    public LinkedHashSet<PrivatePage> searchByCoach(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (User user:system.getUsers()) {
            if(user instanceof  Coach){
                if(((Coach)user).getPage() != null){
                        ans.add(((Coach)user).getPage());
                }
            }
        }
        return ans;
    }

    //or
    //the user chooses to search all teams , returns all the teams who have pages
    public LinkedHashSet<PrivatePage> searchByTeam(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            for (Team t:system.getTeams()) {
                if( t.getPage() != null ){
                    ans.add(t.getPage());
                }
            }
        return ans;
    }

    //</editor-fold>

    //<editor-fold desc="Sign in Functions">
    /**OR**/
    //if you sign in as team manager or team player
    public boolean signInAsSub(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Subscription newSub= new Subscription(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            system.addUser(newSub);
            return true;
        }
        return false;
    }

    /**OR**/
    public boolean signInAsPlayer(String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Player newPlayer= new Player(system,name,phoneNumber,email,userName,password,dateOfBirth);
            system.removeUser(this);
            system.addUser(newPlayer);
            return true;
        }
        return false;
    }

    /**OR**/
    public boolean signInAsCoach(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Coach newCoach= new Coach(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            system.addUser(newCoach);
            return true;
        }
        return false;
    }

    /**OR**/
    /*
    public boolean signInAsReferee(String name, String phoneNumber, String email, String userName, String password, String qualification){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Referee newRef= new Referee(system,name,phoneNumber,email,userName,password,qualification);
            system.removeUser(this);
            system.addUser(newRef);
            return true;
        }
        return false;
    }
*/
    /**OR**/
    public boolean signInAsFan(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Fan newFan= new Fan(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            system.addUser(newFan);
            return true;
        }
        return false;
    }
    /**OR**/
    public boolean signInAsSystemManager(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            SystemManager newSM= new SystemManager(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            system.addUser(newSM);
            return true;
        }
        return false;
    }

    /**OR**/
    public boolean signInAsRFA(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber)){
            Rfa newRFA= new Rfa(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            system.addUser(newRFA);
            return true;
        }
        return false;
    }



    public boolean checkValidDetails(String userName, String password, String phoneNumber){
        //check that username in unique
        for (User user:system.getUsers()) {
            if(user instanceof Subscription){
                if(((Subscription)user).getUserName().equals(userName)){
                    return false;
                }
            }
        }
        //password length is 6 or more
        if(password.length()<6){
            return false;
        }
        // phone number is 10 digits
        if( !phoneNumber.matches("^[0-9]*$") || phoneNumber.length()!=10){
            return false;
        }
        return true;
    }
    //</editor-fold>

    /**OR*
     * this function return the user that the userName and password are correct
     * null if there is no user that fits
     * @param userName
     * @param password
     * @return
     */
    public User logIn(String userName, String password){
        for (User user:system.getUsers()) {
            if(user instanceof Subscription){
                if(((Subscription)user).getUserName().equals(userName) && ((Subscription)user).getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }


    /**OR**/
    public void logOut(){
        //what to do here?!?!!??!
    }
}
