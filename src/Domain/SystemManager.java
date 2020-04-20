package Domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class SystemManager extends Fan implements Observer ,NotificationsUser {

    public static  HashSet<Complaint> complaints=new HashSet<>();//*
    private static final Logger LOG = LogManager.getLogger();
    HashSet<Notification> notifications;

    /***
     * create system manager from fan:
     *      1.adding system manager to system.
     *      2.remove fan from system.
     * @param fan
     * @param ms
     */
    public SystemManager(Fan fan, MainSystem ms) {
        super(ms, fan.getName(), fan.getPhoneNumber(), fan.getEmail(), fan.getUserName(), fan.getPassword(), fan.getDateOfBirth());
//        this.complaints = new HashSet<>();
        notifications=new HashSet<>();
        /**remove fan from system*/
        ms.removeUser(fan);
    }

    public SystemManager (MainSystem ms, String name, String phoneNumber, String email, String userName, String password, Date date) {
        super(ms,name,phoneNumber,email,userName,password,date);
//        this.complaints = new HashSet<>();
        notifications=new HashSet<>();

    }


    //<editor-fold desc="getters and setters">

    public HashSet<Complaint> getComplaints() {
        return complaints;
    }



    /**
     * create new Domain.SystemManager , connect to him all the complaints
     * @param user
     * @codeBy Eden
     */
    public SystemManager addNewSystemManager(Fan user) throws Exception {
        if(user==null){
            throw new Exception("user is null");
        }
        /**constructor make connection to system**/
        SystemManager newSystemManager=new SystemManager(user,system);
        /**delete Domain.Fan from system*/
        system.removeUser(user);
        /**add the new system Manager to be observer of all complaints **/
        for(Complaint c:this.getComplaints()){
            c.addObserver(newSystemManager);
        }
        return  newSystemManager;

    }
    /**Eden*/
    public static void answerToComplaint (Complaint com, String ans){
        if(SystemManager.complaints.contains(com)){
            com.setAnswer(ans);
            com.send(ans);
        }
    }
    /**Eden*/
    public static void addComplaint(Complaint complaint) {
        complaints.add(complaint);
        List<SystemManager> sm= MainSystem.getInstance().getSystemManagers();
        for(SystemManager cur:sm) {
            complaint.addObserver(cur);
        }
    }

    /**
     * Show details about the system events.
     * @return FileReader to the Log file
     * @throws FileNotFoundException
     * @codeby Eden
     */
    public FileReader showSystemInfo() throws FileNotFoundException {
        File file =new File("LOG.text");
        return new FileReader(file);
    }


    /**
     * Switch between team owner which is founder to another team owner.
     * @param toDelete- the team owner to delete
     * @param toAdd- the team owner to subscribe as founder
     * @param team
     * @return the removed objects
     * @throws Exception
     * @codeBy Eden
     */

    public void replaceTeamOwnerFounder(TeamOwner toAdd, TeamOwner toDelete, Team team) throws Exception {
        if(toAdd==null||toDelete==null){
            throw  new Exception("null input");
        }
        if(team.getFounder()==toDelete){
            team.setFounder(toAdd);
            team.getTeamOwners().add(toAdd);
            toAdd.addNewTeam(team);
        }
        else{
            throw new Exception("wrong team owner and team");
        }
    }


    //<editor-fold desc="Remove Domain.User">
    /**
     * remove user if it a valid system operation.
     * @param userToDelete
     * @return list of removed object
     * @throws Exception if the remove isnt valid
     * @codeBy Eden
     */
    //TODO test
    public List<Object> removeUser(User userToDelete) throws Exception {
        List<Object> objectsDeleted=new LinkedList<>();
        boolean isFan=true;


        if(userToDelete instanceof Rfa) {
            /***must have at least one RFA at system**/
            if(system.numOfRfa()==1){
                throw new Exception("There is only one RFA left, you cannot delete it ");
            }
            objectsDeleted=deleteRfa(((Rfa)userToDelete));
            isFan=false;
        }
        if(userToDelete instanceof SystemManager) {
            if(userToDelete==this){
                throw  new Exception("You Cannot Delete Yourself!!!");
            }
            /***must have at least one system Manager at system**/
            if(system.getSystemManagers().size()==1){
                throw new Exception("There is only one System Manager left, you cannot delete it ");
            }
            objectsDeleted=deleteSystemManager(((SystemManager)userToDelete));
            isFan=false;
        }
        if(userToDelete instanceof Referee) {
            objectsDeleted=deleteReferee(((Referee)userToDelete));
            isFan=false;

        }


        if(userToDelete instanceof TeamRole){
            objectsDeleted= teamRoleRemove(((TeamRole)userToDelete));
            isFan=false;

        }

        if(userToDelete instanceof Fan&& isFan){
            objectsDeleted=fanRemove(((Fan)userToDelete));
        }

        return objectsDeleted;
    }


    /**
     * remove fan from system , delete his all complaints-not relevant anyMore.
     * @param userToDelete
     * @return
     * @codeBy Eden
     */
    //TODO test
    private List<Object> fanRemove(Fan userToDelete) {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        /**remove fan complaints from system manager**/
        for(Complaint c:userToDelete.getMyComplaints()){
            SystemManager.complaints.remove(c);
            res.add(complaints);
        }
        /***remove user name from system*/
        system.getUserNames().remove(userToDelete.getUserName());
        return res;
    }

    /**
     * delete Domain.Team Manager and delete all his subscribe .
     * @param userToDelete
     * @codeBy Eden
     */
    //TODO test

    private List<Object> deleteTeamManager(TeamManager userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        if(userToDelete.getTeam()!=null) {
            userToDelete.getTeam().setTeamManager(null);
        }
        HashSet<TeamSubscription> allSub= userToDelete.getMySubscriptions();
        for(TeamSubscription sub: allSub){
            res.add(sub.role);
            if(sub.role instanceof TeamOwner){
                userToDelete.removeTeamOwner(((TeamOwner)sub.role),system,sub.team);
            }
        }
        return res;
    }


    /****
     * Domain.TeamRole handling:
     *  1.check if remove is valid ->(coach\Domain.TeamOwner\Domain.TeamManager\Domain.Player)
     *  2.if coach!=null -> handlingCoachRemove
     *  3.if Domain.TeamOwner!=null->handlingTeamOwnerRemove
     *  4.if Domain.TeamManager!=null->handlingTeamManagerRemove
     *  5.if player!=null->handlingPlayerRemove
     *  6.disconnect user from system.
     *
     * @return lost of removed objects.
     * @exception if the Deletion isn't valid
     * @codeBy Eden
     */
    //TODO test
    private List<Object> teamRoleRemove(TeamRole userToRemove) throws Exception {
        isValidRemove(userToRemove);
        /*******if not valid - exception thrown*******/
        List<Object> removedObjects=new LinkedList<>();
        /**if coach!=null -> handlingCoachRemove*/
        if(userToRemove.getCoach()!=null)  {
            removedObjects.addAll(deleteCoach(userToRemove.getCoach()));
        }
        /**if player!=null->handlingPlayerRemove*/
        if(userToRemove.getPlayer()!=null)  {
            removedObjects.addAll(deletePlayer(userToRemove.getPlayer()));
        }
        /**if Domain.TeamManager!=null->handlingTeamManagerRemove*/
        if(userToRemove.getTeamManager()!=null)  {
            removedObjects.addAll(deleteTeamManager(userToRemove.getTeamManager()));
        }
        /**if Domain.TeamOwner!=null->handlingTeamOwnerRemove*/
        if(userToRemove.getTeamOwner()!=null)  {
            removedObjects.addAll(deleteTeamOwner(userToRemove.getTeamOwner()));
        }

        /**disconnect user from system*/
        system.removeUser(userToRemove);

        return removedObjects;
    }

    /**
     * check if Domain.SystemManager can remove the Domain.TeamRole:
     *      1.if is coach and he connect to any team, throws Exception.
     *      2.if is player belong to any Domain.Team which hasn't more than 11 player throws Exception.
     *      3.if is Domain.TeamManager->always valid
     *      4.if is Domain.TeamOwner and he is a founder of any Domain.Team from Domain.Team list which he hold->throws Exception
     * @param userToRemove
     * @throws Exception
     */
    //TODO test
    private void isValidRemove(TeamRole userToRemove) throws Exception{
        /**if is coach-> check if coach is not connected to any team ->
         if connect throws Exception("you have to replace team coach")*/
        if(userToRemove.getCoach()!=null) {
            if(userToRemove.getCoach().getCoachTeam()!=null){
                throw new Exception("you have to subscribe another Domain.Coach to "+(userToRemove.getCoach()).getCoachTeam().getName()+" Domain.Team first");
            }
        }
        /**if is player-> check if the player belong to any Domain.Team , if so , check if team has more than 11 player ->
         if not throws Exception ("you have to add more players to team before delete ")*/
        if(userToRemove.getPlayer()!=null) {
            if (userToRemove.getPlayer().getTeam() != null) {
                if (userToRemove.getPlayer().getTeam().getPlayers().size() <= 11) {
                    throw new Exception("You Cannot Delete Domain.Player From " + userToRemove.getPlayer().getTeam().getName() + " Domain.Team ,any team have to be at least 11 Players!");
                }
            }
        }

        /**if is Domain.TeamOwner-> if he is a founder of any Domain.Team from Domain.Team list which he hold->
         *throws Exception("you have to replace team founder")*/
        if(userToRemove.getTeamOwner()!=null){
            LinkedList<Team> OwnerTeams =userToRemove.getTeamOwner().getTeams();
            for(Team t: OwnerTeams){
                if(t.getFounder()==userToRemove.getTeamOwner()){
                    throw new Exception(userToRemove.getName()+" is founder of:"+ " "+t.getName() +"please replace the team's fonder");
                }
            }
        }


    }

    /**
     * delete Domain.Team Owner only if he isn't a Founder of any team and delete all his subscribe .
     * @param userToDelete
     * @throws Exception if team owner is founder of any team.
     * @codeBy Eden
     */
    //TODO test
    private List<Object> deleteTeamOwner(TeamOwner userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);

        /**delete owner from deleted teams**/
        LinkedList<Team>teams= userToDelete.getDeletedTeams();
        for(Team t : teams){
            t.getTeamOwners().remove(userToDelete);
        }
        /***remove all the subscription**/
        HashSet<TeamSubscription> subscriptions= userToDelete.getMySubscriptions();
        for(TeamSubscription sub: subscriptions){
            if(sub.role instanceof TeamOwner){
                    userToDelete.removeTeamOwner(((TeamOwner) sub.role), system, sub.team);
            }

            if(sub.role instanceof TeamManager){
                userToDelete.removeTeamManager(((TeamManager)sub.role),system,sub.team);
            }
        }

        /**remove team request from RFA because the requests are not relevant**/
        for(Team t:userToDelete.getRequestedTeams()){
            Rfa.teamRequests.remove(t);
            res.add(t);
        }
        return res;
    }
    /**
     * delete Domain.Player only if the team has at least 11 other players
     * @param userToDelete
     * @throws Exception
     * @codeBy Eden
     */
    //TODO test
    private List<Object> deletePlayer(Player userToDelete) throws Exception {
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete Domain.Coach .
     * @param userToDelete
     * @codeBy Eden
     */
    //TODO test
    private List<Object> deleteCoach(Coach userToDelete) {
        List<Object> re=new LinkedList<>();
        re.add(userToDelete);
        return  re;
    }

    /**
     * delete Domain.Referee only if he hasn't any future match .
     * @param userToDelete
     * @return list with the referee object.
     * @throws Exception if referee has future matches.
     * @codeBy Eden
     */
//TODO test
    private List<Object> deleteReferee(Referee userToDelete) throws Exception {
        //check the all matches that the referee is refereeing
        for (Match m : userToDelete.getMatches()) {
            if (m.getStartDate().after(new Date(System.currentTimeMillis()))) {
                throw new Exception("You cannot remove referee , he has matches in this season");
            }
        }
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }

    /**
     * delete System Manager.
     * @param userToDelete
     * @codeBy Eden
     */
    //TODO test
    private List<Object> deleteSystemManager(SystemManager userToDelete) {
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }
    /**
     * delete RFA , disconnect from budget control and from system
     * @param userToDelete
     * @codeBy Eden
     */
    //TODO test
    private List<Object> deleteRfa(Rfa userToDelete){
        //userToDelete.getBudgetControl().removeRfa(userToDelete);
        system.removeUser(userToDelete);
        List<Object> res=new LinkedList<>();
        res.add(userToDelete);
        return res;
    }
    //</editor-fold>

    //<editor-fold desc="Domain.Team Remove">
    /**
     * remove team from system ,
     * if team does not have future Matches(not belong to current season).
     * doesnt delete all team matches.
     * disconnect the team owners and managers.
     * delete the team's subscriptions from their subscriptions list.
     * disconnect the coach
     * disconnect the players
     * @param teamToRemove
     * @codeBy Eden
     */
    //TODO test
    public void removeTeamFromSystem(Team teamToRemove) throws Exception {

        checkValidTeam(teamToRemove);
        /**delete team from owner*/
        HashSet<TeamOwner> teamOwners= teamToRemove.getTeamOwners();
        for(TeamOwner curTO:teamOwners){
            teamToRemove.addObserver(curTO);
            curTO.getTeams().remove(teamToRemove);
            /** delete the team's subscriptions from team owner subscriptions list**/
            HashSet<TeamSubscription> toRemove=new HashSet<>();
            for(TeamSubscription ts: curTO.getMySubscriptions()){
                if(ts.team==teamToRemove){
                    toRemove.add(ts);
                }
            }
            curTO.getMySubscriptions().removeAll(toRemove);
        }

        /**remove team manager from team*/
        TeamManager teamManager= teamToRemove.getTeamManager();
        if(teamManager!=null) {
            teamToRemove.addObserver(teamManager);
            teamManager.setTeam(null);
            teamManager.getTeamRole().deleteTeamManager();
        }

        /**remove coach*/
        teamToRemove.getCoach().setCoachTeam(null);
        /**remove players*/
        HashSet<Player> players = teamToRemove.getPlayers();
        for(Player p : players){
            p.setPlayerTeam(null);
        }

        /***remove team name from system*/
        system.getTeamNames().remove(teamToRemove.getName());

        /**remove from system*/
        system.getActiveTeams().remove(teamToRemove);
        //teamToRemove.sendNotiAbouteClose();

    }

    /***
     * check if team deletion is valid
     * @param teamToRemove
     * @throws Exception
     * @codeBy Eden
     */
    //TODO test
    private void checkValidTeam(Team teamToRemove) throws Exception {
        if(teamToRemove.getLeaguePerSeason().containsKey(system.getCurrSeason())){
            throw new Exception("cannot delete team in current season");
        }
        Date currDate= new Date(System.currentTimeMillis());
        for (Match m:teamToRemove.getHome()) {
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }
        for( Match m:teamToRemove.getAway()){
            if(m.getStartDate().after(currDate)){
                throw  new Exception("cannot delete team with future matches");
            }
        }
    }


    //</editor-fold>

    //<editor-fold desc="Notifications Handler">
    /**
     * mark notification as readen
     * @param not-unread notification to mark as read
     * @codeBy Eden
     */
    @Override
    public void MarkAsReadNotification(Notification not){
        not.isRead=true;
    }
    @Override
    public HashSet<Notification> getNotificationsList() {
        return notifications;
    }

    /***
     * @return only the unread notifications . if not have return null
     * @codeBy Eden
     */
    @Override
    public HashSet<Notification> genUnReadNotifications(){
        HashSet<Notification> unRead=new HashSet<>();
        for(Notification n: notifications){
            if(n.isRead==false){
                unRead.add(n);
            }
        }
        return unRead;
    }

    /**
     * Get notifications about:
     *  1.Open Domain.Team request.
     *  2.Get new complaint.
     * @param o
     * @param arg
     * @codeBy Or and Eden
     */
    @Override
    public void update(Observable o, Object arg) {
        /**Eden**/
        if(o instanceof  Complaint){
            if(o instanceof Complaint){
                /**if it's not an answer notify*/
                if(((Complaint)o).getAnswer()==null){
                    notifications.add(new Notification(o,arg,false));
                }
                /**if its an answer notify - remove from notifications(because system manager already handled )*/
                else{
                    for(Notification noti : notifications){
                        if(noti.sender==o){
                            noti.isRead=true;
                        }

                    }
                }
            }
        }

        /***Or*/
        if(o instanceof Team){
            if( arg.equals("team deleted by team owner")){// the team was deleted by system manager and not active any more
                notifications.add(new Notification(o,arg,false));
            }
            else if(arg.equals("team reopened by team owner")){
                notifications.add(new Notification(o,arg,false));

            }
        }
    }
    //</editor-fold>
}

