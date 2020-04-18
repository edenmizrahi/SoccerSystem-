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
    public LinkedHashSet<PrivatePage> searchByName(String name) throws Exception {
        if(name==null){
            throw new Exception("name null");
        }
        if(name.length()==0){
            throw new Exception("name empty");
        }
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (User user:system.getUsers()) {
            if(user instanceof PageOwner){
                if(((PageOwner)user).getPage() != null){
                    if(user instanceof Fan && ((Fan)user).getName().contains(name)){
                            ans.add(((PageOwner)user).getPage());
                    }
                }
            }
        }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Name: "+name);
        }
        writeToLOG("searched by name: "+name);
        return ans;
    }

    //or
    //the user chooses to search by key word and enters the words in the text box
    public LinkedHashSet<PrivatePage> searchByKeyWord(String keyWord) throws Exception {
        if(keyWord==null){
            throw new Exception("keyWord null");
        }
        if(keyWord.length()==0){
            throw new Exception("keyWord empty");
        }
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

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("KeyWord: "+keyWord);
        }

        writeToLOG("searched by key word: "+keyWord);
        return ans;
    }

    //or
    //the user chooses to search by league and chooses the league name from the options
    public LinkedHashSet<PrivatePage> searchByLeague(String leagueName) throws Exception {
        if(leagueName==null){
            throw new Exception("leagueName null");
        }
        if(leagueName.length()==0){
            throw new Exception("leagueName empty");
        }
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (League l:system.getLeagues()) {
            if(l.getName().equals(leagueName)){
                for (Season s:l.getTeamsInSeason().keySet()) {
                    if (s.equals(system.getCurrSeason())) {// get only the current season
                        for (Team t : l.getTeamsInSeason().get(s)) {
                            ans.addAll(getPrivatePageforTeam(t));
                        }
                    }
                }
            }
        }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("League: "+leagueName);
        }

        writeToLOG("searched league name: "+leagueName);
        return ans;
    }

    //or
    //the user chooses to search by season and chooses the season year from the options
    public LinkedHashSet<PrivatePage> searchBySeason(int seasonYear) throws Exception {
        if(seasonYear<1970 || seasonYear>2021){// not a year
            throw new Exception("year not valid");
        }
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (Season s:system.getSeasons()) {
            if(s.getYear()== seasonYear){
                for (HashSet<Team> hashT:s.getTeamsInCurrentSeasonleagues().values()) {
                    for (Team t:hashT) {
                        ans.addAll(getPrivatePageforTeam(t));
                    }
                }
            }

        }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Season: "+seasonYear);
        }

        writeToLOG("searched season year: "+seasonYear);
        return ans;
    }

    //or
    //the user chooses to search by team and chooses the team name from the options
    public LinkedHashSet<PrivatePage> searchByTeamName(String teamName) throws Exception {
        if(teamName==null){
            throw new Exception("teamName null");
        }
        if(teamName.length()==0){
            throw new Exception("teamName empty");
        }
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            for (Team t:system.getAllTeams()) {
               if(t.getName().contains(teamName)){
                   ans.addAll(getPrivatePageforTeam(t));
               }
            }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Team Name: "+teamName);
        }

        writeToLOG("searched team name: "+teamName);
        return ans;
    }


    //or - private func helps with search
    private LinkedHashSet<PrivatePage> getPrivatePageforTeam(Team t){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            //go through all the players
        if(t.getPlayers()!= null) {
            for (Player p : t.getPlayers()) {
                if (p.getPage() != null) {
                    if (!ans.contains(p.getPage())) {// no duplicates
                        ans.add(p.getPage());
                    }
                }
            }
        }
        if(t.getCoach()!= null) {
            // coach page
            if (t.getCoach().getPage() != null) {
                ans.add(t.getCoach().getPage());
            }
        }
            //team's page
            if(t.getPage() != null){
                ans.add(t.getPage());
            }
        return ans;

    }

    //or
    //the user chooses to search all players , returns all the player that have pages
    public LinkedHashSet<PrivatePage> searchAllPlayers(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (Player player:system.getAllPlayer()) {
            if(player.getPage() != null){
                ans.add(player.getPage());
            }
        }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Search all Players");
        }

        writeToLOG("searched all players in system");
        return ans;
    }

    //or
    //the user chooses to search all coaches , returns all the coaches who have pages
    public LinkedHashSet<PrivatePage> searchAllCoaches(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
        for (Coach coach:system.getAllCoach()) {
                if(coach.getPage() != null) {
                    if (!ans.contains(coach.getPage())) {
                        ans.add(coach.getPage());
                    }
                }
            }


        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Search all Coaches");
        }

        writeToLOG("searched all coaches in system");
        return ans;
    }

    //or
    //the user chooses to search all teams , returns all the teams who have pages
    public LinkedHashSet<PrivatePage> searchAllTeams(){
        LinkedHashSet<PrivatePage> ans= new LinkedHashSet<>();
            for (Team t:system.getAllTeams()) {
                if( t.getPage() != null ){
                    ans.add(t.getPage());
                }
            }

        //if fan- save the search
        if(this instanceof Fan){
            ((Fan)this).addToSearchHistory("Search All Teams");
        }

        writeToLOG("searched all teams in system");
        return ans;
    }

    //</editor-fold>

    //<editor-fold desc="Filter Functions">

    /**OR**/
    public LinkedHashSet<PrivatePage> filterOnlyTeams(LinkedHashSet<PrivatePage> searchResults) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        for (PrivatePage privatePage:searchResults) {
            if((privatePage.getPageOwner() instanceof Team) ){
               newSearchResults.add(privatePage);
            }
        }

        writeToLOG("search filter- only teams");
        return newSearchResults;
    }

    /**OR**/
    public LinkedHashSet<PrivatePage> filterOnlyPlayers(LinkedHashSet<PrivatePage> searchResults) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        for (PrivatePage privatePage:searchResults) {
            if((privatePage.getPageOwner() instanceof Player) ){
                newSearchResults.add(privatePage);
            }
        }

        writeToLOG("search filter- only players");
        return newSearchResults;
    }

    /**OR**/
    public LinkedHashSet<PrivatePage> filterOnlyCoachs(LinkedHashSet<PrivatePage> searchResults) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        for (PrivatePage privatePage:searchResults) {
            if(privatePage.getPageOwner() instanceof Coach){
                newSearchResults.add(privatePage);
            }
        }

        writeToLOG("search filter- only coaches");
        return newSearchResults;
    }

    /**OR**/
    public LinkedHashSet<PrivatePage> filterByLeagueName(LinkedHashSet<PrivatePage> searchResults, String leagueName) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        if(leagueName==null){
            throw new Exception("leagueName null");
        }
        if(leagueName.length() ==0){
            throw new Exception("leagueName empty");
        }
        Season currS= system.getCurrSeason();
        for (PrivatePage privatePage:searchResults) {
            //check if player's team is in this league
            if((privatePage.getPageOwner() instanceof Player) ){//if false
                if( (((Player) privatePage.getPageOwner()).getTeam().getLeaguePerSeason().get(currS).getName()== leagueName)){
                    newSearchResults.add(privatePage);
                }
            }
            //check if coach's team is in this league
            else if((privatePage.getPageOwner() instanceof Coach) ){//if false
                if( (((Coach) privatePage.getPageOwner()).getCoachTeam().getLeaguePerSeason().get(currS).getName()== leagueName)){
                    newSearchResults.add(privatePage);
                }
            }
            //check if team is in this league
            else if((privatePage.getPageOwner() instanceof Team)){//if false
                if( (((Team) privatePage.getPageOwner()).getLeaguePerSeason().get(currS).getName()== leagueName)){
                    newSearchResults.add(privatePage);
                }
            }
        }

        writeToLOG("search filter- by league name: "+leagueName);
        return newSearchResults;
    }

    /**OR**/
    public LinkedHashSet<PrivatePage> filterBySeasonYear(LinkedHashSet<PrivatePage> searchResults, int seasonYear) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        if(seasonYear<1900 || seasonYear>2020){
            throw new Exception("year not valid");
        }
        for (PrivatePage privatePage:searchResults) {
            //check if player's team is in this season
            if((privatePage.getPageOwner() instanceof Player) ){
                if( (((Player) privatePage.getPageOwner()).getTeam().playedInSeason(seasonYear))){//if false
                    newSearchResults.add(privatePage);
                }
            }
            //check if coach's team is in this season
            else if((privatePage.getPageOwner() instanceof Coach) ){
                if( (((Coach) privatePage.getPageOwner()).getCoachTeam().playedInSeason(seasonYear))){//if false
                    newSearchResults.add(privatePage);
                }
            }
            //check if team is in this season
            else if((privatePage.getPageOwner() instanceof Team)){
                if((((Team) privatePage.getPageOwner()).playedInSeason(seasonYear))){// if false
                    newSearchResults.add(privatePage);
                }
            }
        }

        writeToLOG("search filter- by season year : "+seasonYear);
        return newSearchResults;
    }

    /**OR**/
    public LinkedHashSet<PrivatePage> filterByTeamName(LinkedHashSet<PrivatePage> searchResults, String teamName) throws Exception {
        LinkedHashSet<PrivatePage> newSearchResults= new LinkedHashSet<>();
        if(searchResults==null){
            throw new Exception("searchResults null");
        }
        if(searchResults.size() ==0){
            throw new Exception("no results in list");
        }
        if(teamName==null){
            throw new Exception("teamName null");
        }
        if(teamName.length() ==0){
            throw new Exception("teamName empty");
        }
        for (PrivatePage privatePage:searchResults) {
            //check player's team
            if((privatePage.getPageOwner() instanceof Player) ){
                if( (((Player) privatePage.getPageOwner()).getTeam().getName().equals(teamName))){//if false
                    newSearchResults.add(privatePage);
                }
            }
            //check if coach's team
            else if((privatePage.getPageOwner() instanceof Coach) ){
                if( (((Coach) privatePage.getPageOwner()).getCoachTeam().getName().equals(teamName))){//if false
                    newSearchResults.add(privatePage);
                }
            }
            //check if team is in this season
            else if((privatePage.getPageOwner() instanceof Team)){
                if( (((Team) privatePage.getPageOwner()).getName().equals(teamName))){// if false
                    newSearchResults.add(privatePage);
                }
            }
        }

        writeToLOG("search filter- by team name: "+teamName);
        return newSearchResults;
    }

    //</editor-fold>

    //<editor-fold desc="Sign in Functions">

    /**OR**/
    public TeamRole signInAsPlayer(String name, String phoneNumber, String email, String userName, String password, Date dateOfBirth){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber,email)){
            TeamRole newPlayer= new TeamRole(system,name,phoneNumber,email,userName,password);
            newPlayer.becomePlayer(dateOfBirth);
            system.removeUser(this);
            LOG.info(String.format("%s - %s", userName, "sign in as Player"));
            return newPlayer;
        }
        return null;
    }

    /**OR**/
    public TeamRole signInAsCoach(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber,email)){
            TeamRole newCoach= new TeamRole(system,name,phoneNumber,email,userName,password);
            newCoach.becomeCoach();
            system.removeUser(this);
            LOG.info(String.format("%s - %s", userName, "sign in as Coach"));
            return newCoach;
        }
        return null;
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
    public Fan signInAsFan(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber,email)){
            Fan newFan= new Fan(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            LOG.info(String.format("%s - %s", userName, "sign in as Fan"));
            return newFan;
        }
        return null;
    }

    /**OR**/
    /*
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
    */

    /**OR**/
    public Rfa signInAsRFA(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber,email)){
            Rfa newRFA= new Rfa(system,name,phoneNumber,email,userName,password);
            system.removeUser(this);
            LOG.info(String.format("%s - %s", userName, "sign in as RFA"));
            return newRFA;
        }
        return null;
    }

    /**OR**/
    public TeamRole signInAsTeamOwner(String name, String phoneNumber, String email, String userName, String password){
        // first check valid details
        if(checkValidDetails(userName,password,phoneNumber,email)){
            TeamRole teamOwner= new TeamRole(system,name,phoneNumber,email,userName,password);
            teamOwner.becomeTeamOwner();
            system.removeUser(this);
            LOG.info(String.format("%s - %s", userName, "sign in as team owner"));
            return teamOwner;
        }
        return null;
    }


    public boolean checkValidDetails(String userName, String password, String phoneNumber, String email){
        //check that username in unique
        for (Fan user:system.getAllFans()) {
                if(user.getUserName().equals(userName)){
                    return false;
                }
            }
        //password length is 6 or more
        if(password.length()<6){
            return false;
        }
        // phone number is 10 digits
        if( !( phoneNumber.matches("^[0-9]*$") && phoneNumber.length()==10) ){
            return false;
        }
        //email contains @
        if(! email.contains("@")){
            return false;
        }
        if( ! (email.contains(".com") || email.contains(".co.il"))){
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
    public Fan logIn(String userName, String password) throws Exception {
        if(userName==null){
            throw new Exception("userName null");
        }
        if(userName.length()==0){
            throw new Exception("userName empty");
        }
        if(password==null){
            throw new Exception("password null");
        }
        if(password.length()<6){
            throw new Exception("password not valid");
        }
        for (Fan fan:system.getAllFans()) {
                if(fan.getUserName().equals(userName) && fan.getPassword().equals(password)){
                    LOG.info(String.format("%s - %s", userName, "loged in to system"));
                    return fan;
                }
        }
        return null;
    }

    private void writeToLOG(String message){
        if(this instanceof Fan){
            LOG.info(String.format("%s - %s", ((Fan)this).getUserName(), message));
        }
        else{
            LOG.info(String.format("%s - %s", "Guest User", message));
        }
    }
}
